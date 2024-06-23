package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {

}
