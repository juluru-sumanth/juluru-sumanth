package com.ems;

import com.ems.entity.Employee;
import com.ems.entity.Player;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.PlayerRepository;
import com.ems.service.EmployeeService;
import com.ems.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
 class EmployeManagementSystemApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private PlayerService playerService;

	@MockBean
	private PlayerRepository playerRepository;

	@Test
	void getUserTest() {

	}



}
