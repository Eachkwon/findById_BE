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
import com.example.week06.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다.")
        );

        //코멘트 가져오기
        List<Comment> comments = commentRepository.findAllByPostOrderByCreatedAtDesc(post);
        List<CommentResponse> commentList = new ArrayList<>();
        for(Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse(comment);
            commentList.add(commentResponse);
        }

        PostResponse postResponse = new PostResponse(post,commentList);
        return postResponse;
    }

    // 게시글 작성
    public void createPost(User user, PostRequest postRequest) {
        Post post = new Post(postRequest, user);
        postRepository.save(post);
    }

    // 게시글 해결완료
    public boolean complete(Long postId, UserDetailsImpl userDetails) {
        Long post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")).getId();
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );
        Optional<Post> complete = postRepository.findByPostIdAndUser(post, user);
        if (!complete.isPresent()) {
            Post completed = new Post(post, user);
            postRepository.save(completed);
            return false;
        } else {
            postRepository.deleteByPostIdAndUser(post, user);
            return true;
        }
    }

    // 게시글 수정
    @Transactional
    public ResponseMessage updatePost(PostRequest requestDto, Long postId, MultipartFile file) {

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(true);
        responseMessage.setMessage("게시글 수정 성공");
        return responseMessage;
    }


    //게시글 삭제
    public ResponseMessage deletePost(Long postId) {
        postRepository.deleteById(postId);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(true);
        responseMessage.setMessage("게시글 삭제 성공");
        return responseMessage;
    }

}