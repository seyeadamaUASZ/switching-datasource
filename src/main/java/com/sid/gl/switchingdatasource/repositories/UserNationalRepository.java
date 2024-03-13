package com.sid.gl.switchingdatasource.repositories;


import com.sid.gl.switchingdatasource.entities.UserNational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNationalRepository extends JpaRepository<UserNational,Integer> {
    List<UserNational> findAll();
}
