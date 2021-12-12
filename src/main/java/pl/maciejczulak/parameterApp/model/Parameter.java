package pl.maciejczulak.parameterApp.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Long id;
    @Column(unique = true, length = 50)
    @NotNull
    private String name;
    private String description;
    @OneToMany(targetEntity = ParameterValue.class, mappedBy = "parameter")
    private List<ParameterValue> parameterValue;

    public Parameter(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}


