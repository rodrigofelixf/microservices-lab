package io.github.rodrigofelix.mscreditvalidation.ex;

import lombok.Getter;

public class ErrorCommunicationMicroservicesException extends Exception {

    @Getter
    private Integer status;

    public ErrorCommunicationMicroservicesException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}

