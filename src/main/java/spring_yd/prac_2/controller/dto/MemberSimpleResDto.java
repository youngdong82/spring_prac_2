package spring_yd.prac_2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberSimpleResDto {
    private Long id;
    private String name;
}
