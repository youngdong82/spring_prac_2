package spring_yd.prac_2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring_yd.prac_2.controller.dto.CommentReqDto;
import spring_yd.prac_2.controller.dto.CommentSimpleResDto;
import spring_yd.prac_2.controller.dto.MemberSimpleResDto;
import spring_yd.prac_2.controller.dto.mapper.SuccessResDto;
import spring_yd.prac_2.domain.Comment;
import spring_yd.prac_2.domain.Member;
import spring_yd.prac_2.domain.Post;
import spring_yd.prac_2.repository.CommentRepository;
import spring_yd.prac_2.repository.MemberRepository;
import spring_yd.prac_2.repository.PostRepository;
import spring_yd.prac_2.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    //댓글 쓰기
    @PostMapping("/api/comment")
    public SuccessResDto<CommentSimpleResDto> commenting(
            @RequestBody CommentReqDto req
            ){
        Post findPost = postRepository.findById(req.getPostId());
        Member findMember = memberRepository.findById(req.getMemberId());
        Comment newComment;
        //부모여부에 따른 분기 => service로 옮기자 => 뭐가 맞는거지...? 이게 비즈니스 로직인가..?
        if(req.getParentId() == null){
            newComment = commentService.createComment(findPost, findMember, null, req.getDesc());
        }else{
            Comment findParentComment = commentRepository.findById(req.getParentId());
            newComment = commentService.createComment(findPost, findMember, findParentComment, req.getDesc());
        }
        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "댓글 생성 성공",
                convertToCommentSimpleResDto(newComment)
        );
    }



    //댓글 상세보기(자식 댓글 보기) => 이걸로 대댓글 구현해야하는디ㅠ 오 성공잼!!!!
    @GetMapping("/api/comment/{commentId}")
    public SuccessResDto<List<CommentSimpleResDto>> getChildComments(
            @PathVariable("commentId") Long commentId
    ){
        List<Comment> all = commentService.findCommentsAll(commentId);
        List<CommentSimpleResDto> collect = all.stream()
                .map(c -> convertToCommentSimpleResDto(c)).collect(Collectors.toList());
        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "댓글 상세보기 성공",
                collect
        );
    }


    //댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public SuccessResDto removeComment(
            @PathVariable("commentId") Long commentId
    ){
        commentService.removeComment(commentId);
        return new SuccessResDto(
                HttpStatus.OK.value(),
                "댓글 삭제 성공",
                null
        );
    }

    //======공통 함수 추출======
    private static CommentSimpleResDto convertToCommentSimpleResDto(Comment newComment) {
        return new CommentSimpleResDto(
                newComment.getId(),
                new MemberSimpleResDto(newComment.getMember().getId(), newComment.getMember().getName()),
                newComment.getDesc(),
                newComment.getCreatedAt(),
                newComment.getUpdatedAt()
        );
    }
}
