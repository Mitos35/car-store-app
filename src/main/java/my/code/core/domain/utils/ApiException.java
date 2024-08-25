package my.code.core.domain.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ApiException extends ResponseStatusException {

    public ApiException(String reason) {
        this(HttpStatus.BAD_REQUEST, reason);
    }

    private ApiException(HttpStatus status, String reason) {
        super(status, reason);
    }

}
