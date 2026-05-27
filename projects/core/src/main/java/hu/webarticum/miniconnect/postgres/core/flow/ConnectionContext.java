package hu.webarticum.miniconnect.postgres.core.flow;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.postgres.core.model.CancellationKey;
import hu.webarticum.miniconnect.postgres.core.model.CString;
import hu.webarticum.miniconnect.postgres.core.model.ProtocolVersion;
import hu.webarticum.miniconnect.postgres.core.model.StartupParameter;
import hu.webarticum.miniconnect.postgres.core.model.TransactionStatus;

public interface ConnectionContext {

    public ProtocolVersion protocolVersion();

    public ImmutableList<StartupParameter> startupParameters();

    public CString startupParameter(CString name);

    public ProtocolPhase protocolPhase();

    public TransactionStatus transactionStatus();

    public boolean sslEnabled();

    public boolean gssEncryptionEnabled();

    public int backendProcessId();

    public CancellationKey cancellationSecretKey();

    public <T> T attribute(ConnectionAttributeKey<T> key);

    public <T> void attribute(ConnectionAttributeKey<T> key, T value);

}
