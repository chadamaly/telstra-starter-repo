package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.model.ActivatedSim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivatedSimRepository extends JpaRepository<ActivatedSim, Long> {
}
