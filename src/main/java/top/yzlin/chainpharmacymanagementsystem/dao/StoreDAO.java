package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Store;

import javax.transaction.Transactional;

@Repository
public interface StoreDAO extends JpaRepository<Store, Integer> {
}
