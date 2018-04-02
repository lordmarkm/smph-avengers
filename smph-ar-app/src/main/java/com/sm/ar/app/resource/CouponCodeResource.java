package com.sm.ar.app.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/coupon-code")
public class CouponCodeResource {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile csv) {
        return null;
    }

}
