package com.smph.ar.customer.service;

import java.util.Optional;

import com.mynt.core.jpa.service.MyntJpaService;
import com.smph.ar.customer.model.Customer;

public interface CustomerService extends MyntJpaService<Customer> {

    Optional<Customer> findByCode(String customerCode);

}
