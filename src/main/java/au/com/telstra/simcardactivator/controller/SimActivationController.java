package au.com.telstra.simcardactivator.controller;
import au.com.telstra.simcardactivator.dto.ActuatorResponse;
import au.com.telstra.simcardactivator.dto.Sim;
import au.com.telstra.simcardactivator.model.ActivatedSim;
import au.com.telstra.simcardactivator.repository.ActivatedSimRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@RestController
@RequestMapping("/sim")
public class SimActivationController {

    private final ActivatedSimRepository activatedSimRepository;

    public SimActivationController(ActivatedSimRepository activateSimRepository) {
        this.activatedSimRepository = activateSimRepository;
    }

    @PostMapping("/activate")
    public ResponseEntity<ActivatedSim> activateSim(@RequestBody Sim request) {
        RestTemplate restTemplate = new RestTemplate();
        String actuatorUrl = "http://localhost:8444/actuate";

        ActuatorResponse response = restTemplate.postForObject(
                actuatorUrl,
                new Sim(request.getIccid(), null),
                ActuatorResponse.class
        );

        ActivatedSim record = new ActivatedSim(
                request.getIccid(),
                request.getCustomerEmail(),
                response != null && response.isSuccess()
        );
        activatedSimRepository.save(record);

        return ResponseEntity.ok(record);

    }

    @GetMapping
    public ResponseEntity<ActivatedSim> getActivatedSim(@RequestParam Long simCardId) {
        Optional<ActivatedSim> optionalSim = activatedSimRepository.findById(simCardId);
        if (optionalSim.isPresent()) {
            ActivatedSim sim = optionalSim.get();
            return ResponseEntity.ok(sim);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
