package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentScheduleService;
import med.voll.api.domain.appointment.CreateScheduleDTO;
import med.voll.api.domain.appointment.ReadScheduleDetailedDTO;
import med.voll.api.domain.doctor.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<CreateScheduleDTO> createScheduleDTOJSON;

    @Autowired
    private JacksonTester<ReadScheduleDetailedDTO> readScheduleDetailedDTOJSON;

    @MockBean
    private AppointmentScheduleService service;

    @Test
    @DisplayName("Deverá devolver http 400 quando as informações estão inválidas ")
    @WithMockUser
    void schedule_Scenario1() throws Exception {
       var response =  mockMvc.perform(post("/appointment"))
                .andReturn().getResponse();

       assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deverá devolver http 200 quando as informações estão válidas ")
    @WithMockUser
    void schedule_Scenario2() throws Exception {
        //Given or Arrange
        LocalDateTime date = LocalDateTime.now().plusHours(1);
        Especialidade especialidade = Especialidade.CARDIOLOGIA;

        ReadScheduleDetailedDTO ReadScheduleDTO  = new ReadScheduleDetailedDTO(null, 2L, 5L, date);
        when(service.Schedule(any())).thenReturn(ReadScheduleDTO);

        var response =  mockMvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createScheduleDTOJSON.write(
                                        new CreateScheduleDTO(2L, 5L, especialidade, date)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String jsonExpected = readScheduleDetailedDTOJSON.write(ReadScheduleDTO).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}