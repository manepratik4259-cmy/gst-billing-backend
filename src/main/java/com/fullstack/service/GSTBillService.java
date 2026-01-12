package com.fullstack.service;

import com.fullstack.entity.GSTBill;
import com.fullstack.entity.User;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.repository.GSTBillRepository;
import com.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GSTBillService implements IGSTBill {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final GSTBillRepository gstBillRepository;

    public User signUp(User user) {

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        return userRepository.save(user);

    }


    @Override
    public List<GSTBill> saveAll(List<GSTBill> gstBills) {
        return gstBillRepository.saveAll(gstBills);
    }

    @Override
    public GSTBill createBill(GSTBill gstBill) {

        gstBill.setGstBillInvoiceAmount((gstBill.getGstBillInvoiceAmount()) + gstBill.getGstBillInvoiceAmount() * 18 / 100);
        return gstBillRepository.save(gstBill);
    }


    @Override
    public Optional<GSTBill> findById(long invoiceId) {

        return Optional.of(gstBillRepository.findById(invoiceId).orElseThrow(() -> new RecordNotFoundException("#ID does not exist")));
    }

    @Override
    public List<GSTBill> findAll() {
        return gstBillRepository.findAll();
    }

    @Override
    public GSTBill update(long invoiceId, GSTBill gstBill) {

        GSTBill gstBill1 = findById(invoiceId).get();

        gstBill1.setGstBillInvoiceAmount(gstBill.getGstBillInvoiceAmount());
        gstBill1.setGstBillInvoiceDate(gstBill.getGstBillInvoiceDate());
        gstBill1.setCustAddress(gstBill.getCustAddress());
        gstBill1.setCustName(gstBill.getCustName());
        gstBill1.setInvoiceId(gstBill.getInvoiceId());
        gstBill1.setCustContactNumber(gstBill.getCustContactNumber());
        gstBill1.setCustEmailId(gstBill.getCustEmailId());
        return gstBillRepository.save(gstBill1);
    }

    @Override
    public void deleteById(long invoiceId) {

        gstBillRepository.deleteById(invoiceId);

    }

    @Override
    public List<GSTBill> findByName(String custName) {
        return gstBillRepository.findByCustName(custName);
    }

    @Override
    public GSTBill findByEmail(String custEmailId) {
        return gstBillRepository.findBillByCustEmail(custEmailId);
    }

    @Override
    public List<GSTBill> findByAnyInput(String input) {

        return gstBillRepository.findAll().stream().filter(gst -> gst.getCustName().equals(input)
                || gst.getCustEmailId().equals(input)
                || String.valueOf(gst.getInvoiceId()).equals(input)
                || String.valueOf(gst.getCustContactNumber()).equals(input)).toList();
    }

    @Override
    public List<GSTBill> sortByIdDesc() {
        return gstBillRepository.findAll().stream().sorted(Comparator.comparing(GSTBill::getInvoiceId).reversed()).toList();
    }

    @Override
    public List<GSTBill> sortByName() {
        return gstBillRepository.findAll().stream().sorted(Comparator.comparing(GSTBill::getCustName)).toList();
    }

    @Override
    public List<GSTBill> sortByInvoiceAmount() {
        return gstBillRepository.findAll().stream().sorted(Comparator.comparing(GSTBill::getGstBillInvoiceAmount)).toList();
    }

    @Override
    public GSTBill changeContactNumber(long invoiceId, long custContactNumber) {

        GSTBill gst = findById(invoiceId).get();
        gst.setCustContactNumber(custContactNumber);
        return gstBillRepository.save(gst);
    }

    @Override
    public void deleteAll() {

        gstBillRepository.deleteAll();

    }
}
