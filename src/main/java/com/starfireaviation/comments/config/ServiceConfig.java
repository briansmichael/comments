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

package com.starfireaviation.comments.config;

import com.starfireaviation.comments.model.CommentRepository;
import com.starfireaviation.comments.service.CommentService;
import com.starfireaviation.comments.service.DataService;
import com.starfireaviation.comments.validation.CommentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ServiceConfig.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({ ApplicationProperties.class })
public class ServiceConfig {

    /**
     * CommentService.
     *
     * @param cRepository CommentRepository
     * @return CommentService
     */
    @Bean
    public CommentService commentService(final CommentRepository cRepository) {
        return new CommentService(cRepository);
    }

    /**
     * CommentValidator.
     *
     * @param dService DataService
     * @return CommentValidator
     */
    @Bean
    public CommentValidator commentValidator(final DataService dService) {
        return new CommentValidator(dService);
    }

}
