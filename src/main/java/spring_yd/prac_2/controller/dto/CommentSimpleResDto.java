package spring_yd.prac_2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentSimpleResDto {
    private Long id;
    private MemberSimpleResDto member;
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
