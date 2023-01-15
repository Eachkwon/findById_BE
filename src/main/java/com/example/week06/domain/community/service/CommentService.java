package com.example.week06.domain.community.service;

import com.example.week06.domain.community.entity.Comment;
import com.example.week06.domain.community.entity.Post;
import com.example.week06.domain.user.entity.User;
import com.example.week06.domain.community.repository.CommentRepository;
import com.example.week06.domain.community.repository.PostRepository;
import com.example.week06.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createComment(CommentRequest commentRequest, String email, Long postId){
        User user = userRepository.findByEmail(email).get();
        Post post = postRepository.findById(postId).get();
        Comment comment = new Comment(commentRequest, user, post);
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
    public void putComment(Long commentId, CommentRequest commentRequest, String email){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        User writer = comment.getUser();


    }

}