package pl.maciejczulak.parameterApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
public class NumericalParameterValue extends ParameterValue {
    private BigDecimal value;
    private Unit unit;

    public NumericalParameterValue(Long id, Date dateFrom, Date dateTo, BigDecimal value, Unit unit) {
        super(id, dateFrom, dateTo);
        this.value = value;
        this.unit = unit;
    }


}


