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

package com.starfireaviation.comments.controller;

import com.starfireaviation.comments.exception.AccessDeniedException;
import com.starfireaviation.comments.exception.InvalidPayloadException;
import com.starfireaviation.comments.exception.ResourceNotFoundException;
import com.starfireaviation.comments.model.Comment;
import com.starfireaviation.comments.service.CommentService;
import com.starfireaviation.comments.validation.CommentValidator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * CommentController.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping({
        "/comments"
})
public class CommentController {

    /**
     * CommentService.
     */
    private final CommentService commentService;

    /**
     * CommentValidator.
     */
    private final CommentValidator commentValidator;

    /**
     * CommentController.
     *
     * @param cService   CommentService
     * @param cValidator CommentValidator
     */
    public CommentController(final CommentService cService,
                             final CommentValidator cValidator) {
        commentService = cService;
        commentValidator = cValidator;
    }

    /**
     * Creates a comment.
     *
     * @param comment   Comment
     * @param principal Principal
     * @return Comment
     * @throws ResourceNotFoundException when no user is found
     * @throws AccessDeniedException     when user doesn't have permission to
     *                                   perform operation
     * @throws InvalidPayloadException   when invalid data is provided
     */
    @PostMapping
    public Comment post(@RequestBody final Comment comment, final Principal principal)
            throws InvalidPayloadException, ResourceNotFoundException, AccessDeniedException {
        commentValidator.validate(comment);
        commentValidator.accessAnyAuthenticated(principal);
        return commentService.store(comment);
    }

    /**
     * Gets a comment.
     *
     * @param commentId Long
     * @param principal Principal
     * @return Comment
     * @throws ResourceNotFoundException when comment is not found
     * @throws AccessDeniedException     when user doesn't have permission to
     *                                   perform operation
     */
    @GetMapping(path = {
            "/{commentId}"
    })
    public Comment get(@PathVariable("commentId") final long commentId, final Principal principal)
            throws ResourceNotFoundException, AccessDeniedException {
        commentValidator.accessAnyAuthenticated(principal);
        return commentService.get(commentId);
    }

    /**
     * Updates a comment.
     *
     * @param comment   Comment
     * @param principal Principal
     * @return Comment
     * @throws AccessDeniedException     when user doesn't have permission to
     *                                   perform operation
     * @throws ResourceNotFoundException when no comment is found
     * @throws InvalidPayloadException   when invalid data is provided
     */
    @PutMapping
    public Comment put(@RequestBody final Comment comment, final Principal principal)
            throws ResourceNotFoundException, AccessDeniedException, InvalidPayloadException {
        commentValidator.validate(comment);
        commentValidator.accessAdmin(principal);
        return commentService.store(comment);
    }

    /**
     * Deletes a comment.
     *
     * @param commentId Long
     * @param principal Principal
     * @return Comment
     * @throws ResourceNotFoundException when reference material is not found
     * @throws AccessDeniedException     when user doesn't have permission to
     *                                   perform operation
     */
    @DeleteMapping(path = {
            "/{commentId}"
    })
    public Comment delete(
            @PathVariable("commentId") final long commentId,
            final Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        commentValidator.accessAdmin(principal);
        return commentService.delete(commentId);
    }

    /**
     * Get all comments.
     *
     * @param principal Principal
     * @return list of Comment
     * @throws ResourceNotFoundException when reference material is not found
     * @throws AccessDeniedException     when user doesn't have permission to
     *                                   perform operation
     */
    @GetMapping
    public List<Comment> list(final Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        commentValidator.accessAnyAuthenticated(principal);
        return commentService.getAll();
    }

    /**
     * Get all comments from a user.
     *
     * @param userId    User ID
     * @param principal Principal
     * @return list of Comment
     * @throws ResourceNotFoundException when reference material is not found
     * @throws AccessDeniedException     when user doesn't have permission to
     *                                   perform operation
     */
    @GetMapping({
            "/{userId}"
    })
    public List<Comment> list(@PathVariable("userId") final long userId, final Principal principal)
            throws ResourceNotFoundException, AccessDeniedException {
        commentValidator.accessAnyAuthenticated(principal);
        return commentService.findCommentsByUserId(userId);
    }
}
