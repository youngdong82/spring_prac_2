package spring_yd.prac_2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="POSTS")
public class Post extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) //post와 생멍주기를 함께하는 comment, post가 없어지면 댓글도 사라진다.
    private List<Comment> commentList = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public List<Comment> getCommentList() {
        return commentList;
    }
    private String title;
    private String desc;

    protected Post() {}
    public static Post createPost(Member member, Category category, String title, String desc) {
        Post post = new Post();
        post.assignWriter(member);
        post.assignCategory(category);
        post.title = title;
        post.desc = desc;
        post.setCreatedAt(LocalDateTime.now());
        return post;
    }

    //연관관계 편의 메소드
    private void assignCategory(Category category) {
        this.category = category;
        category.getPosts().add(this);
    }
    private void assignWriter(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

}
