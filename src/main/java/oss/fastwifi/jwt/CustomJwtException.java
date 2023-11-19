package oss.fastwifi.jwt;

import lombok.Getter;
import oss.fastwifi.error.dto.ErrorCode;
import oss.fastwifi.error.exception.BusinessException;

@Getter
public class CustomJwtException extends BusinessException {
    public CustomJwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}
