package pl.maciejczulak.parameterApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.model.ParameterValue;
import pl.maciejczulak.parameterApp.service.NumericalParameterValueService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/value/num")
public class NumericalParameterValueController {
    private static final Logger log = LoggerFactory.getLogger(NumericalParameterValueController.class);
    public NumericalParameterValueService service;
    public NumericalParameterValueController(NumericalParameterValueService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public NumericalParameterValue addNumericalParameterValue (@RequestBody NumericalParameterValue toAdd) {
        log.info("Attempt to save numerical parameter value with parameterId={} to database", toAdd.getParameter().getId());
        return service.addNumericalParameterValue(toAdd);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public NumericalParameterValue updateNumericalParameterValue(@PathVariable Long id, @RequestBody NumericalParameterValue toUpdate){
        log.info("Attempt to update numerical parameter value with id={}", id);
        return service.updateNumericalParameterValue(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteNumericalParameterValue(@PathVariable Long id){
        log.info("Attempt to delete numerical parameter value with id={}", id);
        service.deleteNumericalParameterValue(id);
    }

    @GetMapping
    public List<NumericalParameterValue> getNumericalParameterValueList(){
        log.info("Attempt to get list of all numerical parameter values");
        return service.getNumericalParameterValueList();
    }

    @GetMapping("/filter/{parameterId}")
    public List<NumericalParameterValue> getNumericalParameterValueListByParameter(@PathVariable Long parameterId){
        log.info("Attempt to get list of all numerical parameter values with parameterId={}", parameterId);
        return service.getNumericalParameterValueListByParameter(parameterId);
    }

    @GetMapping("/filter/{min}/{max}")
    public List<NumericalParameterValue> getNumericalParameterValueListInPeriodOfTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate min,
                                                                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate max){
        log.info("Attempt to get list of numerical parameter values in period of time min={} to max={}", min, max);
        return service.getNumericalParameterValueListInPeriodOfTime(min, max);
    }

    @GetMapping("/filter/from/{min}/{max}")
    public List<NumericalParameterValue> getNumericalParameterValueListByDateFrom(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate min,
                                                                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate max){
        log.info("Attempt to get list of numerical parameter values by dateFrom min={} to max={}", min, max);
        return service.getNumericalParameterValueListByDateFrom(min, max);
    }

    @GetMapping("/filter/to/{min}/{max}")
    public List<NumericalParameterValue> getNumericalParameterValueListByDateTo(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate min,
                                                                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate max){
        log.info("Attempt to get list of numerical parameter values by dateTo min={} to max={}", min, max);
        return service.getNumericalParameterValueListByDateTo(min, max);
    }




}
