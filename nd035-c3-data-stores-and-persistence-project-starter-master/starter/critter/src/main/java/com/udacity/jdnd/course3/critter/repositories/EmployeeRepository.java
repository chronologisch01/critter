package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.*;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> getAllByDaysAvailable(DayOfWeek dayOfWeek);

}
