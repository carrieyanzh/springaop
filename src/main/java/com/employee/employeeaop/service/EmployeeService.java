package com.employee.employeeaop.service;


import com.employee.employeeaop.entity.Employee;
import com.employee.employeeaop.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws Exception {
      Employee employee = employeeRepository.findById(id).orElse(null);
      //return employeeRepository.findById(id).orElse(null);
      if(employee != null)
        return employee;
      else {
        throw new Exception("Invalid! Customer Id");
      }
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) throws Exception {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            return employeeRepository.save(employee);
        }else{
            throw new Exception("Invalid! Customer Id");
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
