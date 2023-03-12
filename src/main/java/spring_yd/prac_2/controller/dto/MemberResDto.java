package spring_yd.prac_2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring_yd.prac_2.domain.MemberType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class MemberResDto {
    private Long id;
    private String name;
    private MemberType type;
    private String teamName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<PostSimpleResDto> posts;
}
