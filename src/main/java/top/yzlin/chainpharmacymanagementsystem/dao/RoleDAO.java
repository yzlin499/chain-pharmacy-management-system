package top.yzlin.chainpharmacymanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yzlin.chainpharmacymanagementsystem.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
    Role findByAuthority(String authority);
}
