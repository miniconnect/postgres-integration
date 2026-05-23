package hu.webarticum.miniconnect.postgres.core.model;

/** Framework-level phase of a PostgreSQL protocol connection. */
public enum ProtocolPhase {

    STARTUP,
    AUTHENTICATION,
    STARTUP_COMPLETION,
    READY,
    SIMPLE_QUERY,
    EXTENDED_QUERY,
    EXTENDED_QUERY_SKIP_UNTIL_SYNC,
    FUNCTION_CALL,
    COPY_IN,
    COPY_OUT,
    COPY_BOTH,
    TERMINATED,
    ;

}
