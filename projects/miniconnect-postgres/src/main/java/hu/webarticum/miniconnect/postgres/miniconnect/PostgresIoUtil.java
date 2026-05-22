package hu.webarticum.miniconnect.postgres.miniconnect;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class PostgresIoUtil {

    private PostgresIoUtil() {
        // utility class
    }

    public static void sendMessage(OutputStream out, int messageType, byte[] payload) throws IOException {
        out.write(messageType);
        writeI32(out, payload.length + 4);
        out.write(payload);
    }

    public static byte[] readBytes(InputStream in, int length) throws IOException {
        byte[] result = new byte[length];
        int offset = 0;
        while (offset < result.length) {
            int readLength = in.read(result, offset, result.length - offset);
            if (readLength < 0) {
                throw new EOFException();
            }
            offset += readLength;
        }
        return result;
    }

    public static String readCString(byte[] payload, int offset) {
        int endOffset = offset;
        while (endOffset < payload.length && payload[endOffset] != 0) {
            endOffset++;
        }
        return new String(payload, offset, endOffset - offset, StandardCharsets.UTF_8);
    }

    public static void writeCString(OutputStream out, String value) throws IOException {
        out.write(value.getBytes(StandardCharsets.UTF_8));
        out.write(0);
    }

    public static void writeI16(OutputStream out, int value) throws IOException {
        out.write((value >>> 8) & 0xFF);
        out.write(value & 0xFF);
    }

    public static int readI32(InputStream in) throws IOException {
        int b1 = in.read();
        int b2 = in.read();
        int b3 = in.read();
        int b4 = in.read();
        if ((b1 | b2 | b3 | b4) < 0) {
            throw new EOFException();
        }
        return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
    }

    public static void writeI32(OutputStream out, int value) throws IOException {
        out.write((value >>> 24) & 0xFF);
        out.write((value >>> 16) & 0xFF);
        out.write((value >>> 8) & 0xFF);
        out.write(value & 0xFF);
    }

    public static Map<String, String> readStartupParams(InputStream in, int length) throws IOException {
        byte[] payload = readBytes(in, length);
        Map<String, String> result = new LinkedHashMap<>();
        int offset = 0;
        while (offset < payload.length && payload[offset] != 0) {
            String key = readCString(payload, offset);
            offset += key.getBytes(StandardCharsets.UTF_8).length + 1;
            String value = readCString(payload, offset);
            offset += value.getBytes(StandardCharsets.UTF_8).length + 1;
            result.put(key, value);
        }
        return result;
    }

    public static void sendParameterStatus(OutputStream out, String name, String value) throws IOException {
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
        writeCString(payload, name);
        writeCString(payload, value);
        sendMessage(out, 'S', payload.toByteArray());
    }

    public static void sendBackendKeyData(OutputStream out) throws IOException {
        Random random = new Random();
        out.write('K');
        writeI32(out, 12);
        writeI32(out, random.nextInt());
        writeI32(out, random.nextInt());
    }

    public static void sendReadyForQuery(OutputStream out) throws IOException {
        out.write('Z');
        writeI32(out, 5);
        out.write('I');
    }

    public static void sendEmptyQueryResponse(OutputStream out) throws IOException {
        out.write('I');
        writeI32(out, 4);
    }

    public static void sendAuthenticationOk(OutputStream out) throws IOException {
        out.write('R');
        writeI32(out, 8);
        writeI32(out, 0);
    }

    public static void sendErrorResponse(OutputStream out, String sqlState, String message) throws IOException {
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
        payload.write('S');
        writeCString(payload, "ERROR");
        payload.write('C');
        writeCString(payload, sqlState);
        payload.write('M');
        writeCString(payload, message);
        payload.write(0);
        sendMessage(out, 'E', payload.toByteArray());
    }

    public static void sendCommandComplete(OutputStream out, String commandTag) throws IOException {
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
        writeCString(payload, commandTag);
        sendMessage(out, 'C', payload.toByteArray());
    }

}
