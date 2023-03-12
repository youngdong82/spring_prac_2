package spring_yd.prac_2.controller.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResDto {
    private final int errorCode;
    private final String errorMessage;
}
