package pl.maciejczulak.parameterApp.model;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

public class DescriptionalParameterValue extends ParameterValue{
    @Column(length = 30)
    private String description;

    public DescriptionalParameterValue(Long id, LocalDate dateFrom, LocalDate dateTo, Long parameterId, String description) {
        super(id, dateFrom, dateTo, parameterId);
        this.description = description;
    }
}
