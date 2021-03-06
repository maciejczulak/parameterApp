package pl.maciejczulak.parameterApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public abstract class ParameterValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @ManyToOne(targetEntity = Parameter.class)
    @JoinColumn(referencedColumnName = "parameter_id")
    private Parameter parameter;





}
