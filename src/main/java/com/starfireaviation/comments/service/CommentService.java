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

import com.starfireaviation.comments.model.CommentEntity;
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
    public CommentEntity store(final CommentEntity comment) {
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
    public CommentEntity delete(final long id) {
        final CommentEntity comment = get(id);
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
    public List<CommentEntity> getAll() {
        final List<CommentEntity> comments = new ArrayList<>();
        final List<CommentEntity> commentEntities = commentRepository.findAll();
        for (final CommentEntity commentEntity : commentEntities) {
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
    public CommentEntity get(final long id) {
        return commentRepository.findById(id);
    }

    /**
     * Gets all comments for a given user.
     *
     * @param userId Long
     * @return list of Comment
     */
    public List<CommentEntity> findCommentsByUserId(final Long userId) {
        final List<CommentEntity> comments = new ArrayList<>();
        final List<CommentEntity> commentEntities = commentRepository.findByUserId(userId);
        for (final CommentEntity commentEntity : commentEntities) {
            comments.add(get(commentEntity.getId()));
        }
        return comments;
    }

}
