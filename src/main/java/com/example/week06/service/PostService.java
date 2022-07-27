package com.example.week06.service;

import com.example.week06.dto.PostDetailsResponseDto;
import com.example.week06.dto.PostRequestDto;
import com.example.week06.dto.PostResponseDto;
import com.example.week06.dto.ResponseMessageDto;
import com.example.week06.model.Comment;
import com.example.week06.model.Post;
import com.example.week06.model.User;
import com.example.week06.repository.AttachmentRepository;
import com.example.week06.repository.CommentRepository;
import com.example.week06.repository.PostRepository;
import com.example.week06.repository.UserRepository;
import com.example.week06.security.UserDetailsImpl;
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
@Transactional(readOnly = true)
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AttachmentRepository attachmentRepository;
    private final CommentRepository commentRepository;
    private final AttachmentService attachmentService;


    //전체 게시글 조회
    @Transactional
    public List<PostResponseDto> getPosts() {

        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postList = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .postId(post.getId())
                    .title(post.getTitle())
                    .district(post.getDistrict())
                    .imageURL(attachmentRepository.findByPost(post).getImageURL())
                    .build();

            postList.add(postResponseDto);
        }

        return postList;
    }

    @Transactional
    public void createPost(UserDetailsImpl userDetails, PostRequestDto requestDto, MultipartFile file) throws IOException {

        String email = userDetails.getUser().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NullPointerException("유저 정보를 찾을 수 없습니다.")
        );

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

    //게시글 수정
    @Transactional
    public ResponseMessageDto updatePost(PostRequestDto requestDto, Long postId, MultipartFile file) throws Exception {

        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        responseMessageDto.setStatus(true);
        responseMessageDto.setMessage("게시글 수정 성공");
        return responseMessageDto;
    }


    //게시글 삭제
    public ResponseMessageDto deletePost(Long postId) {
        postRepository.deleteById(postId);
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        responseMessageDto.setStatus(true);
        responseMessageDto.setMessage("게시글 삭제 성공");
        return responseMessageDto;
    }

    //게시글 상세 페이지 조회
    @Transactional
    public PostDetailsResponseDto getPost(Long postId, UserDetailsImpl userDetails) {

        //게시글 가져오기
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시물이 존재하지 않습니다.")
        );

        //이미지 가져오기
        String imageURL = attachmentRepository.findByPost(post).getImageURL();

        //코멘트 가져오기
        List<Comment> comments = commentRepository.findByPostOrderByCreatedAtDesc(post);

        PostDetailsResponseDto postDetailsResponseDto = PostDetailsResponseDto.builder()
                .postId(postId)
                .title(post.getTitle())
                .imageURL(imageURL)
                .nickname(userDetails.getNickname())
                .district(post.getDistrict())
                .content(post.getContent())
                .gadaoda(post.getGadaoda())
                .completed(post.getCompleted())
                .comments(comments)
                .build();

        return postDetailsResponseDto;

    }

    //완료 처리(completed)
    @Transactional
    public boolean complete(Long postId, Long userId) {
        Long post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")).getId();
        User user = userRepository.getById(userId);
        Optional<Post> complete = postRepository.findByPostIdAndUserId(post, user.getId());
        if (!complete.isPresent()) {
            Post completed = new Post(post, user);
            postRepository.save(completed);
            return false;
        } else {
            postRepository.deleteByPostIdAndUserId(post, user.getId());
            return true;
        }
    }

}