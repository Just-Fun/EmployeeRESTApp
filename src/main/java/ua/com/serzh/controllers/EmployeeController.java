package ua.com.serzh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.serzh.entity.Employee;
import ua.com.serzh.dao.EmployeeDAO;
import ua.com.serzh.dao.EmployeeDAOImpl;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    // Get all employees
    @RequestMapping(method = RequestMethod.GET)
    public Employee[] getAll() {
        return employeeDAO.getAllEmployees().toArray(new Employee[0]);
    }

    // Get an employee
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity get(@PathVariable long id) {

        Employee match = null;
        match = employeeDAO.getEmployee(id);

        if (match != null) {
//            return new ResponseEntity<>(match, HttpStatus.OK);
            return ResponseEntity.ok(match);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            return ResponseEntity.notFound();
        }
    }

    // Get employees by lastName
    @RequestMapping(method = RequestMethod.GET, value = "/lastname/{name}")
    public ResponseEntity getByLastName(@PathVariable String name) {

        List<Employee> matchList = employeeDAO.getByLastName(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Employee[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // Get employee by title
    @RequestMapping(method = RequestMethod.GET, value = "/title/{name}")
    public ResponseEntity getByTitle(@PathVariable String name) {

        List<Employee> matchList = employeeDAO.getByTitle(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Employee[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get employee by dept
    @RequestMapping(method = RequestMethod.GET, value = "/department/{name}")
    public ResponseEntity getByDept(@PathVariable String name) {

        List<Employee> matchList = employeeDAO.getByDept(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Employee[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Add an employee
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Employee employee) {
        if (employeeDAO.add(employee)) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an employee
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Employee employee) {

        if (employeeDAO.update(id, employee)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a employee
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable long id) {

        boolean result = employeeDAO.delete(id);

        if (result) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
