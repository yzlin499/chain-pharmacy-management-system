package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
