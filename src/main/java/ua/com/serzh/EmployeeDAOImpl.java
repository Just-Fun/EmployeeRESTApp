package ua.com.serzh;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class EmployeeDAOImpl implements EmployeeDAO{
    
    private final CopyOnWriteArrayList<Employee> eList = MockEmployeeList.getInstance();
    
    @Override
    public List<Employee> getAllEmployees(){
        return eList;
    }
   

    @Override
    public Employee getEmployee(long id){
        Employee match;
                
        match = eList.stream()
                    .filter(e -> e.getId() == id)
                    .findFirst().orElse(null);
        
        return match;        
    }
    

    @Override
    public List<Employee> getByLastName(String name){

        return eList.stream()
            .filter((e) -> (e.getLastName().contains(name)))
            .collect(Collectors.toList());
    }
    
    
    @Override
    public List<Employee> getByTitle(String title){

        return eList.stream()
            .filter((e) -> (e.getTitle().contains(title)))
            .collect(Collectors.toList());
    }

    
    @Override
    public List<Employee> getByDept(String dept){

        return eList.stream()
            .filter((e) -> (e.getDept().contains(dept)))
            .collect(Collectors.toList());
    }
    
    
    @Override
    public boolean add(Employee employee){
        long next = eList.size() + 100;

        Employee nextEmployee = 
            new Employee( next, employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getPhone(), 
                employee.getBirthDate(), employee.getTitle(), employee.getDept());
            
        eList.add(nextEmployee);
        return true;
    }
    
    
    @Override
    public boolean update(long id, Employee employee){
        int matchIndex = -1;
        
        matchIndex = eList.stream()
                    .filter(e -> e.getId() == id)
                    .findFirst()
                    .map(eList::indexOf)
                    .orElse(matchIndex);
               
        if (matchIndex > -1){
            eList.set(matchIndex, employee);
            return true;           
        } else {
            return false;           
        }                
    
    }

    
    @Override
    public boolean delete(long id){
        int matchIndex = -1;
        
        matchIndex = eList.stream()
                    .filter(e -> e.getId() == id)
                    .findFirst()
                    .map(eList::indexOf)
                    .orElse(matchIndex);
                
        if (matchIndex > -1){
            eList.remove(matchIndex);
            return true;           
        } else {
            return false;           
        }                
    }

}
