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
    @OneToMany(targetEntity = ParameterValue.class)
    @JoinColumn(referencedColumnName = "id")
    private List<Long> parameterValueId;


}


