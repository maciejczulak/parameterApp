package pl.maciejczulak.parameterApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.model.NumericalParameterValue;
import pl.maciejczulak.parameterApp.model.ParameterValue;
import pl.maciejczulak.parameterApp.service.NumericalParameterValueService;

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
        log.info("Attempt to save parameter {} to database", toAdd.getId());
        return service.addNumericalParameterValue(toAdd);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public NumericalParameterValue updateNumericalParameterValue(@PathVariable Long id, @RequestBody NumericalParameterValue toUpdate){
        log.info("Attempt to update parameter value with id={}", id);
        return service.updateNumericalParameterValue(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteNumericalParameterValue(@PathVariable Long id){
        log.info("Attempt to delete parameter value with id={}", id);
        service.deleteNumericalParameterValue(id);
    }

    @GetMapping
    public List<NumericalParameterValue> getNumericalParameterValueList(){
        log.info("Attempt to get list of all parameters value");
        return service.getNumericalParameterValueList();
    }

    @GetMapping("/filter/{parameterId}")
    public List<NumericalParameterValue> getNumericalParameterValueListByParameter(@PathVariable Long parameterId){
        log.info("Attempt to get list of all parameters value with paremeterId={}", parameterId);
        return service.getNumericalParameterValueListByParameter(parameterId);
    }






}
