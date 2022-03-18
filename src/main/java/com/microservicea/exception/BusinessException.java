package com.microservicea.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

        private static final long serialVersionUID = -4456050434471317723L;
        private final int httpStatusCode;

        public BusinessException(String message, int httpStatusCode) {
            super(message);
            this.httpStatusCode = httpStatusCode;
        }
}
