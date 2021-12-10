package pl.maciejczulak.parameterApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.parameterApp.model.Parameter;
import pl.maciejczulak.parameterApp.model.ParameterValue;
import pl.maciejczulak.parameterApp.repository.ParameterValueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterValueService {
    private static final Logger log = LoggerFactory.getLogger(ParameterValueService.class);
    public ParameterValueRepository repository;
    public ParameterValueService(ParameterValueRepository repository) {
        this.repository = repository;
    }

    public ParameterValue addParameterValue(ParameterValue toAdd) {
        log.info("Successfully saved value of parameter {} to database", toAdd.getId());
        return repository.save(toAdd);
    }

    public ParameterValue updateParameterValue(Long id, ParameterValue toUpdate) {
        Optional<ParameterValue> optionalParameterValue = repository.findById(id);
        if (optionalParameterValue.isEmpty()){
            log.info("Parameter value with id={} not found", id);
            return null;
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        log.info("Parameter value with id={} successfully updated", id);
        return toUpdate;
    }

    public void deleteParameterValue (Long id) {
        Optional <ParameterValue> optionalParameterValue = repository.findById(id);
        optionalParameterValue.ifPresentOrElse(parameterValue -> {
                    repository.delete(parameterValue);
                    log.info("Parameter value with id={} deleted from database", id);
                },() -> log.info("Parameter value with id={} not found", id)
        );
    }

    public List<ParameterValue> getParameterValueList() {
        log.info("Getting list of all paremeters value");
        return repository.findAll();
    }


}
