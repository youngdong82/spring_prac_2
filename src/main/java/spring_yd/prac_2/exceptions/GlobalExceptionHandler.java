package spring_yd.prac_2.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring_yd.prac_2.controller.dto.mapper.ErrorResDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ErrorResDto handleBadRequestException(BadRequestException ex){
        ErrorResDto errorResDto = ErrorResDto.builder()
                .errorCode(ex.getHttpStatus().value())
                .errorMessage(ex.getMessage())
                .build();

        return errorResDto;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResDto handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        return ErrorResDto.builder()
                .errorCode(500)
                .errorMessage("그런 데이터는 없어요")
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResDto handleDataIntegrityViolationException(IllegalArgumentException ex){
        return ErrorResDto.builder()
                .errorCode(500)
                .errorMessage("그런 데이터는 없어요")
                .build();
    }

}
