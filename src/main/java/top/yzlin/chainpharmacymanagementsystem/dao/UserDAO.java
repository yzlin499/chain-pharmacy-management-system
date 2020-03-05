package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    @RestResource(path = "storeId", rel = "storeId")
    @Query("select u from User u where u.enabled=true and u.store.id=:storeId")
    List<User> findByStoreId(@Param("storeId") Integer storeId);
}
