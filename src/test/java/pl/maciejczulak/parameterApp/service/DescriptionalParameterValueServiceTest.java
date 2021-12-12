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
import pl.maciejczulak.parameterApp.model.Parameter;
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
    DescriptionalParameterValue VALUE1 = new DescriptionalParameterValue(1L, LocalDate.of(2019,9,10), LocalDate.of(2019,11,10), new Parameter(1L, "Param1", null), "First measurement");
    DescriptionalParameterValue VALUE2 = new DescriptionalParameterValue(2L, LocalDate.of(2020,9,10), LocalDate.of(2020,11,10), new Parameter(1L, "Param2", null), "Second measurement");
    DescriptionalParameterValue VALUE3 = new DescriptionalParameterValue(3L, LocalDate.of(2021,9,10), LocalDate.of(2021,11,10), new Parameter(2L, "Param2",  null), "Third measurement");
    List<DescriptionalParameterValue> ALL_VALUES = List.of(VALUE1, VALUE2, VALUE3);
    LocalDate min = LocalDate.of(2019, 8, 10);
    LocalDate max1 = LocalDate.of(2020, 10, 10);
    LocalDate max2 = LocalDate.of(2021, 12, 10);
    DescriptionalParameterValue VALUE1TOUPDATE = new DescriptionalParameterValue(null, LocalDate.of(2019,9,10), LocalDate.of(2019,11,10), new Parameter(1L, "Param2", null), "First update measurement");


    @Mock
    private DescriptionalParameterValueRepository repository;

    @InjectMocks
    private DescriptionalParameterValueService service;

    @Test
    void shouldAddCorrectly(){
        when(repository.findByParameterIdAndDateFromAndDateTo(VALUE1.getParameter().getId(), VALUE1.getDateFrom(), VALUE1.getDateTo())).thenReturn(Optional.empty());
        DescriptionalParameterValue checked = service.addDescriptionalParameterValue(VALUE1);
        verify(repository).save(VALUE1);
    }

    @Test
    void shouldAddReturnNullIfValueExists(){
        when(repository.findByParameterIdAndDateFromAndDateTo(VALUE1.getParameter().getId(), VALUE1.getDateFrom(), VALUE1.getDateTo())).thenReturn(Optional.of(VALUE1));
        DescriptionalParameterValue checked = service.addDescriptionalParameterValue(VALUE1);
        assertThat(checked).isNull();
    }

    @Test
    void shouldUpdateCorrectly(){
        when(repository.findById(VALUE1.getId())).thenReturn(Optional.of(VALUE1));
        DescriptionalParameterValue checked = service.updateDescriptionalParameterValue(VALUE1.getId(), VALUE1TOUPDATE);
        verify(repository).save(VALUE1TOUPDATE);
        assertThat(checked.getDescription()).isEqualTo("First update measurement");
    }

    @Test
    void shouldUpdateReturnNullIfValueDontExists(){
        Long notExistsId = 4L;
        when(repository.findById(notExistsId)).thenReturn(Optional.empty());
        DescriptionalParameterValue checked = service.updateDescriptionalParameterValue(notExistsId, VALUE1TOUPDATE);
        assertThat(checked).isNull();
    }

    @Test
    void shouldDeleteCorrectly(){
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