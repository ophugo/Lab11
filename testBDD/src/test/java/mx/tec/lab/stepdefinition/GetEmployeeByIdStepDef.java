package mx.tec.lab.stepdefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetEmployeeByIdStepDef {
	
	TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<String> response = null;

    @When("the client calls \\/users\\/{int}")
    public void the_client_calls_users(int userId) {
    	headers.add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        response = restTemplate.exchange("https://reqres.in/api/users/" + userId,
                HttpMethod.GET, entity, String.class);
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    @And("the client receives user first name {string}")
    public void the_client_receives_user_first_name_of(String userFirstname) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        assertEquals(userFirstname, root.path("data").path("first_name").asText());
    }

}
