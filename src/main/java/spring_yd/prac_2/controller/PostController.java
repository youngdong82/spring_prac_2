package spring_yd.prac_2.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring_yd.prac_2.controller.dto.*;
import spring_yd.prac_2.controller.dto.mapper.SuccessResDto;
import spring_yd.prac_2.domain.Category;
import spring_yd.prac_2.domain.Comment;
import spring_yd.prac_2.domain.Member;
import spring_yd.prac_2.domain.Post;
import spring_yd.prac_2.exceptions.BadRequestException;
import spring_yd.prac_2.repository.CategoryRepository;
import spring_yd.prac_2.service.MemberService;
import spring_yd.prac_2.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;
    private final CategoryRepository categoryRepository;

    //게시하기
    @PostMapping("/api/posts")
    public SuccessResDto<PostResDto> posting(
            @RequestBody PostReqDto req
    ){
        Member postOwner = memberService.findMemberById(req.getMemberId());
        Category category = categoryRepository.getCategoryById(req.getCategoryId());
        Post newPost = postService.createPost(postOwner, category, req.getTitle(), req.getDesc());

        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "포스트 생성 성공",
                convertToPostResDto(newPost, postOwner,category)
        );
    }

    //전체조회
    @GetMapping("/api/posts")
    public List<PostResDto> getAllPosts(){
        List<Post> all = postService.findPostsAll();
        List<PostResDto> collect = all.stream()
                .map(p -> convertToPostResDto(p, p.getMember(), p.getCategory()))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * fetch join + pagination + data mapping
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("/api/posts2")
    public SuccessResDto<List<PostResDto>> getAllPostsV2(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "2") int limit
    ){
        List<Post> all = postService.findPostsAllV2(offset, limit);
        List<PostResDto> collect = all.stream()
                .map(p -> convertToPostResDto(p, p.getMember(), p.getCategory()))
                .collect(Collectors.toList());
        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "post 조회 성공",
                collect
        );
    }

    //상세조회
    @GetMapping("/api/posts/{id}")
    public SuccessResDto<PostResDto>  getPostById(
            @PathVariable("id") Long postId
    ){
        Post findPost = postService.findPostById(postId);
        //custom exception 처리 (custom 이 아니면 굳이 할 필요가 없음)
        if(findPost == null){
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "please check postId from getPostById");
        }
        Member findMember = findPost.getMember();
        Category findCategory = findPost.getCategory();

        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "post 상세조회 성공",
                convertToPostResDto(findPost,findMember,findCategory)
        );
    }

    //삭제하기
    @DeleteMapping("/api/posts/{postId}")
    public SuccessResDto removePost(@PathVariable("postId") Long postId){

        postService.deletePostById(postId);
        return new SuccessResDto(
                HttpStatus.OK.value(),
                "포스트 삭제 성공",
                null
        );
    }

    //======공통 함수 추출======
    private static List<CommentSimpleResDto> getCommentSimpleResDtoList(List<Comment> commentList) {
        return commentList.stream()
                .filter(c -> c.getParent() == null) //효율 극악이다 진짜;;
                .map(c ->
                        new CommentSimpleResDto(
                        c.getId(),
                        new MemberSimpleResDto(
                                c.getMember().getId(),
                                c.getMember().getName()),
                        c.getDesc(),
                        c.getCreatedAt(),
                        c.getUpdatedAt()
                )).collect(Collectors.toList());
    }
    private static PostResDto convertToPostResDto(Post post, Member postOwner, Category category){
        return new PostResDto(
                post.getId(),
                new MemberSimpleResDto(postOwner.getId(),postOwner.getName()),
                new CategorySimpleResDto(category.getId(),category.getName()),
                post.getTitle(),
                post.getDesc(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                getCommentSimpleResDtoList(post.getCommentList())
        );
    }
}
