package com.nmquan1503.backend_springboot.exceptions;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    
    private final ResponseCode responseCode;

    public GeneralException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public GeneralException(ResponseCode responseCode, Throwable cause) {
        super(responseCode.getMessage(), cause);
        this.responseCode = responseCode;
    }

}
