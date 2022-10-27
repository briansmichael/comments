/*
 *  Copyright (C) 2022 Starfire Aviation, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.starfireaviation.comments.service;

import com.starfireaviation.comments.model.Comment;
import com.starfireaviation.comments.model.CommentRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * CommentService.
 */
@Slf4j
public class CommentService {

    /**
     * CommentRepository.
     */
    private final CommentRepository commentRepository;

    /**
     * CommentService.
     *
     * @param cRepository CommentRepository
     */
    public CommentService(final CommentRepository cRepository) {
        commentRepository = cRepository;
    }

    /**
     * Creates a comment.
     *
     * @param comment Comment
     * @return Comment
     */
    public Comment store(final Comment comment) {
        if (comment == null) {
            return comment;
        }
        return get(commentRepository.save(comment).getId());
    }

    /**
     * Deletes a comment.
     *
     * @param id Long
     * @return Comment
     */
    public Comment delete(final long id) {
        final Comment comment = get(id);
        if (comment != null) {
            commentRepository.delete(comment);
        }
        return comment;
    }

    /**
     * Gets all comments.
     *
     * @return list of Comment
     */
    public List<Comment> getAll() {
        final List<Comment> comments = new ArrayList<>();
        final List<Comment> commentEntities = commentRepository.findAll();
        for (final Comment commentEntity : commentEntities) {
            comments.add(get(commentEntity.getId()));
        }
        return comments;
    }

    /**
     * Gets a comment.
     *
     * @param id Long
     * @return Comment
     */
    public Comment get(final long id) {
        return commentRepository.findById(id);
    }

    /**
     * Gets all comments for a given user.
     *
     * @param userId Long
     * @return list of Comment
     */
    public List<Comment> findCommentsByUserId(final Long userId) {
        final List<Comment> comments = new ArrayList<>();
        final List<Comment> commentEntities = commentRepository.findByUserId(userId);
        for (final Comment commentEntity : commentEntities) {
            comments.add(get(commentEntity.getId()));
        }
        return comments;
    }

}
