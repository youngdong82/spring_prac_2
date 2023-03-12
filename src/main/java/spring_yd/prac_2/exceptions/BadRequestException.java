package spring_yd.prac_2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BadRequestException extends RuntimeException{
    private final HttpStatus httpStatus;
    public final String message;
}
