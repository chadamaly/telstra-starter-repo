package au.com.telstra.simcardactivator.controller;
import au.com.telstra.simcardactivator.dto.ActuatorResponse;
import au.com.telstra.simcardactivator.dto.Sim;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/sim")
public class SimActivationController {
    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody Sim request) {
        RestTemplate restTemplate = new RestTemplate();
        String actuatorUrl = "http://localhost:8444/actuate";
        ActuatorResponse response = restTemplate.postForObject(
                actuatorUrl,
                new Sim(request.getIccid(), null),
                ActuatorResponse.class
        );

        return ResponseEntity.ok(response.toString());

    }

}
