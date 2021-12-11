package pl.maciejczulak.parameterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;

import java.time.LocalDate;
import java.util.Optional;

public interface DescriptionalParameterValueRepository extends JpaRepository<DescriptionalParameterValue, Long> {
    Optional<DescriptionalParameterValue> findByDateFrom(LocalDate dateFrom);
    Optional<DescriptionalParameterValue> findByDateTo(LocalDate dateTo);
}
