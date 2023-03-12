package spring_yd.prac_2.controller.dto;

import lombok.Data;

@Data
public class CommentReqDto {
    private Long memberId;
    private Long postId;
    private Long parentId;
    private String desc;
}
