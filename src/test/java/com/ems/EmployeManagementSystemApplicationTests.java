package com.ems;


import com.ems.controller.RestEmployeeController;
import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(value = RestEmployeeController.class)
class EmployeeManagementSystemApplicationTests {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void saveEmployeeTest() throws Exception {

        Employee mockEmployee = new Employee();
        mockEmployee.setId(100);
        mockEmployee.setFirstName("sumanth");
        mockEmployee.setLastName("juluru");
        mockEmployee.setEmail("sumanth@gmail.com");

        String inputJson = this.mapToJson(mockEmployee);
        String URI = "/api/v2/saveEmp";
        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                post(URI).accept(MediaType.APPLICATION_JSON).
                content(inputJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());


    }


    @Test
    public void getAllEmployeesTest() throws Exception {

        Employee mockemployee1 = new Employee();
        mockemployee1.setId(10);
        mockemployee1.setFirstName("sumanth");
        mockemployee1.setLastName("juluru");
        mockemployee1.setEmail("sumanth@gmail.com");

        Employee mockEmployee2 = new Employee();
        mockEmployee2.setId(20);
        mockEmployee2.setFirstName("mahesh");
        mockEmployee2.setLastName("babu");
        mockEmployee2.setEmail("mahesh@gmail.com");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(mockemployee1);
        employeeList.add(mockEmployee2);

        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeList);
        String URI = "/api/v2/getAll";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(employeeList);
        String outputJson = mvcResult.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(expectedJson);


    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }


}
