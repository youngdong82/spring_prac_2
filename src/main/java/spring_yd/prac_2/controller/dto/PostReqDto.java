package spring_yd.prac_2.controller.dto;

import lombok.Data;

@Data
public class PostReqDto {
    private String title;
    private String desc;
    private Long memberId;
    private Long categoryId;
}
