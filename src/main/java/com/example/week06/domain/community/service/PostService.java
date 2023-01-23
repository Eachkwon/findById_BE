package com.example.week06.domain.community.service;

import com.example.week06.domain.community.dto.CommentResponse;
import com.example.week06.domain.community.dto.PostResponse;
import com.example.week06.domain.community.dto.PostRequest;
import com.example.week06.domain.community.dto.PostListResponse;
import com.example.week06.domain.community.entity.Comment;
import com.example.week06.domain.community.entity.Post;
import com.example.week06.domain.user.entity.User;
import com.example.week06.domain.community.repository.CommentRepository;
import com.example.week06.domain.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시판 목록 조회
    public List<PostListResponse> getPostList(String lost_and_found) {

        List<Post> posts = postRepository.findAllByLost_and_foundOrderByCreatedAtDesc(lost_and_found);
        List<PostListResponse> postList = new ArrayList<>();

        for (Post post : posts) {
            PostListResponse postListResponse = new PostListResponse(post);
            postList.add(postListResponse);
        }

        return postList;
    }

    //게시글 조회
    public PostResponse getPost(Long postId) {

        //게시글 가져오기
        Post post = findPostByPostId(postId);

        //코멘트 가져오기
        List<Comment> comments = commentRepository.findAllByPostOrderByCreatedAtDesc(post);
        List<CommentResponse> commentList = new ArrayList<>();
        for(Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse(comment);
            commentList.add(commentResponse);
        }

        return new PostResponse(post,commentList);
    }

    // 게시글 작성
    public String createPost(User user, PostRequest postRequest) {
        Post post = new Post(postRequest, user);
        postRepository.save(post);

        return "게시글 작성을 성공하였습니다.";
    }

    // 게시글 해결완료
    public String complete(Long postId, User user) {
        Post post = findPostByPostId(postId);

        checkAuthority(post.getUser().getId(), user.getId());

        if(post.getCompleted().equals("uncompleted")) {
            post.updateCompleted("completed");
        } else {
            post.updateCompleted("uncompleted");
        }

        postRepository.save(post);

        return "게시글 해결완료를 성공하였습니다.";
    }

    // 게시글 수정
    @Transactional
    public String updatePost(PostRequest postRequest, Long postId, User user) {

        Post post = findPostByPostId(postId);

        checkAuthority(post.getUser().getId(), user.getId());

        post.updatePost(postRequest);
        postRepository.save(post);

        return "게시글 수정에 성공하였습니다.";
    }

    //게시글 삭제
    public String deletePost(Long postId, User user) {

        Post post = findPostByPostId(postId);

        checkAuthority(post.getUser().getId(), user.getId());

        postRepository.delete(post);

        return "게시글 삭제에 성공하였습니다.";
    }

    // 게시글 반환
    private Post findPostByPostId(Long postId) {

        return postRepository.findById(postId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시물이 존재하지 않습니다.")
        );
    }

    // 게시글 권한 확인
    private void checkAuthority(Long writerId, Long userId) {

        if(!writerId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시물에 대한 권한이 없습니다.");
        }
    }

}