package com.smph.ar.customer.service.custom.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mynt.core.jpa.service.MyntJpaServiceCustomImpl;
import com.smph.ar.customer.dto.PromoPointsInfo;
import com.smph.ar.customer.model.Customer;
import com.smph.ar.customer.model.PromoPoints;
import com.smph.ar.customer.service.CustomerService;
import com.smph.ar.customer.service.PromoPointsService;
import com.smph.ar.customer.service.custom.PromoPointsServiceCustom;
import com.smph.ar.shared.enums.DeviceType;

@Transactional
public class PromoPointsServiceCustomImpl extends MyntJpaServiceCustomImpl<PromoPoints, PromoPointsInfo, PromoPointsService>
    implements PromoPointsServiceCustom {

    @Autowired
    private CustomerService customerService;

    @Override
    public Optional<PromoPointsInfo> addPoints(String customerCode, DeviceType deviceType, String promoCode, long additionalPoints) {

        Optional<PromoPoints> promoPoints = repo.findByCustomerCodeAndPromoCode(customerCode, promoCode);
        if (promoPoints.isPresent()) {
            PromoPoints existing = promoPoints.get();
            existing.setPoints(existing.getPoints() + additionalPoints);
            return Optional.of(toDto(existing));
        } else {
            Customer customer = customerService.findByCode(customerCode)
                    .map(c -> c)
                    .orElse(newCustomer(customerCode));

            PromoPoints newRecord = new PromoPoints();
            newRecord.setCustomer(customer);
            newRecord.setPromoCode(promoCode);
            newRecord.setPoints(additionalPoints);
            return Optional.of(toDto(repo.save(newRecord)));
        }

    }

    private Customer newCustomer(String customerCode) {
        Customer customer = new Customer();
        customer.setCode(customerCode);
        return customerService.save(customer);
    }

    @Override
    public Optional<PromoPointsInfo> getPoints(String customerCode, DeviceType deviceType, String promoCode) {
        return repo.findByCustomerCodeAndPromoCode(customerCode, promoCode)

                //TODO toDto(Optional<E>) should be in MyntJpaServiceCustom
                .map(p -> toDto(p));
    }

}
