package hu.webarticum.miniconnect.postgres.core.model;

/** Authentication backend message distinguished by an authentication code. */
public interface AuthenticationMessage extends TaggedMessage, BackendMessage {

    public static final int MESSAGE_TYPE = 'R';

    /** One-byte message type code used on the wire. */
    @Override
    public default int messageType() {
        return MESSAGE_TYPE;
    }

    public int authenticationCode();

}
