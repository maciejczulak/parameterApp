package pl.maciejczulak.parameterApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class DescriptionalParameterValue extends ParameterValue{
    @Column(length = 30)
    private String description;

    public DescriptionalParameterValue(Long id, LocalDate dateFrom, LocalDate dateTo, Long parameterId, String description) {
        super(id, dateFrom, dateTo, parameterId);
        this.description = description;
    }
}
