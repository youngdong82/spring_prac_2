package spring_yd.prac_2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_yd.prac_2.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    //게시글에 댓글 쓰기
    public void create (Comment comment){
        em.persist(comment);
    }

    //id로 찾기
    public Comment findById(Long commentId){
        return em.find(Comment.class,commentId);
    }

    //자식 댓글 보기
    public List<Comment> findChildAll(Long commentId){
        String query = "select c from Comment c where c.parent.id = :commentId";
//        child가 있는데;;
        return em.createQuery(query, Comment.class)
                .setParameter("commentId", commentId)
                .getResultList();
    }

    //게시글 댓글 삭제
    public void delete(Comment comment){
        em.remove(comment);
    }
}
