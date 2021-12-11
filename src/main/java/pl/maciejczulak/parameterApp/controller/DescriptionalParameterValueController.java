package pl.maciejczulak.parameterApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.model.ParameterValue;
import pl.maciejczulak.parameterApp.service.DescriptionalParameterValueService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/value/desc")
public class DescriptionalParameterValueController {
    private static final Logger log = LoggerFactory.getLogger(DescriptionalParameterValueController.class);
    public DescriptionalParameterValueService service;
    public DescriptionalParameterValueController(DescriptionalParameterValueService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DescriptionalParameterValue addDescriptionalParameterValue (@RequestBody DescriptionalParameterValue toAdd) {
        log.info("Attempt to save parameter {} to database", toAdd.getId());
        return service.addDescriptionalParameterValue(toAdd);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public DescriptionalParameterValue updateDescriptionalParameterValue(@PathVariable Long id, @RequestBody DescriptionalParameterValue toUpdate){
        log.info("Attempt to update parameter value with id={}", id);
        return service.updateDescriptionalParameterValue(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteDescriptionalParameterValue(@PathVariable Long id){
        log.info("Attempt to delete parameter value with id={}", id);
        service.deleteDescriptionalParameterValue(id);
    }

    @GetMapping
    public List<DescriptionalParameterValue> getDescriptionalParameterValueList(){
        log.info("Attempt to get list of all parameters value");
        return service.getDescriptionalParameterValueList();
    }

    @GetMapping("/filter/{parameterId}")
    public List<DescriptionalParameterValue> getDescriptionalParameterValueListByParameter(@PathVariable Long parameterId){
        log.info("Attempt to get list of all parameters value with paremeterId={}", parameterId);
        return service.getDescriptionalParameterValueListByParameter(parameterId);
    }

    @GetMapping("/filter/{min}/{max}")
    public List<DescriptionalParameterValue> getDescriptionalParameterValueListInPeriodOfTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate min,
                                                                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate max){
        log.info("Attempt to get list of descriptional parameter values in period of time min={} to max={}", min, max);
        return service.getDescriptionalParameterValueListInPeriodOfTime(min, max);
    }

    @GetMapping("/filter/from/{min}/{max}")
    public List<DescriptionalParameterValue> getDescriptionalParameterValueListByDateFrom(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate min,
                                                                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate max){
        log.info("Attempt to get list of descriptional parameter values by dateFrom min={} to max={}", min, max);
        return service.getDescriptionalParameterValueListByDateFrom(min, max);
    }

    @GetMapping("/filter/to/{min}/{max}")
    public List<DescriptionalParameterValue> getDescriptionalParameterValueListByDateTo(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate min,
                                                                                @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate max){
        log.info("Attempt to get list of descriptional parameter values by dateTo min={} to max={}", min, max);
        return service.getDescriptionalParameterValueListByDateTo(min, max);
    }













}
