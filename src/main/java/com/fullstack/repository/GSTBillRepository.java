package com.fullstack.repository;

import com.fullstack.entity.GSTBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GSTBillRepository extends JpaRepository<GSTBill, Long> {

    //Custom Method
    List<GSTBill> findByCustName(String custName);

    //JPAQL
    @Query("SELECT g FROM GSTBill g WHERE g.custEmailId = :custEmailId")
    GSTBill findBillByCustEmail(@Param("custEmailId") String custEmailId);

}
