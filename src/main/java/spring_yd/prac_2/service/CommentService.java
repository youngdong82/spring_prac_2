package spring_yd.prac_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_yd.prac_2.domain.Comment;
import spring_yd.prac_2.domain.Member;
import spring_yd.prac_2.domain.Post;
import spring_yd.prac_2.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    //댓글 쓰기
    @Transactional
    public Comment createComment(Post post, Member member, Comment parent, String desc){
        Comment newComment = Comment.createComment(post, member, parent, desc);
        commentRepository.create(newComment);
        return newComment;
    }

    //자식 댓글 조회
    public List<Comment> findCommentsAll(Long commentId){
        return commentRepository.findChildAll(commentId);
    }

    //댓글 삭제
    @Transactional
    public void removeComment(Long commentId){
        Comment findComment = commentRepository.findById(commentId);
        commentRepository.delete(findComment);
    }
}
