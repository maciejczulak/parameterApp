package pl.maciejczulak.parameterApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.maciejczulak.parameterApp.model.DescriptionalParameterValue;
import pl.maciejczulak.parameterApp.repository.DescriptionalParameterValueRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DescriptionalParameterValueServiceTest {
    final static DescriptionalParameterValue VALUE1 = new DescriptionalParameterValue(1L, LocalDate.of(2019,9,10), LocalDate.of(2019,11,10), 1L, "First measurement");
    final static DescriptionalParameterValue VALUE2 = new DescriptionalParameterValue(2L, LocalDate.of(2020,9,10), LocalDate.of(2020,11,10), 1L, "Second measurement");
    final static DescriptionalParameterValue VALUE3 = new DescriptionalParameterValue(3L, LocalDate.of(2021,9,10), LocalDate.of(2021,11,10), 2L, "Third measurement");
    List<DescriptionalParameterValue> ALL_VALUES = List.of(VALUE1, VALUE2, VALUE3);
    LocalDate min = LocalDate.of(2019, 8, 10);
    LocalDate max1 = LocalDate.of(2020, 10, 10);
    LocalDate max2 = LocalDate.of(2021, 12, 10);

    @Mock
    private DescriptionalParameterValueRepository repository;

    @InjectMocks
    private DescriptionalParameterValueService service;

    @Test
    void shouldDeleteValueFromDatabase(){
        when(repository.findById(VALUE1.getId())).thenReturn(Optional.of(VALUE1));
        service.deleteDescriptionalParameterValue(VALUE1.getId());
        verify(repository).delete(VALUE1);
    }

    @Test
    void shouldGetAllValues(){
        when(repository.findAll()).thenReturn(ALL_VALUES);
        List<DescriptionalParameterValue> checklist = service.getDescriptionalParameterValueList();
        assertThat(checklist.size()).isEqualTo(3);
    }

    @Test
    void shouldGetValuesByParameterId(){
        when(repository.findAll()).thenReturn(ALL_VALUES);
        Long parameterId = 1L;
        List<DescriptionalParameterValue> checklist = service.getDescriptionalParameterValueListByParameter(parameterId);
        assertThat(checklist.size()).isEqualTo(2);
    }

    @Test
    void shouldGetValuesByPeriodOfTime(){
        when(repository.findAll()).thenReturn(ALL_VALUES);
        List<DescriptionalParameterValue> checklist1 = service.getDescriptionalParameterValueListInPeriodOfTime(min, max1);
        List<DescriptionalParameterValue> checklist2 = service.getDescriptionalParameterValueListInPeriodOfTime(min, max2);
        assertThat(checklist1.size()).isEqualTo(1);
        assertThat(checklist2.size()).isEqualTo(3);
    }

    @Test
    void shouldGetValuesByDateFrom(){
        when(repository.findAll()).thenReturn(ALL_VALUES);
        List<DescriptionalParameterValue> checklist1 = service.getDescriptionalParameterValueListByDateFrom(min, max1);
        List<DescriptionalParameterValue> checklist2 = service.getDescriptionalParameterValueListByDateFrom(min, max2);
        assertThat(checklist1.size()).isEqualTo(2);
        assertThat(checklist2.size()).isEqualTo(3);
    }

    @Test
    void shouldGetValuesByDateTo(){
        when(repository.findAll()).thenReturn(ALL_VALUES);
        List<DescriptionalParameterValue> checklist1 = service.getDescriptionalParameterValueListByDateTo(min, max1);
        List<DescriptionalParameterValue> checklist2 = service.getDescriptionalParameterValueListByDateTo(min, max2);
        assertThat(checklist1.size()).isEqualTo(1);
        assertThat(checklist2.size()).isEqualTo(3);
    }






}