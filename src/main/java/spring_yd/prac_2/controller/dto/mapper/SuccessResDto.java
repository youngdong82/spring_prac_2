package spring_yd.prac_2.controller.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 결과값을 그대로 반환하면 데이터의 유연성이 크게 떨어짐
 * + 아무것도 리턴하지 않는 API의 경우에도 메시지를 리턴할 수 있다.
 * 한번 감싸서 리턴하자
 * 근데 이러면 T 타입체크 어떻게 해? => 해결
 * @param <T>
 */
@Data
@AllArgsConstructor
public class SuccessResDto<T> {
    private int statusCode;
    private String message;
    private T data;
}
