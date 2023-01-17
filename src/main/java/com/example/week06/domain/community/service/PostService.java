package com.example.week06.domain.community.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시판 목록 조회
    public List<PostListResponse> getPosts() {

        List<Post> posts = postRepository.findAll();
        List<PostListResponse> postList = new ArrayList<>();

        for (Post post : posts) {
            PostListResponse postListResponse = PostListResponse.builder()
                    .postId(post.getId())
                    .title(post.getTitle())
                    .district(post.getDistrict())
                    .build();

            postList.add(postListResponse);
        }

        return postList;
    }

    //게시글 조회
    public PostResponse getPost(Long postId, UserDetailsImpl userDetails) {

        //게시글 가져오기
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시물이 존재하지 않습니다.")
        );

        //코멘트 가져오기
        List<Comment> comments = commentRepository.findByPostOrderByCreatedAtDesc(post);

        User user = userDetails.getUser();

        PostResponse postResponse = PostResponse.builder()
                .postId(postId)
                .title(post.getTitle())
                .imageURL(imageURL)
                .nickname(user.getNickname())
                .district(post.getDistrict())
                .content(post.getContent())
                .gadaoda(post.getGadaoda())
                .completed(post.getCompleted())
                .comments(comments)
                .build();

        return postResponse;

    }

    // 게시글 작성
    public void createPost(UserDetailsImpl userDetails, PostRequest requestDto, MultipartFile file) throws IOException {

        //게시글 등록
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        String gadaoda = requestDto.getGadaoda();
        String district = requestDto.getDistrict();
        Post post = new Post(title, content, gadaoda, district, user);

        postRepository.save(post);

        //이미지 등록
        attachmentService.savePostImage(post, file);

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