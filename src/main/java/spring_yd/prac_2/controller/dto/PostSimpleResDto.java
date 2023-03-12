package spring_yd.prac_2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostSimpleResDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
