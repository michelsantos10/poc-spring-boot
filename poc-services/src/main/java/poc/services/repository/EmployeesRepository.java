/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.services.repository;

import org.springframework.data.repository.CrudRepository;
import poc.services.entities.Employees;

/**
 *
 * @author miche
 */
@RepositoryRestResource(collectionResourceRel = "Employees", path = "Employees")
public interface EmployeesRepository extends CrudRepository<Employees, Integer> {

}
