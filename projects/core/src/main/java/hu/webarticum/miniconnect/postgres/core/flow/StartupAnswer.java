package hu.webarticum.miniconnect.postgres.core.flow;

import java.util.Objects;

import hu.webarticum.miniconnect.lang.ImmutableList;
import hu.webarticum.miniconnect.postgres.core.model.CString;
import hu.webarticum.miniconnect.postgres.core.model.ErrorResponse;
import hu.webarticum.miniconnect.postgres.core.model.ProtocolVersion;

public final class StartupAnswer {

    public enum Action {
        ACCEPT,
        REJECT,
        NEGOTIATE_PROTOCOL_VERSION
    }

    private final Action action;

    private final ErrorResponse errorResponse;

    private final ProtocolVersion newestSupportedProtocolVersion;

    private final ImmutableList<CString> unrecognizedProtocolOptions;

    private StartupAnswer(
            Action action,
            ErrorResponse errorResponse,
            ProtocolVersion newestSupportedProtocolVersion,
            ImmutableList<CString> unrecognizedProtocolOptions) {
        this.action = Objects.requireNonNull(action, "action");
        this.errorResponse = errorResponse;
        this.newestSupportedProtocolVersion = newestSupportedProtocolVersion;
        this.unrecognizedProtocolOptions = unrecognizedProtocolOptions;
    }

    public static StartupAnswer accept() {
        return new StartupAnswer(Action.ACCEPT, null, null, null);
    }

    public static StartupAnswer reject(ErrorResponse errorResponse) {
        return new StartupAnswer(Action.REJECT, Objects.requireNonNull(errorResponse, "errorResponse"), null, null);
    }

    public static StartupAnswer negotiateProtocolVersion(
            ProtocolVersion newestSupportedProtocolVersion,
            ImmutableList<CString> unrecognizedProtocolOptions) {
        return new StartupAnswer(
                Action.NEGOTIATE_PROTOCOL_VERSION,
                null,
                Objects.requireNonNull(newestSupportedProtocolVersion, "newestSupportedProtocolVersion"),
                Objects.requireNonNull(unrecognizedProtocolOptions, "unrecognizedProtocolOptions"));
    }

    public Action action() {
        return action;
    }

    public ErrorResponse errorResponse() {
        return errorResponse;
    }

    public ProtocolVersion newestSupportedProtocolVersion() {
        return newestSupportedProtocolVersion;
    }

    public ImmutableList<CString> unrecognizedProtocolOptions() {
        return unrecognizedProtocolOptions;
    }

}
