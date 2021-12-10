package pl.maciejczulak.parameterApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejczulak.parameterApp.model.Parameter;
import pl.maciejczulak.parameterApp.repository.ParameterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterService {
    private static final Logger log = LoggerFactory.getLogger(ParameterService.class);
    public ParameterRepository repository;
    public ParameterService(ParameterRepository repository) {
        this.repository = repository;
    }

    public Parameter addParameter(Parameter toAdd) {
        log.info("Successfully saved parameter {} to database", toAdd.getName());
        return repository.save(toAdd);
    }

    public Parameter updateParameter(Long id, Parameter toUpdate) {
        Optional <Parameter> optionalParameter = repository.findById(id);
        if (optionalParameter.isEmpty()){
            log.info("Parameter with id={} not found", id);
            return null;
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        log.info("Parameter with id={} successfully updated", id);
        return toUpdate;
    }

    public void deleteParameter (Long id) {
        Optional <Parameter> optionalParameter = repository.findById(id);
        optionalParameter.ifPresentOrElse(parameter -> {
            repository.delete(parameter);
            log.info("Parameter with id={} deleted from database", id);
        },() -> log.info("Parameter with id={} not found", id)
        );
    }

    public List<Parameter> getParameterList() {
        log.info("Getting list of all paremeters");
        return repository.findAll();
    }


    }




