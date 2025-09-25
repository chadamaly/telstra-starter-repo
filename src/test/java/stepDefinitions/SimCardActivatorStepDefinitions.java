package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.Sim;
import au.com.telstra.simcardactivator.model.ActivatedSim;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {

    private TestRestTemplate restTemplate  = new TestRestTemplate();

    private Sim simRequest;
    private ResponseEntity<ActivatedSim> postResponse;
    private ResponseEntity<ActivatedSim> getResponse;

    @Given("I have a SIM card with ICCID {string} and email {string}")
    public void i_have_a_sim_card_with_iccid_and_email(String iccid, String email) {
        simRequest = new Sim(iccid, email);
    }

    @When("I send an activation request")
    public void i_send_an_activation_request() {
        postResponse = restTemplate.postForEntity(
                "http://localhost:8081/sim/activate",
                simRequest,
                ActivatedSim.class
        );
    }

    @Then("the SIM card with id {long} should be active")
    public void the_sim_card_should_be_active(Long id) {
        getResponse = restTemplate.getForEntity(
                "http://localhost:8081/sim?simCardId=" + id,
                ActivatedSim.class
        );
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().isActive()).isTrue();
    }

    @Then("the SIM card with id {long} should not be active")
    public void the_sim_card_should_not_be_active(Long id) {
        getResponse = restTemplate.getForEntity(
                "http://localhost:8081/sim?simCardId=" + id,
                ActivatedSim.class
        );
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().isActive()).isFalse();
    }

}