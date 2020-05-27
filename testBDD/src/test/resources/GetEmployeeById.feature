Feature: Access particular employee data using rest api /employee and the employee id

  Scenario: Client makes call to GET /users/{id}
    When the client calls /users/10
    Then the client receives status code of 200
    And the client receives user first name 'Byron'
    