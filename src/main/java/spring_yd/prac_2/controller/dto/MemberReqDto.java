package spring_yd.prac_2.controller.dto;

import lombok.Data;
import spring_yd.prac_2.domain.MemberType;

@Data
public class MemberReqDto {
    private String name;
    private MemberType type;
    private String teamName;
}
