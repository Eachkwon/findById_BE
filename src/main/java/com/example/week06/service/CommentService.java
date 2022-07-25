package com.example.week06.service;

import com.example.week06.Dto.CommentRequestDto;
import com.example.week06.model.Comment;
import com.example.week06.model.Post;
import com.example.week06.model.User;
import com.example.week06.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createComment(CommentRequestDto commentRequestDto, Long userId , Long postId){
        String comments = commentRequestDto.getComment();
        User user = userRepository.findById(userId).get;
        Post post = postRepository.findById(postId).get;
        Comment comment = new Comment(comments, user, post);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId){
        User writerId = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")).getUser_id();
        if (Objects.equals(writerId, userId)) {
            commentRepository.deleteById(commentId);
        }
    }

    @Transactional
    public void putComment(Long id, CommentRequestDto commentRequestDto, Long userId){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        User user = userRepository.findById(userId).get;
        if(Objects.equals(comment, userId)) {
            comment.update(commentRequestDto);
        }
    }

}
