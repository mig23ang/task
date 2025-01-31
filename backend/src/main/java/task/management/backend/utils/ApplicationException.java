package task.management.backend.utils;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private String message;
    private String errorCode;


    public ApplicationException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getFormattedMessage() {
        return String.format("Error %s: %s", errorCode, message);
    }
}
