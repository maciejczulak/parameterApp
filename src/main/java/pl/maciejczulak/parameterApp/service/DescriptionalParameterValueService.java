package pl.maciejczulak.parameterApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.repository.DescriptionalParameterValueRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DescriptionalParameterValueService {
    private static final Logger log = LoggerFactory.getLogger(DescriptionalParameterValueService.class);
    public DescriptionalParameterValueRepository repository;
    public DescriptionalParameterValueService(DescriptionalParameterValueRepository repository) {
        this.repository = repository;
    }

    public DescriptionalParameterValue addDescriptionalParameterValue(DescriptionalParameterValue toAdd) {
        log.info("Successfully saved value of parameter {} to database", toAdd.getId());
        return repository.save(toAdd);
    }

    public DescriptionalParameterValue updateDescriptionalParameterValue(Long id, DescriptionalParameterValue toUpdate) {
        Optional<DescriptionalParameterValue> optionalDescriptionalParameterValue = repository.findById(id);
        if (optionalDescriptionalParameterValue.isEmpty()){
            log.info("Parameter value with id={} not found", id);
            return null;
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        log.info("Parameter value with id={} successfully updated", id);
        return toUpdate;
    }

    public void deleteDescriptionalParameterValue (Long id) {
        Optional <DescriptionalParameterValue> optionalDescriptionalParameterValue = repository.findById(id);
        optionalDescriptionalParameterValue.ifPresentOrElse(param -> {
                    repository.delete(param);
                    log.info("Parameter value with id={} deleted from database", id);
                },() -> log.info("Parameter value with id={} not found", id)
        );
    }

    public List<DescriptionalParameterValue> getDescriptionalParameterValueList() {
        log.info("Getting list of all paremeters value");
        return repository.findAll();
    }

    public List<DescriptionalParameterValue> getDescriptionalParameterValueListByParameter(Long paremeterId) {
        log.info("Getting list of paremeters value with paremeterId={}", paremeterId);
        return repository.findAll().stream()
                .filter(a -> a.getParameterId().equals(paremeterId)).collect(Collectors.toList());
    }




}
