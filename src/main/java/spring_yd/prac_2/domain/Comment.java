package spring_yd.prac_2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "COMMENTS")
public class Comment extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)      //대댓글
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)   //대댓글
    private List<Comment> child = new ArrayList<>();

    private String desc;


    protected Comment() {}
    public static Comment createComment(Post post, Member member, Comment parent, String desc) {
        Comment newComment = new Comment();
        newComment.assignPost(post);
        newComment.member = member;   //멤버에서 내가 쓴 댓글 가져오는 기능 없음
        newComment.parent = parent;
        if(parent != null){
            parent.getChild().add(newComment);  // 대댓글이라면 부모의 child에 추가
        }
        newComment.desc = desc;
        newComment.setCreatedAt(LocalDateTime.now());
        return newComment;
    }

    private void assignPost(Post post) {
        this.post = post;
        post.getCommentList().add(this);
    }
}
