package com.caoimvin.security.comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {

    List<Comment> getAllComments();

    Comment add(Comment request);

    Optional<Comment> findById(Long id);

    void delete(Long id);

    Comment update(Long id, Comment request);
}
