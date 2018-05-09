package com.smph.ar.customer.aspect;

import java.util.Optional;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smph.ar.customer.model.Customer;
import com.smph.ar.customer.service.CustomerService;
import com.smph.ar.shared.enums.DeviceType;

/**
 * Marks a customer as an ios user or an android user
 * @see PromoPointsServiceCustomImpl
 * @author mbmartinez
 */
@Aspect
@Component
public class DeviceTypeAspect {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceTypeAspect.class);

    @Autowired
    private CustomerService customerService;

    @Pointcut("execution(public * com.smph.ar.customer.service.custom.PromoPointsServiceCustom.*(..))")
    public void everythingInPromoPointsServiceCustom() {};

    @After("everythingInPromoPointsServiceCustom() && args(customerCode,deviceType,..)")
    public void checkCustomerDeviceType(String customerCode, DeviceType deviceType) {
        LOG.debug("DeviceTypeAspect::checkCustomerDeviceType({}, {})", customerCode, deviceType);

        Optional<Customer> customerOpt = customerService.findByCode(customerCode);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            switch (deviceType) {
            case android:
                customer.setAndroidUser(true);
                customer.setIosUser(false);
                break;
            case ios:
                customer.setIosUser(true);
                customer.setAndroidUser(false);
                break;
            default:
                LOG.error("Unknown device type! type={}", deviceType);
            }
            customerService.save(customer);
            LOG.debug("Customer {} is now an {} user", customerCode, deviceType);
        } else {
            LOG.warn("Could not update customer device type! customerCode={}, dType={}", customerCode, deviceType);
        }
    }

}
