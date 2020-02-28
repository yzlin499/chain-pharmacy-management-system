package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface MedicineDAO extends JpaRepository<Medicine, Long> {

    @RestResource(path = "common", rel = "common")
    @Query("select m from Medicine m where m.id like CONCAT('%',:k,'%') or m.name like CONCAT('%',:k,'%') or m.brand like CONCAT('%',:k,'%')")
    Page<Medicine> commonSearch(@Param("k") String k, Pageable p);
}
