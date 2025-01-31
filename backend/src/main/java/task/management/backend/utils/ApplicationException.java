package task.management.backend.utils;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final String errorCode;

    public ApplicationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
