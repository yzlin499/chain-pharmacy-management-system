package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Notice;

@Repository
public interface NoticeDAO extends JpaRepository<Notice, Integer> {
}
