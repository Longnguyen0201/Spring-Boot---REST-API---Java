package com.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @Column(name = "customer_number")
    private Integer customerNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "contact_last_name")
    private String contactLastName;
    @Column(name = "contact_first_name")
    private String contactFirstName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    private String country;

    @Column(name = "sales_rep_employee_number")
    private Integer salesRepEmployeeNumber;
    @Column(name = "credit_limit")
    private Long creditLimit;

}
