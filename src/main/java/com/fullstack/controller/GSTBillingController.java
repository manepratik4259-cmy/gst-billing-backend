package com.fullstack.controller;

import com.fullstack.entity.GSTBill;
import com.fullstack.service.EmailService;
import com.fullstack.service.GSTBillService;
import com.fullstack.service.IEmailService;
import com.fullstack.service.IGSTBill;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gstbills")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "GST Billing", description = "APIs for managing GST bills")
@SecurityRequirement(name = "Bearer Auth")
public class GSTBillingController {

    private final IGSTBill gstBillService;

    private final IEmailService emailService;

    @PostMapping("/saveAll")
    public ResponseEntity<List<GSTBill>> saveAll(@RequestBody @Valid List<GSTBill> gstBill) {
        return new ResponseEntity<>(gstBillService.saveAll(gstBill), HttpStatus.CREATED);
    }

    @PostMapping("/createinvoice")
    public ResponseEntity<GSTBill> createBill(@RequestBody GSTBill gstBill) {
        return new ResponseEntity<>(gstBillService.createBill(gstBill), HttpStatus.CREATED);
    }

    @GetMapping("/findbyinvoice/{invoiceid}")
    public ResponseEntity<Optional<GSTBill>> findById(@PathVariable long invoiceId) {
        return new ResponseEntity<>(gstBillService.findById(invoiceId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<GSTBill>> findAll() {
        return new ResponseEntity<>(gstBillService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{invoiceId}")
    public ResponseEntity<GSTBill> update(@PathVariable long invoiceId, @RequestBody @Valid GSTBill gstBill) {
        return new ResponseEntity<>(gstBillService.update(invoiceId, gstBill), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{invoiceId}")
    public ResponseEntity<Void> deleteById(@PathVariable long invoiceId) {
        gstBillService.deleteById(invoiceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sortbyiddesc")
    public ResponseEntity<List<GSTBill>> sortByIdDesc() {
        return new ResponseEntity<>(gstBillService.sortByIdDesc(), HttpStatus.OK);
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<GSTBill>> sortByName() {
        return new ResponseEntity<>(gstBillService.sortByName(), HttpStatus.OK);
    }

    @GetMapping("/sortbyinvoiceamount")
    public ResponseEntity<List<GSTBill>> sortByInvoiceAmount() {
        return new ResponseEntity<>(gstBillService.sortByInvoiceAmount(), HttpStatus.OK);
    }

    @GetMapping("/findbyname/{custName}")
    public ResponseEntity<List<GSTBill>> findByName(@PathVariable String custName) {
        return new ResponseEntity<>(gstBillService.findByName(custName), HttpStatus.OK);
    }

    @GetMapping("/findbyemail/{custEmailId}")
    public ResponseEntity<GSTBill> findByEmail(@PathVariable String custEmailId) {
        return new ResponseEntity<>(gstBillService.findByEmail(custEmailId), HttpStatus.OK);
    }

    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<List<GSTBill>> findByAnyInput(@PathVariable String input) {
        return new ResponseEntity<>(gstBillService.findByAnyInput(input), HttpStatus.OK);
    }

    @PatchMapping("/changecontactnumber/{invoiceId}/{custContactNumber}")
    public ResponseEntity<GSTBill> changeContactNumber(@PathVariable long invoiceId, @PathVariable long custContactNumber) {
        return new ResponseEntity<>(gstBillService.changeContactNumber(invoiceId, custContactNumber), HttpStatus.OK);
    }

    @GetMapping("/sendinvoicebyemail/{custEmailId}")
    public ResponseEntity<String> sendInvoiceByEmail(@PathVariable String custEmailId) throws MessagingException {
        emailService.sendInvoiceByEmail(custEmailId);
        return new ResponseEntity<>("Email Sent Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<Void> deleteAll() {
        gstBillService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
