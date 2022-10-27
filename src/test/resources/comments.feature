@Comments
Feature: Comments
  As a user
  I want to interact with comments
  So that I might provide feedback

  Scenario Outline: Create a new comment
    Given I am an authenticated user
    And I have a comment
    And The comment has text with <number> characters
    And The comment's component type is <componentType>
    When I submit the comment
    Then I should receive a success response

    Examples:
    | number | componentType      |
    | 255    | none               |
    | 1      | event              |
    | 25     | quiz               |
    | 52     | question           |
    | 250    | lesson             |
    | 205    | reference material |

  Scenario Outline: Create a comment without required data
    Given I am an authenticated user
    And I have a comment
    And The comment has text with <number> characters
    When I submit the comment
    Then I should receive an InvalidPayloadException

    Examples:
    | number |
    | 0      |
    | 256    |

  Scenario: Get a comment
    Given I am an authenticated user
    And A comment exists
    When I get the comment
    Then I should receive a success response
    And A comment should be received

  Scenario: Update an existing comment
    Given I am an authenticated user
    And A comment exists
    And The comment's text is modified to be 50 characters
    And The comment's componentType is modified to be event
    When I submit the comment for update
    Then I should receive a success response

  Scenario: Delete a comment
    Given I am an authenticated user
    And A comment exists
    When I delete the comment
    Then I should receive a success response
    And The comment should be removed

  Scenario Outline: Create a comment as an unauthenticated user
    Given I have a comment
    And The comment has text with <number> characters
    And The comment's component type is <componentType>
    When I submit the comment
    Then I should receive an unauthenticated response

    Examples:
    | number | componentType |
    | 15     | SLACK         |

  Scenario: Get a comment as an unauthenticated user
    Given A comment exists
    When I get the comment
    Then I should receive an unauthenticated response

  Scenario: Update an existing comment as an unauthenticated user
    Given A comment exists
    And The comment's text is modified to be 50 characters
    And The comment's componentType is modified to be event
    When I submit the comment for update
    Then I should receive an unauthenticated response

  Scenario: Delete a comment as an unauthenticated user
    Given A comment exists
    When I delete the comment
    Then I should receive an unauthenticated response
