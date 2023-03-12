package spring_yd.prac_2.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_yd.prac_2.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class PostRepository {
    private final EntityManager em;

    //게시하기
    public void create(Post post){
        em.persist(post);
    }
    //전체조회
    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }
    public List<Post> findAllV2(int offset, int limit){
        String fetchQuery = "select distinct p from Post p" +
                            " join fetch p.member m" +
                            " join fetch p.category ca" +
                            " left join fetch p.commentList c" +    //inner join이면 댓글이 없는 포스트 제외됨
                            " left join fetch c.member cm";         //comment를 쓴 member

        return em.createQuery(fetchQuery, Post.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    //상세조회
    public Post findById(Long postId){
        return em.find(Post.class, postId);
    }

    //삭제하기
    public void delete(Post post){
        em.remove(post);
    }
}
