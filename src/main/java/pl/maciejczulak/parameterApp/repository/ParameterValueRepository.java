package pl.maciejczulak.parameterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejczulak.parameterApp.model.ParameterValue;

public interface ParameterValueRepository extends JpaRepository <ParameterValue, Long> {
}
