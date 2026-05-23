package hu.webarticum.miniconnect.postgres.core.flow;

import hu.webarticum.miniconnect.postgres.core.model.StartupMessage;

/** God interface for the entire protocol flow as a whole. */
public interface PostgresProtocolController {

    public StartupAnswer onStartup(ConnectionContext context, StartupMessage message);

    // TODO

}
