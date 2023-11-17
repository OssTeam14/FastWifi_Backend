package oss.fastwifi.verification.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class NaverSmsRes {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}