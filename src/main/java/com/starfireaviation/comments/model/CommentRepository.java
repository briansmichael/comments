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

package com.starfireaviation.comments.model;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * CommentRepository.
 *
 * @author brianmichael
 */
public interface CommentRepository extends Repository<Comment, Long> {

    /**
     * Deletes a comment.
     *
     * @param comment Comment
     */
    void delete(Comment comment);

    /**
     * Gets all comments.
     *
     * @return list of Comment
     */
    List<Comment> findAll();

    /**
     * Gets a comment.
     *
     * @param id Long
     * @return Quiz
     */
    Comment findById(long id);

    /**
     * Gets all comments for a given user.
     *
     * @param id Long
     * @return list of Comment
     */
    List<Comment> findByUserId(long id);

    /**
     * Saves a comment.
     *
     * @param comment Comment
     * @return Comment
     */
    Comment save(Comment comment);
}
