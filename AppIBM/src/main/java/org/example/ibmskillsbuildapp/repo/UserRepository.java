package org.example.ibmskillsbuildapp.repo;

import java.util.List;
import org.example.ibmskillsbuildapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);

    List<User> findByIdInOrderByScoreDesc(List<Long> friends); //Friends Only Leaderboard

    List<User> findAllByOrderByScoreDesc();//Global Leaderboard

    List<User> findByUserNameStartingWith(String username);//To search users
}
