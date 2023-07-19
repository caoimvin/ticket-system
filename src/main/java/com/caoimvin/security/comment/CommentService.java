package com.caoimvin.security.comment;

import com.caoimvin.security.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment add(Comment request) {
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setTicket(request.getTicket());
        return commentRepository.save(request);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("No comment found with id :" + id)));
    }

    @Override
    public void delete(Long id) {
        Optional<Comment> comment = findById(id);
        comment.ifPresent(theComment -> commentRepository.deleteById(theComment.getId()));
    }

    @Override
    public Comment update(Long id, Comment request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("No comment found with id: " + id));
        comment.setText(request.getText());
        comment.setTicket(request.getTicket());
        return commentRepository.save(comment);
    }
}
