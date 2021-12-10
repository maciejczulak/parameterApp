package pl.maciejczulak.parameterApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.model.ParameterValue;
import pl.maciejczulak.parameterApp.repository.NumericalParameterValueRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NumericalParameterValueService {
    private static final Logger log = LoggerFactory.getLogger(NumericalParameterValueService.class);
    public NumericalParameterValueRepository repository;
    public NumericalParameterValueService(NumericalParameterValueRepository repository) {
        this.repository = repository;
    }

    public NumericalParameterValue addNumericalParameterValue(NumericalParameterValue toAdd) {
        log.info("Successfully saved value of parameter {} to database", toAdd.getId());
        return repository.save(toAdd);
    }

    public NumericalParameterValue updateNumericalParameterValue(Long id, NumericalParameterValue toUpdate) {
        Optional<NumericalParameterValue> optionalNumericalParameterValue = repository.findById(id);
        if (optionalNumericalParameterValue.isEmpty()){
            log.info("Parameter value with id={} not found", id);
            return null;
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        log.info("Parameter value with id={} successfully updated", id);
        return toUpdate;
    }

    public void deleteNumericalParameterValue (Long id) {
        Optional <NumericalParameterValue> optionalNumericalParameterValue = repository.findById(id);
        optionalNumericalParameterValue.ifPresentOrElse(param -> {
                    repository.delete(param);
                    log.info("Parameter value with id={} deleted from database", id);
                },() -> log.info("Parameter value with id={} not found", id)
        );
    }

    public List<NumericalParameterValue> getNumericalParameterValueList() {
        log.info("Getting list of all paremeters value");
        return repository.findAll();
    }

    public List<NumericalParameterValue> getNumericalParameterValueListByParameter(Long paremeterId) {
        log.info("Getting list of paremeters value with paremeterId={}", paremeterId);
        return repository.findAll().stream()
                .filter(a -> a.getParameterId().equals(paremeterId)).collect(Collectors.toList());
    }

}
