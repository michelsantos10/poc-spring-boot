/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.services.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import poc.services.entities.Employees;

/**
 *
 * @author miche
 */
@RepositoryRestResource(collectionResourceRel = "Employees", path = "Employees")
public interface EmployeesRepository extends PagingAndSortingRepository<Employees, Integer> {
	List<Employees> findByFirstName(@Param("f")String firstName);

	List<Employees> findByFirstNameAndLastName(@Param("f")String firstName, @Param("l")String lastName);
}
