package hu.webarticum.miniconnect.postgres.core.flow;

/** PostgreSQL wire-level constants used outside the model layer. */
public final class PostgresWireConstants {

    /** Cancel request startup packet code. */
    public static final int CANCEL_REQUEST_CODE = 80877102;

    /** SSL request startup packet code. */
    public static final int SSL_REQUEST_CODE = 80877103;

    /** GSSAPI encryption request startup packet code. */
    public static final int GSS_ENCRYPTION_REQUEST_CODE = 80877104;

    /** Authentication message type. */
    public static final int AUTHENTICATION_MESSAGE_TYPE = 'R';

    /** BackendKeyData message type. */
    public static final int BACKEND_KEY_DATA_MESSAGE_TYPE = 'K';

    /** BindComplete message type. */
    public static final int BIND_COMPLETE_MESSAGE_TYPE = '2';

    /** Bind message type. */
    public static final int BIND_MESSAGE_TYPE = 'B';

    /** CloseComplete message type. */
    public static final int CLOSE_COMPLETE_MESSAGE_TYPE = '3';

    /** ClosePortal message type. */
    public static final int CLOSE_PORTAL_MESSAGE_TYPE = 'C';

    /** CloseStatement message type. */
    public static final int CLOSE_STATEMENT_MESSAGE_TYPE = 'C';

    /** CommandComplete message type. */
    public static final int COMMAND_COMPLETE_MESSAGE_TYPE = 'C';

    /** CopyBothResponse message type. */
    public static final int COPY_BOTH_RESPONSE_MESSAGE_TYPE = 'W';

    /** CopyData message type. */
    public static final int COPY_DATA_MESSAGE_TYPE = 'd';

    /** CopyDone message type. */
    public static final int COPY_DONE_MESSAGE_TYPE = 'c';

    /** CopyFail message type. */
    public static final int COPY_FAIL_MESSAGE_TYPE = 'f';

    /** CopyInResponse message type. */
    public static final int COPY_IN_RESPONSE_MESSAGE_TYPE = 'G';

    /** CopyOutResponse message type. */
    public static final int COPY_OUT_RESPONSE_MESSAGE_TYPE = 'H';

    /** DataRow message type. */
    public static final int DATA_ROW_MESSAGE_TYPE = 'D';

    /** DescribePortal message type. */
    public static final int DESCRIBE_PORTAL_MESSAGE_TYPE = 'D';

    /** DescribeStatement message type. */
    public static final int DESCRIBE_STATEMENT_MESSAGE_TYPE = 'D';

    /** EmptyQueryResponse message type. */
    public static final int EMPTY_QUERY_RESPONSE_MESSAGE_TYPE = 'I';

    /** ErrorResponse message type. */
    public static final int ERROR_RESPONSE_MESSAGE_TYPE = 'E';

    /** Execute message type. */
    public static final int EXECUTE_MESSAGE_TYPE = 'E';

    /** Flush message type. */
    public static final int FLUSH_MESSAGE_TYPE = 'H';

    /** FunctionCall message type. */
    public static final int FUNCTION_CALL_MESSAGE_TYPE = 'F';

    /** FunctionCallResponse message type. */
    public static final int FUNCTION_CALL_RESPONSE_MESSAGE_TYPE = 'V';

    /** GssResponse message type. */
    public static final int GSS_RESPONSE_MESSAGE_TYPE = 'p';

    /** NegotiateProtocolVersion message type. */
    public static final int NEGOTIATE_PROTOCOL_VERSION_MESSAGE_TYPE = 'v';

    /** NoData message type. */
    public static final int NO_DATA_MESSAGE_TYPE = 'n';

    /** NoticeResponse message type. */
    public static final int NOTICE_RESPONSE_MESSAGE_TYPE = 'N';

    /** NotificationResponse message type. */
    public static final int NOTIFICATION_RESPONSE_MESSAGE_TYPE = 'A';

    /** ParameterDescription message type. */
    public static final int PARAMETER_DESCRIPTION_MESSAGE_TYPE = 't';

    /** ParameterStatus message type. */
    public static final int PARAMETER_STATUS_MESSAGE_TYPE = 'S';

    /** ParseComplete message type. */
    public static final int PARSE_COMPLETE_MESSAGE_TYPE = '1';

    /** Parse message type. */
    public static final int PARSE_MESSAGE_TYPE = 'P';

    /** Password message type. */
    public static final int PASSWORD_MESSAGE_TYPE = 'p';

    /** PortalSuspended message type. */
    public static final int PORTAL_SUSPENDED_MESSAGE_TYPE = 's';

    /** Query message type. */
    public static final int QUERY_MESSAGE_TYPE = 'Q';

    /** ReadyForQuery message type. */
    public static final int READY_FOR_QUERY_MESSAGE_TYPE = 'Z';

    /** RowDescription message type. */
    public static final int ROW_DESCRIPTION_MESSAGE_TYPE = 'T';

    /** SaslInitialResponse message type. */
    public static final int SASL_INITIAL_RESPONSE_MESSAGE_TYPE = 'p';

    /** SaslResponse message type. */
    public static final int SASL_RESPONSE_MESSAGE_TYPE = 'p';

    /** Sync message type. */
    public static final int SYNC_MESSAGE_TYPE = 'S';

    /** Terminate message type. */
    public static final int TERMINATE_MESSAGE_TYPE = 'X';

    /** AuthenticationOk authentication code. */
    public static final int AUTHENTICATION_OK_CODE = 0;

    /** AuthenticationKerberosV5 authentication code. */
    public static final int AUTHENTICATION_KERBEROS_V5_CODE = 2;

    /** AuthenticationCleartextPassword authentication code. */
    public static final int AUTHENTICATION_CLEARTEXT_PASSWORD_CODE = 3;

    /** AuthenticationMd5Password authentication code. */
    public static final int AUTHENTICATION_MD5_PASSWORD_CODE = 5;

    /** AuthenticationGss authentication code. */
    public static final int AUTHENTICATION_GSS_CODE = 7;

    /** AuthenticationGssContinue authentication code. */
    public static final int AUTHENTICATION_GSS_CONTINUE_CODE = 8;

    /** AuthenticationSspi authentication code. */
    public static final int AUTHENTICATION_SSPI_CODE = 9;

    /** AuthenticationSasl authentication code. */
    public static final int AUTHENTICATION_SASL_CODE = 10;

    /** AuthenticationSaslContinue authentication code. */
    public static final int AUTHENTICATION_SASL_CONTINUE_CODE = 11;

    /** AuthenticationSaslFinal authentication code. */
    public static final int AUTHENTICATION_SASL_FINAL_CODE = 12;

    private PostgresWireConstants() {
        // Utility class.
    }

}
