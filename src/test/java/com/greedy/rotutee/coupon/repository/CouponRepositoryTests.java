package com.greedy.rotutee.coupon.repository;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class CouponRepositoryTests {
}
