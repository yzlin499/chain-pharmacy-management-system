package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;

@Repository
public interface MedicineDAO extends JpaRepository<Medicine, Long> {
}
