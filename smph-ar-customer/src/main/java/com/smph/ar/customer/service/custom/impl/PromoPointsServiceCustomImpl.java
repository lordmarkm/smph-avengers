package com.smph.ar.customer.service.custom.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mynt.core.jpa.service.MyntJpaServiceCustomImpl;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.smph.ar.customer.dto.PromoPointsDashboardStats;
import com.smph.ar.customer.dto.PromoPointsInfo;
import com.smph.ar.customer.dto.SinglePromoDashboardStats;
import com.smph.ar.customer.model.Customer;
import com.smph.ar.customer.model.PromoPoints;
import com.smph.ar.customer.model.QPromoPoints;
import com.smph.ar.customer.service.CustomerService;
import com.smph.ar.customer.service.PromoPointsService;
import com.smph.ar.customer.service.custom.PromoPointsServiceCustom;
import com.smph.ar.shared.enums.DeviceType;
import static com.smph.ar.customer.model.QPromoPoints.promoPoints;

@Transactional
public class PromoPointsServiceCustomImpl extends MyntJpaServiceCustomImpl<PromoPoints, PromoPointsInfo, PromoPointsService>
    implements PromoPointsServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(PromoPointsServiceCustomImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CustomerService customerService;

    @Override
    public Optional<PromoPointsInfo> addPoints(String customerCode, DeviceType deviceType, String promoCode, long addedPoints,
            long addedSecondaryPoints) {

        Optional<PromoPoints> promoPoints = repo.findByCustomerCodeAndPromoCode(customerCode, promoCode);
        if (promoPoints.isPresent()) {
            PromoPoints existing = promoPoints.get();
            existing.setPoints(existing.getPoints() + addedPoints);
            existing.setSecondaryPoints(existing.getSecondaryPoints() + addedSecondaryPoints);
            return Optional.of(toDto(existing));
        } else {
            Customer customer = customerService.findByCode(customerCode)
                    .map(c -> c)
                    .orElse(newCustomer(customerCode));

            PromoPoints newRecord = new PromoPoints();
            newRecord.setCustomer(customer);
            newRecord.setPromoCode(promoCode);
            newRecord.setPoints(addedPoints);
            newRecord.setSecondaryPoints(addedSecondaryPoints);
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

    @Override
    public PromoPointsDashboardStats dashboardStats() {
        //Android
        JPAQuery<Tuple> query = new JPAQuery<>(em);
        query.from(promoPoints)
            .groupBy(promoPoints.promoCode)
            .where(promoPoints.customer.androidUser.isTrue());
        query.select(promoPoints.promoCode, promoPoints.promoCode.count(), promoPoints.points.sum());
        List<Tuple> results = query.fetch();
        LOG.debug("android results={}", results);

        Map<String, SinglePromoDashboardStats> statsByPromo =  results.stream()
                .map(t -> {
                    SinglePromoDashboardStats spds = new SinglePromoDashboardStats();
                    spds.setPromoCode(t.get(0, String.class));
                    spds.setAndroidUsers(t.get(1, Long.class));
                    spds.setAndroidPoints(t.get(2, Long.class));
                    return spds;
                })
                .collect(Collectors.toMap(SinglePromoDashboardStats::getPromoCode, s -> s));

        //IOS
        JPAQuery<Tuple> iosQuery = new JPAQuery<>(em);
        iosQuery.from(promoPoints)
            .groupBy(promoPoints.promoCode)
            .where(promoPoints.customer.iosUser.isTrue());
        iosQuery.select(promoPoints.promoCode, promoPoints.promoCode.count(), promoPoints.points.sum());
        List<Tuple> iosResults = iosQuery.fetch();
        LOG.debug("ios results={}", iosResults);

        iosResults.forEach(t -> {
            String promoCode = t.get(0, String.class);
            SinglePromoDashboardStats spds = statsByPromo.get(promoCode);
            if (null == spds) {
                spds = new SinglePromoDashboardStats();
                spds.setPromoCode(promoCode);
            }
            spds.setIosUsers(t.get(1, Long.class));
            spds.setIosPoints(t.get(2, Long.class));
        });

        PromoPointsDashboardStats stats = new PromoPointsDashboardStats();
        stats.setPromos(Lists.newArrayList(statsByPromo.values()));

        return stats;
    }

}
