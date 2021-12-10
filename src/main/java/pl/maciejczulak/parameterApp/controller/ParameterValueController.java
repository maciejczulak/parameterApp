package pl.maciejczulak.parameterApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.maciejczulak.parameterApp.model.Parameter;
import pl.maciejczulak.parameterApp.model.ParameterValue;
import pl.maciejczulak.parameterApp.service.ParameterValueService;

import java.util.List;

@RestController
@RequestMapping("/parameter/value")
public class ParameterValueController {
    private static final Logger log = LoggerFactory.getLogger(ParameterValueController.class);
    public ParameterValueService service;
    public ParameterValueController(ParameterValueService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ParameterValue addParameterValue (@RequestBody ParameterValue toAdd) {
        log.info("Attempt to save parameter {} to database", toAdd.getId());
        return service.addParameterValue(toAdd);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ParameterValue updateParameterValue(@PathVariable Long id, @RequestBody ParameterValue toUpdate){
        log.info("Attempt to update parameter value with id={}", id);
        return service.updateParameterValue(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteParameterValue(@PathVariable Long id){
        log.info("Attempt to delete parameter value with id={}", id);
        service.deleteParameterValue(id);
    }

    @GetMapping
    public List<ParameterValue> getParameterValueList(){
        log.info("Attempt to get list of all parameters");
        return service.getParameterValueList();
    }
}
