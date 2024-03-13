package com.sid.gl.switchingdatasource.repositories;

import com.sid.gl.switchingdatasource.entities.UserInternational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInternationalRepository extends JpaRepository<UserInternational,Integer> {
   List<UserInternational> findAll();
}
