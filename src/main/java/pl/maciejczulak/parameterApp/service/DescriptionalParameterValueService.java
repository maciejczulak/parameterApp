package pl.maciejczulak.parameterApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.repository.DescriptionalParameterValueRepository;

import java.time.LocalDate;
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
        Optional<DescriptionalParameterValue> opt1 = repository.findByDateFrom(toAdd.getDateFrom());
        Optional<DescriptionalParameterValue> opt2 = repository.findByDateTo(toAdd.getDateTo());
        if(opt1.isPresent() && opt2.isPresent()){
            throw new IllegalArgumentException("DescriptionalParameterValue in that period of time already exists");
        }
        log.info("Successfully saved descriptional value of parameter {} to database", toAdd.getId());
        return repository.save(toAdd);
    }

    public DescriptionalParameterValue updateDescriptionalParameterValue(Long id, DescriptionalParameterValue toUpdate) {
        Optional<DescriptionalParameterValue> optionalDescriptionalParameterValue = repository.findById(id);
        if (optionalDescriptionalParameterValue.isEmpty()){
            log.info("Descriptional parameter value with id={} not found", id);
            return null;
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        log.info("Descriptional parameter value with id={} successfully updated", id);
        return toUpdate;
    }

    public void deleteDescriptionalParameterValue (Long id) {
        Optional <DescriptionalParameterValue> optionalDescriptionalParameterValue = repository.findById(id);
        optionalDescriptionalParameterValue.ifPresentOrElse(param -> {
                    repository.delete(param);
                    log.info("Descriptional parameter value with id={} deleted from database", id);
                },() -> log.info("Descriptional parameter value with id={} not found", id)
        );
    }

    public List<DescriptionalParameterValue> getDescriptionalParameterValueList() {
        log.info("Getting list of all descriptional parameter values");
        return repository.findAll();
    }

    public List<DescriptionalParameterValue> getDescriptionalParameterValueListByParameter(Long paremeterId) {
        log.info("Getting list of descriptional parameter values with paremeterId={}", paremeterId);
        return repository.findAll().stream()
                .filter(a -> a.getParameterId().equals(paremeterId)).collect(Collectors.toList());
    }

    public List<DescriptionalParameterValue> getDescriptionalParameterValueListInPeriodOfTime(LocalDate min, LocalDate max) {
        log.info("Getting list of descriptional parameter values in period of time from={} to={}", min, max);
        return repository.findAll().stream()
                .filter(a -> a.getDateFrom().isAfter(min)==true)
                .filter(b -> b.getDateTo().isBefore(max)==true)
                .collect(Collectors.toList());
    }

    public List<DescriptionalParameterValue> getDescriptionalParameterValueListByDateFrom(LocalDate min, LocalDate max) {
        log.info("Getting list of descriptional parameter values by dateFrom from={} to={}", min, max);
        return repository.findAll().stream()
                .filter(a -> a.getDateFrom().isAfter(min)==true)
                .filter(b -> b.getDateFrom().isBefore(max)==true)
                .collect(Collectors.toList());
    }

    public List<DescriptionalParameterValue> getDescriptionalParameterValueListByDateTo(LocalDate min, LocalDate max) {
        log.info("Getting list of descriptional parameters values by dateTo from={} to={}", min, max);
        return repository.findAll().stream()
                .filter(a -> a.getDateTo().isAfter(min)==true)
                .filter(b -> b.getDateTo().isBefore(max)==true)
                .collect(Collectors.toList());
    }



}
