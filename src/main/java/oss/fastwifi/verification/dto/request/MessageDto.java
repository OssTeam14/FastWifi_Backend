package oss.fastwifi.verification.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    String to;
    String content;
}
