package spring_yd.prac_2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_yd.prac_2.domain.Category;
import spring_yd.prac_2.domain.Member;
import spring_yd.prac_2.domain.Post;
import spring_yd.prac_2.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    //게시하기
    @Transactional
    public Post createPost(Member member, Category category,String title,String desc ){
        Post newPost = Post.createPost(member, category, title, desc);
        postRepository.create(newPost);
        return newPost;
    }

    //전체조회
    public List<Post> findPostsAll(){
        return postRepository.findAll();
    }

    //전체조회V2(fetch join)
    public List<Post> findPostsAllV2(int offset, int limit){
        return postRepository.findAllV2(offset,limit);
    }

    //상세조회
    public Post findPostById(Long postId){
        return postRepository.findById(postId);
    }

    //삭제하기
    @Transactional
    public void deletePostById(Long postId){
        Post findPost = findPostById(postId);
        postRepository.delete(findPost);
    }

}
