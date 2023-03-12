package spring_yd.prac_2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostResDto {
    private Long id;
    private MemberSimpleResDto member;
    private CategorySimpleResDto category;
    private String title;
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentSimpleResDto> comments;
}
