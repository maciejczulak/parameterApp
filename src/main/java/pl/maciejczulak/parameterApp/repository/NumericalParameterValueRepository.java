package pl.maciejczulak.parameterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;

import java.time.LocalDate;
import java.util.Optional;

public interface NumericalParameterValueRepository extends JpaRepository<NumericalParameterValue, Long> {
    Optional<NumericalParameterValue> findByDateFrom(LocalDate dateFrom);
    Optional<NumericalParameterValue> findByDateTo(LocalDate dateTo);


}
