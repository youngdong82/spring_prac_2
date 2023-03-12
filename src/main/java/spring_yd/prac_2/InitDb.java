package spring_yd.prac_2;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring_yd.prac_2.domain.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            //team
            Team team1 = new Team();
            team1.setName("LA Lakers");
            Team team2 = new Team();
            team2.setName("men city");
            em.persist(team1);
            em.persist(team2);

            //member
            Member member1 = Member.createMember("Kim", MemberType.ADMIN);
            Member member2 = Member.createMember("Young", MemberType.PLANE);
            member1.assignTeam(team1);
            member2.assignTeam(team2);
            em.persist(member1);
            em.persist(member2);

            //category
            Category category1 = new Category();
            category1.setName("soccer");
            category1.setDesc("soccer");
            Category category2 = new Category();
            category1.setName("baseball");
            category1.setDesc("baseball");
            em.persist(category1);
            em.persist(category2);

            //post
            Post post1 = Post.createPost(member1, category1,"member1's first post", "hellllo");
            Post post2 = Post.createPost(member1, category2,"member1's second post", "hellllo");
            Post post3 = Post.createPost(member2, category1,"member2's first post", "hellllo");
            em.persist(post1);
            em.persist(post2);
            em.persist(post3);

            //comment
            Comment comment1 = Comment.createComment(post1, member1, null, "first comment");
            Comment comment2 = Comment.createComment(post1, member1, comment1, "inner comment");
            Comment comment3 = Comment.createComment(post2, member1, null, "first comment");
            Comment comment4 = Comment.createComment(post2, member2, comment3, "inner comment");
            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);
            em.persist(comment4);
        }


    }
}
