package pl.maciejczulak.parameterApp.model;

import javax.persistence.Column;
import java.util.Date;

public class DescriptionalParameterValue extends ParameterValue{
    @Column(length = 30)
    private String description;

    public DescriptionalParameterValue(Long id, Date dateFrom, Date dateTo, String description) {
        super(id, dateFrom, dateTo);
        this.description = description;
    }
}
