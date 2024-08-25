package my.code.core.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends CarStoreException {

    public NotFoundException(String messageKey, Object... params) {
        super(messageKey, params);
    }

}
