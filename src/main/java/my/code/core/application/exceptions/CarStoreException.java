package my.code.core.application.exceptions;

import lombok.Getter;

/**
 * The base class of all system exceptions that would otherwise extend {@link RuntimeException}.
 */

@Getter
public class CarStoreException extends RuntimeException {

    private final String messageKey;
    private final Object[] params;

    public CarStoreException(String messageKey, Object... params) {
        this.messageKey = messageKey;
        this.params = params;
    }
}
