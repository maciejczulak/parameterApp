package pl.maciejczulak.parameterApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.maciejczulak.parameterApp.model.Parameter;
import pl.maciejczulak.parameterApp.service.ParameterService;

import java.util.List;

@RestController
@RequestMapping("/parameter")
public class ParameterController {
    private static final Logger log = LoggerFactory.getLogger(ParameterController.class);
    public ParameterService service;
    public ParameterController(ParameterService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Parameter addParameter (@RequestBody Parameter toAdd) {
        log.info("Attempt to save parameter {} to database", toAdd.getName());
        return service.addParameter(toAdd);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Parameter updateParameter(@PathVariable Long id, @RequestBody Parameter toUpdate){
        log.info("Attempt to update parameter with id={}", id);
        return service.updateParameter(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteParameter(@PathVariable Long id){
        log.info("Attempt to delete parameter with id={}", id);
        service.deleteParameter(id);
    }

    @GetMapping
    public List<Parameter> getParameterList(){
        log.info("Attempt to get list of all parameters");
        return service.getParameterList();
    }
}
