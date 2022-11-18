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

package com.starfireaviation.comments;

import com.starfireaviation.comments.model.CommentEntity;
import com.starfireaviation.common.CommonConstants;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CommentsSteps {

    /**
     * URL.
     */
    protected static final String URL = "http://localhost:8080";

    /**
     * ORGANIZATION.
     */
    protected static final String ORGANIZATION = "TEST_ORG";

    /**
     * RestTemplate.
     */
    protected RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new RestTemplateResponseErrorHandler()).build();

    @Autowired
    protected TestContext testContext;

    @Before
    public void init() {
        testContext.reset();
    }

    @Given("^I have a comment")
    public void iHaveAComment() throws Throwable {
        testContext.setComment(new CommentEntity());
    }

    @Given("^A comment exists")
    public void aCommentExists() throws Throwable {
        // TODO
    }

    @And("^The comment's text is modified to be (.*) characters$")
    public void theCommentTextIsModifiedToBeXCharacters(final int characterCount) {
        // TODO
    }
  
    @And("^The comment has text with (.*) characters$")
    public void theCommentHasTextWithCharacters(final int characterCount) {
        // TODO
    }

    @And("^The comment's component type is (.*)$")
    public void theCommentComponentTypeIs(final String componentType) {
        // TODO
    }

    @And("^The comment's componentType is modified to be (.*)$")
    public void theCommentHasTextWithCharacters(final String componentType) {
        // TODO
    }

    @When("^I submit the comment$")
    public void iAddTheComment() throws Throwable {
        log.info("I submit the comment");
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (testContext.getOrganization() != null) {
            headers.add(CommonConstants.ORGANIZATION_HEADER_KEY, testContext.getOrganization());
        }
        if (testContext.getCorrelationId() != null) {
            headers.add(CommonConstants.CORRELATION_ID_HEADER_KEY, testContext.getCorrelationId());
        }
        //final HttpEntity<Question> httpEntity = new HttpEntity<>(testContext.getQuestion(), headers);
        //testContext.setResponse(restTemplate.postForEntity(URL, httpEntity, Void.class));
    }

    @When("^I get the comment")
    public void iGetTheComment() throws Throwable {
        // TODO
    }

    @When("^I delete the comment")
    public void iDeleteTheComment() throws Throwable {
        // TODO
    }

    @When("^I submit the comment for update")
    public void iSubmitTheCommentForUpdate() throws Throwable {
        // TODO
    }

    @Then("The comment should be removed")
    public void theCommentShouldBeRemoved() throws Throwable {
        // TODO
    }

    @And("A comment should be received")
    public void aCommentShouldBeReceived() throws Throwable {
        // TODO
    }
}
