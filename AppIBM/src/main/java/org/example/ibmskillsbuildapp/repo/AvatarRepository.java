package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.Avatar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    // Add custom query methods if needed
}
