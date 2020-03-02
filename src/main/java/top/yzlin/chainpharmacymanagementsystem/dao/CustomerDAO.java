package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

    @RestResource(path = "common", rel = "common")
    @Query("select c from Customer c where c.phone like CONCAT(:k,'%')")
    Page<Customer> commonSearch(@Param("k") String k, Pageable p);


    Customer findByPhone(String phone);
}
