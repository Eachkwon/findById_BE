package com.example.week06.domain.community.service;

import com.example.week06.domain.community.dto.PostDetailsResponseDto;
import com.example.week06.domain.community.dto.PostRequestDto;
import com.example.week06.domain.community.dto.PostResponseDto;
import com.example.week06.domain.community.dto.ResponseMessageDto;
import com.example.week06.domain.community.entity.Comment;
import com.example.week06.domain.community.entity.Post;
import com.example.week06.domain.user.entity.User;
import com.example.week06.global.AttachmentRepository;
import com.example.week06.domain.community.repository.CommentRepository;
import com.example.week06.domain.community.repository.PostRepository;
import com.example.week06.domain.user.repository.UserRepository;
import com.example.week06.global.AttachmentService;
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

        String email = userDetails.getUsername();
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
    public ResponseMessageDto updatePost(PostRequestDto requestDto, Long postId, MultipartFile file) {

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

        User user = userDetails.getUser();

        PostDetailsResponseDto postDetailsResponseDto = PostDetailsResponseDto.builder()
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

        return postDetailsResponseDto;

    }

    //완료 처리(completed)
    @Transactional
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

}