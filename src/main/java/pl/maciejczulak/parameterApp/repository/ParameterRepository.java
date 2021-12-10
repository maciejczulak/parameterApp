package pl.maciejczulak.parameterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejczulak.parameterApp.model.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

}
