package pl.maciejczulak.parameterApp.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.maciejczulak.parameterApp.model.Parameter;
import pl.maciejczulak.parameterApp.repository.ParameterRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ParameterServiceTest {

    private static final Parameter PARAMETER1 = new Parameter(1L, "Weight", "Weight measurement");

    @Mock
    private ParameterRepository repository;

    @InjectMocks
    private ParameterService service;

   // @Test
   // void shouldAddParameterToDatabase(){
    //    Mockito.when(service.addParameter(Mockito.any(Parameter.class))).thenReturn(PARAMETER1);
    //    Assertions.assertThat(repository.findAll()).isNotEmpty();
   // }






}