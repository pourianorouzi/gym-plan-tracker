package com.gymplan.tracker.entity;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class EntityTests {

    @Test
    public void testAllEntities() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserEntity.class);
    }
}
