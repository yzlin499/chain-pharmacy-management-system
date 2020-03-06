package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    @RestResource(path = "storeId", rel = "storeId")
    @Query("select u from User u where u.enabled=true and u.store.id=:storeId")
    List<User> findByStoreId(@Param("storeId") Integer storeId);

    @RestResource(path = "common", rel = "common")
    @Query("select u from User u where u.name like CONCAT('%',:k,'%') " +
            "or u.username like CONCAT('%',:k,'%') " +
            "or u.phone like CONCAT(:k,'%')")
    Page<User> commonSearch(@Param("k") String k, Pageable p);
}
