package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Store;

@Repository
public interface StoreDAO extends JpaRepository<Store, Integer> {

    @RestResource(path = "common", rel = "common")
    @Query("select s from Store s where s.name like CONCAT('%',:k,'%') or s.address like CONCAT('%',:k,'%')")
    Page<Store> commonSearch(@Param("k") String k, Pageable p);
}
