package com.gymplan.tracker.entity;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

class EntityTests {

    @Test
    void testAllEntities() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserEntity.class);
    }
}
