package com.example.week06.service;

import com.example.week06.dto.CommentRequestDto;
import com.example.week06.model.Comment;
import com.example.week06.model.Post;
import com.example.week06.model.User;
import com.example.week06.repository.CommentRepository;
import com.example.week06.repository.PostRepository;
import com.example.week06.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createComment(CommentRequestDto commentRequestDto, String email, Long postId){
        User user = userRepository.findByEmail(email).get();
        Post post = postRepository.findById(postId).get();
        Comment comment = new Comment(commentRequestDto, user, post);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        commentRepository.findByIdAndUser(commentId, user).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void putComment(Long commentId, CommentRequestDto commentRequestDto, String email){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        User writer = comment.getUser();


    }

}