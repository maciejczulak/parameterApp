package pl.maciejczulak.parameterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;

public interface NumericalParameterValueRepository extends JpaRepository<NumericalParameterValue, Long> {
}
