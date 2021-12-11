package pl.maciejczulak.parameterApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.model.ParameterValue;
import pl.maciejczulak.parameterApp.repository.NumericalParameterValueRepository;

import java.time.LocalDate;
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
        Optional<NumericalParameterValue> opt1 = repository.findByDateFrom(toAdd.getDateFrom());
        Optional<NumericalParameterValue> opt2 = repository.findByDateTo(toAdd.getDateTo());
        if(opt1.isPresent() && opt2.isPresent()){
            throw new IllegalArgumentException("NumericalParameterValue in that period of time already exists");
        }
        log.info("Successfully saved numerical parameter value {} to database", toAdd.getId());
        return repository.save(toAdd);
    }

    public NumericalParameterValue updateNumericalParameterValue(Long id, NumericalParameterValue toUpdate) {
        Optional<NumericalParameterValue> optionalNumericalParameterValue = repository.findById(id);
        if (optionalNumericalParameterValue.isEmpty()){
            log.info("Numerical parameter value with id={} not found", id);
            return null;
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        log.info("Numerical parameter value with id={} successfully updated", id);
        return toUpdate;
    }

    public void deleteNumericalParameterValue (Long id) {
        Optional <NumericalParameterValue> optionalNumericalParameterValue = repository.findById(id);
        optionalNumericalParameterValue.ifPresentOrElse(param -> {
                    repository.delete(param);
                    log.info("Numerical parameter value with id={} deleted from database", id);
                },() -> log.info("Numerical parameter value with id={} not found", id)
        );
    }

    public List<NumericalParameterValue> getNumericalParameterValueList() {
        log.info("Getting list of all numerical parameter values");
        return repository.findAll();
    }

    public List<NumericalParameterValue> getNumericalParameterValueListByParameter(Long paremeterId) {
        log.info("Getting list of numerical parameter values with paremeterId={}", paremeterId);
        return repository.findAll().stream()
                .filter(a -> a.getParameterId().equals(paremeterId)).
                collect(Collectors.toList());
    }

    public List<NumericalParameterValue> getNumericalParameterValueListInPeriodOfTime(LocalDate min, LocalDate max) {
        log.info("Getting list of numerical parameter values in period of time from={} to={}", min, max);
        return repository.findAll().stream()
                .filter(a -> a.getDateFrom().isAfter(min)==true)
                .filter(b -> b.getDateTo().isBefore(max)==true)
                .collect(Collectors.toList());
    }

    public List<NumericalParameterValue> getNumericalParameterValueListByDateFrom(LocalDate min, LocalDate max) {
        log.info("Getting list of numerical parameter values by dateFrom from={} to={}", min, max);
        return repository.findAll().stream()
                .filter(a -> a.getDateFrom().isAfter(min)==true)
                .filter(b -> b.getDateFrom().isBefore(max)==true)
                .collect(Collectors.toList());
    }

    public List<NumericalParameterValue> getNumericalParameterValueListByDateTo(LocalDate min, LocalDate max) {
        log.info("Getting list of numerical parameters values by dateTo from={} to={}", min, max);
        return repository.findAll().stream()
                .filter(a -> a.getDateTo().isAfter(min)==true)
                .filter(b -> b.getDateTo().isBefore(max)==true)
                .collect(Collectors.toList());
    }



}
