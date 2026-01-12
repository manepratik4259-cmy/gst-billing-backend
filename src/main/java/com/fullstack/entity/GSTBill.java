package com.fullstack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GST_BILL")
public class GSTBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @Size(min = 2, message = "Customer Name should be at least 2 characters")
    private String custName;

    @NotBlank(message = "Customer Address should not be empty or null")
    private String custAddress;

    @Range(min = 1000000000, max = 9999999999L, message = "Contact Number must be 10 digit")
    private long custContactNumber;

    private LocalDate gstBillInvoiceDate;

    private double gstBillInvoiceAmount;

    @Email(message = "Email ID must be valid")
    private String custEmailId;

}
