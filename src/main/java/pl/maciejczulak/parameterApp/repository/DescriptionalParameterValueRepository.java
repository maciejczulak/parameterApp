package pl.maciejczulak.parameterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;

public interface DescriptionalParameterValueRepository extends JpaRepository<DescriptionalParameterValue, Long> {
}
