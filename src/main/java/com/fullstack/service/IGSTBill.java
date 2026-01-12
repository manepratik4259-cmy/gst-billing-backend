package com.fullstack.service;

import com.fullstack.entity.GSTBill;

import java.util.List;
import java.util.Optional;

public interface IGSTBill {

    List<GSTBill> saveAll(List<GSTBill> gstBills);

    GSTBill createBill(GSTBill gstBill);

    Optional<GSTBill> findById(long invoiceId);

    List<GSTBill> findAll();

    GSTBill update(long invoiceId, GSTBill gstBill);

    void deleteById(long invoiceId);

    List<GSTBill> findByName(String custName);

    GSTBill findByEmail(String custEmailId);

    List<GSTBill> findByAnyInput(String input);

    List<GSTBill> sortByIdDesc();

    List<GSTBill> sortByName();

    List<GSTBill> sortByInvoiceAmount();

    GSTBill changeContactNumber(long invoiceId, long custContactNumber);

    void deleteAll();

}
