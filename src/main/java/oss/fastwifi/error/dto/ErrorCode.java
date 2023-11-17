package oss.fastwifi.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C-000", "잘못된 요청입니다\n다시 한 번 확인해주세요"),

    NOT_FOUND(HttpStatus.NOT_FOUND, "C-001", "리소스를 찾을 수 없음"),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C-002", "허용되지 않은 Request Method 호출"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-003", "알 수 없는 오류가 발생하였습니다."),

    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "C-004", "요청 인자가 유효하지 않음"),

    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C-005", "유효하지 않은 값 타입"),

    FORBIDDEN(HttpStatus.FORBIDDEN, "C-006","접근 권한이 없습니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C-007", "로그인이 필요합니다."),

    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "J-001", "유효하지 않은 JWT 토큰입니다."),

    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "J-002", "만료된 JWT 토큰입니다."),

    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "J-003", "지원하지 않는 JWT 토큰입니다."),

    WRONG_ID(HttpStatus.BAD_REQUEST, "A-001", "존재하지 않는 아이디입니다."),

    WRONG_PW(HttpStatus.BAD_REQUEST, "A-002", "비밀번호가 일치하지 않습니다."),

    ID_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "A-003", "이미 사용중인 아이디 입니다."),

    UNMATCHING_NEW_PASSWORD(HttpStatus.BAD_REQUEST, "A-004", "비밀번호가 일치하지 않습니다."),

    INCORRECT_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "V-001", "잘못된 인증번호입니다."),

    EXPIRED_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "V-002", "인증번호 유효시간이 지났습니다. 하단의 버튼을 눌러 재전송해 주세요."),

    UNEXISTING_USER(HttpStatus.BAD_REQUEST, "V-003", "해당 이름과 전화번호를 가진 유저는 존재하지 않습니다."),

    NEED_VERIFICATION(HttpStatus.BAD_REQUEST, "V-004", "전화번호 인증이 사전 수행되어야 합니다."),

    UNEXISTING_ID(HttpStatus.BAD_REQUEST, "V-005", "해당 아이디의 유저는 존재하지 않습니다."),

    UNMATCHING_PHONE_NUM(HttpStatus.BAD_REQUEST, "V-006", "회원정보에 등록된 전화번호와 일치하지 않습니다."),
    UNABLE_TO_SEND_EMAIL(HttpStatus.BAD_REQUEST, "V-007", "메일을 보낼 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
