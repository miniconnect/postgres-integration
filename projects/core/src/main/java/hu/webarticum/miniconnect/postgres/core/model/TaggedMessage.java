package hu.webarticum.miniconnect.postgres.core.model;

/** Message identified by a one-byte message type. */
public interface TaggedMessage extends PostgresMessage {

    /** One-byte message type code used on the wire. */
    public int messageType();

}
