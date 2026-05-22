package hu.webarticum.miniconnect.postgres.miniconnect;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

public class ServerMain {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 3432;

    private static final int PROTOCOL_VERSION_3_0 = 196608;

    private static final int SSL_REQUEST = 0x04D2162F;

    private static final int CANCEL_REQUEST = 0x04D2162E;

    private static final int TEXT_FORMAT = 0;

    private static final int TYPE_TEXT = 25;

    private static final int TYPE_INT4 = 23;

    private static final int TYPE_BOOL = 16;

    private static final int TYPE_UNKNOWN = 705;

    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(HOST, PORT);
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(true);
            serverSocket.bind(socketAddress);
            System.out.println("Listening on " + socketAddress + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    handleClient(socket);
                } catch (EOFException e) {
                    System.out.println("Client disconnected");
                } catch (IOException e) {
                    System.err.println("Client error: " + e.getMessage());
                } finally {
                    socket.close();
                }
            }
        }
    }

    private static void handleClient(Socket socket) throws IOException {
        socket.setTcpNoDelay(true);
        socket.setKeepAlive(true);
        socket.setSoTimeout(30_000);
        System.out.println("Accepted " + socket.getRemoteSocketAddress());

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        if (!handleStartup(in, out)) {
            return;
        }

        PostgresIoUtil.sendAuthenticationOk(out);
        PostgresIoUtil.sendParameterStatus(out, "server_version", "15.0-miniconnect");
        PostgresIoUtil.sendParameterStatus(out, "server_encoding", "UTF8");
        PostgresIoUtil.sendParameterStatus(out, "client_encoding", "UTF8");
        PostgresIoUtil.sendParameterStatus(out, "DateStyle", "ISO, MDY");
        PostgresIoUtil.sendParameterStatus(out, "IntervalStyle", "postgres");
        PostgresIoUtil.sendParameterStatus(out, "TimeZone", "UTC");
        PostgresIoUtil.sendParameterStatus(out, "standard_conforming_strings", "on");
        PostgresIoUtil.sendParameterStatus(out, "integer_datetimes", "on");
        PostgresIoUtil.sendBackendKeyData(out);
        PostgresIoUtil.sendReadyForQuery(out);
        out.flush();

        while (true) {
            int messageType = in.read();
            if (messageType < 0 || messageType == 'X') {
                return;
            }

            int messageLength = PostgresIoUtil.readI32(in);
            byte[] payload = PostgresIoUtil.readBytes(in, messageLength - 4);
            if (messageType == 'Q') {
                handleSimpleQuery(out, PostgresIoUtil.readCString(payload, 0));
                out.flush();
            } else {
                PostgresIoUtil.sendErrorResponse(out, "0A000", "unsupported frontend message: " + (char) messageType);
                PostgresIoUtil.sendReadyForQuery(out);
                out.flush();
            }
        }
    }

    private static boolean handleStartup(InputStream in, OutputStream out) throws IOException {
        while (true) {
            int messageLength = PostgresIoUtil.readI32(in);
            int protocolCode = PostgresIoUtil.readI32(in);
            if (protocolCode == SSL_REQUEST) {
                out.write('N');
                out.flush();
            } else if (protocolCode == CANCEL_REQUEST) {
                PostgresIoUtil.readBytes(in, messageLength - 8);
                return false;
            } else if (protocolCode == PROTOCOL_VERSION_3_0) {
                Map<String, String> params = PostgresIoUtil.readStartupParams(in, messageLength - 8);
                System.out.printf(
                        "Startup: user=%s database=%s application=%s%n",
                        params.getOrDefault("user", ""),
                        params.getOrDefault("database", ""),
                        params.getOrDefault("application_name", ""));
                return true;
            } else {
                PostgresIoUtil.readBytes(in, messageLength - 8);
                PostgresIoUtil.sendErrorResponse(out, "08P01", "unsupported protocol version: " + protocolCode);
                out.flush();
                return false;
            }
        }
    }

    private static void handleSimpleQuery(OutputStream out, String query) throws IOException {
        String trimmedQuery = query.trim();
        System.out.println("Query: " + trimmedQuery);
        if (trimmedQuery.isEmpty()) {
            PostgresIoUtil.sendEmptyQueryResponse(out);
            PostgresIoUtil.sendReadyForQuery(out);
            return;
        }

        String normalizedQuery = normalizeQuery(trimmedQuery);
        if (normalizedQuery.startsWith("select ")) {
            sendSelectResponse(out, trimmedQuery, normalizedQuery);
        } else if (normalizedQuery.startsWith("show ")) {
            sendShowResponse(out, normalizedQuery);
        } else if (normalizedQuery.equals("begin") || normalizedQuery.equals("start transaction")) {
            PostgresIoUtil.sendCommandComplete(out, "BEGIN");
        } else if (normalizedQuery.equals("commit")) {
            PostgresIoUtil.sendCommandComplete(out, "COMMIT");
        } else if (normalizedQuery.equals("rollback")) {
            PostgresIoUtil.sendCommandComplete(out, "ROLLBACK");
        } else if (normalizedQuery.startsWith("set ")) {
            PostgresIoUtil.sendCommandComplete(out, "SET");
        } else {
            PostgresIoUtil.sendCommandComplete(out, "OK");
        }
        PostgresIoUtil.sendReadyForQuery(out);
    }

    private static void sendSelectResponse(OutputStream out, String query, String normalizedQuery) throws IOException {
        if (normalizedQuery.equals("select 1")) {
            sendRowDescription(out, new Column("one", TYPE_INT4));
            sendDataRow(out, "1");
            PostgresIoUtil.sendCommandComplete(out, "SELECT 1");
        } else if (normalizedQuery.equals("select version()")) {
            sendRowDescription(out, new Column("version", TYPE_TEXT));
            sendDataRow(out, "PostgreSQL 15.0-miniconnect sandbox");
            PostgresIoUtil.sendCommandComplete(out, "SELECT 1");
        } else if (normalizedQuery.equals("select current_database()")) {
            sendRowDescription(out, new Column("current_database", TYPE_TEXT));
            sendDataRow(out, "miniconnect");
            PostgresIoUtil.sendCommandComplete(out, "SELECT 1");
        } else {
            sendRowDescription(out, new Column("?column?", TYPE_TEXT));
            sendDataRow(out, "miniconnect received: " + query);
            PostgresIoUtil.sendCommandComplete(out, "SELECT 1");
        }
    }

    private static void sendShowResponse(OutputStream out, String normalizedQuery) throws IOException {
        String settingName = normalizedQuery.substring("show ".length()).replace(";", "").trim();
        String value;
        if ("server_version".equals(settingName)) {
            value = "15.0-miniconnect";
        } else if ("client_encoding".equals(settingName) || "server_encoding".equals(settingName)) {
            value = "UTF8";
        } else if ("standard_conforming_strings".equals(settingName)) {
            value = "on";
        } else if ("transaction_isolation".equals(settingName)) {
            value = "read committed";
        } else if ("integer_datetimes".equals(settingName)) {
            value = "on";
        } else if ("timezone".equals(settingName)) {
            value = "UTC";
        } else {
            value = "";
        }

        sendRowDescription(out, new Column(settingName, TYPE_TEXT));
        sendDataRow(out, value);
        PostgresIoUtil.sendCommandComplete(out, "SHOW");
    }

    private static String normalizeQuery(String query) {
        String normalized = query.toLowerCase(Locale.ROOT).trim();
        while (normalized.endsWith(";")) {
            normalized = normalized.substring(0, normalized.length() - 1).trim();
        }
        return normalized.replaceAll("\\s+", " ");
    }

    private static void sendRowDescription(OutputStream out, Column... columns) throws IOException {
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
        PostgresIoUtil.writeI16(payload, columns.length);
        for (Column column : columns) {
            PostgresIoUtil.writeCString(payload, column.name);
            PostgresIoUtil.writeI32(payload, 0); // table oid
            PostgresIoUtil.writeI16(payload, 0); // attribute number
            PostgresIoUtil.writeI32(payload, column.typeOid);
            PostgresIoUtil.writeI16(payload, typeSize(column.typeOid));
            PostgresIoUtil.writeI32(payload, -1); // type modifier
            PostgresIoUtil.writeI16(payload, TEXT_FORMAT);
        }
        PostgresIoUtil.sendMessage(out, 'T', payload.toByteArray());
    }

    private static void sendDataRow(OutputStream out, String... values) throws IOException {
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
        PostgresIoUtil.writeI16(payload, values.length);
        for (String value : values) {
            if (value == null) {
                PostgresIoUtil.writeI32(payload, -1);
            } else {
                byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
                PostgresIoUtil.writeI32(payload, bytes.length);
                payload.write(bytes);
            }
        }
        PostgresIoUtil.sendMessage(out, 'D', payload.toByteArray());
    }

    private static int typeSize(int typeOid) {
        if (typeOid == TYPE_BOOL) {
            return 1;
        } else if (typeOid == TYPE_INT4) {
            return 4;
        } else if (typeOid == TYPE_TEXT || typeOid == TYPE_UNKNOWN) {
            return -1;
        }
        return -1;
    }

    private static final class Column {

        private final String name;

        private final int typeOid;

        private Column(String name, int typeOid) {
            this.name = name;
            this.typeOid = typeOid;
        }

    }

}
