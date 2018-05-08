package com.smph.ar.customer.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smph.ar.shared.enums.DeviceType;

/**
 * Marks a customer as an ios user or an android user
 * @see PromoPointsServiceCustomImpl
 * @author mbmartinez
 */
@Aspect
public class DeviceTypeAspect {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceTypeAspect.class);

    @Pointcut("execution(public * com.smph.ar.customer.service.custom.impl.PromoPointsServiceCustomImpl.*(..))")
    public void everythingInPromoPointsServiceCustomImpl() {};

    @After("everythingInPromoPointsServiceCustomImpl() && args(customerCode,deviceType,..)")
    public void checkCustomerDeviceType(String customerCode, DeviceType deviceType) {
        LOG.debug("DeviceTypeAspect::checkCustomerDeviceType({}, {})", customerCode, deviceType);
    }

}
