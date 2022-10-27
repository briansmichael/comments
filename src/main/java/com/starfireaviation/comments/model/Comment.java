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

import com.starfireaviation.model.CommonConstants;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Comment.
 */
@Data
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {

    /**
     * Default SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Created At.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt = new Date();

    /**
     * Updated At.
     */
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt = new Date();

    /**
     * Text.
     */
    @Column(name = "text", nullable = false, length = CommonConstants.TWO_HUNDRED_FIFTY_FIVE)
    private String text;

    /**
     * User ID.
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * Reference ID.
     */
    @Column(name = "reference_id", nullable = true)
    private Long referenceId;

    /**
     * ComponentType.
     */
    @Column(name = "type", nullable = false, length = CommonConstants.ONE_HUNDRED)
    @Enumerated(EnumType.STRING)
    private ComponentType componentType;

}