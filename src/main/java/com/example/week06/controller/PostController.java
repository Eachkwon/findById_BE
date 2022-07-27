package com.example.week06.controller;

import com.example.week06.dto.PostDetailsResponseDto;
import com.example.week06.dto.PostRequestDto;
import com.example.week06.dto.PostResponseDto;
import com.example.week06.security.UserDetailsImpl;
import com.example.week06.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    //게시판 List 불러오기
    @GetMapping("api/posts")
    public List<PostResponseDto> getPosts() {

        return postService.getPosts();

    }

    // 게시글 작성
    @PostMapping("api/posts")
    public void createPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart(value = "postRequestDto") PostRequestDto requestDto,
            @RequestPart(value = "file") MultipartFile file
    ) throws Exception {

        /* 파일을 업로드 하지 않았을 경우 처리 */
        if (file.isEmpty()) {
        }

         postService.createPost(userDetails, requestDto, file);
    }


    @GetMapping("api/posts/{postId}")
    public PostDetailsResponseDto getPost(@PathVariable Long postId,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.getPost(postId, userDetails);

    }


    @PostMapping("/api/posts/{postId}/completed")
    public void complete(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long LongId = userDetails.getUser().getId();
        postService.complete(postId, LongId);
    }

}