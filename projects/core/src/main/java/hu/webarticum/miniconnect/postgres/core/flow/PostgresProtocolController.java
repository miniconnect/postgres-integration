package hu.webarticum.miniconnect.postgres.core.flow;

import hu.webarticum.miniconnect.postgres.core.model.BindMessage;
import hu.webarticum.miniconnect.postgres.core.model.CopyDataMessage;
import hu.webarticum.miniconnect.postgres.core.model.ExecuteMessage;
import hu.webarticum.miniconnect.postgres.core.model.ParseMessage;
import hu.webarticum.miniconnect.postgres.core.model.PasswordMessage;
import hu.webarticum.miniconnect.postgres.core.model.QueryMessage;
import hu.webarticum.miniconnect.postgres.core.model.StartupMessage;
import hu.webarticum.miniconnect.postgres.core.model.TerminateMessage;

/** God interface for the entire protocol flow as a whole. */
public interface PostgresProtocolController {

    // TODO: encryption, authentication

    public StartupAnswer onStartup(ConnectionContext context, StartupMessage message);

    // TODO
    public Object /*AuthenticationAnswer*/ onPassword(ConnectionContext context, PasswordMessage message);

    // TODO
    public Object /*QueryAnswer*/ onQuery(ConnectionContext context, QueryMessage message);

    // TODO
    public Object /*ParseAnswer*/ onParse(ConnectionContext context, ParseMessage message);

    // TODO
    public Object /*BindAnswer*/ onBind(ConnectionContext context, BindMessage message);

    // TODO
    public Object /*ExecuteAnswer*/ onExecute(ConnectionContext context, ExecuteMessage message);

    // TODO
    public Object /*CopyInAnswer*/ onCopyData(ConnectionContext context, CopyDataMessage message);

    // TODO
    public Object /*TerminationAnswer*/ onTerminate(ConnectionContext context, TerminateMessage message);


}
