package com.group.call.almundo.repository;

import com.group.call.almundo.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByStatus(String status);

}
