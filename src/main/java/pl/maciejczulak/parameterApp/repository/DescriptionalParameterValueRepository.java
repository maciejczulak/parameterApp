package pl.maciejczulak.parameterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.model.Parameter;

import java.time.LocalDate;
import java.util.Optional;

public interface DescriptionalParameterValueRepository extends JpaRepository<DescriptionalParameterValue, Long> {
    Optional<DescriptionalParameterValue> findByParameterIdAndDateFromAndDateTo(Long parameterId, LocalDate dateFrom, LocalDate dateTo);
    Optional<DescriptionalParameterValue> findByParameterAndDateFromAndDateTo(Parameter parameter, LocalDate dateFrom, LocalDate dateTo);
}
