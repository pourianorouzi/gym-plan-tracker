package com.gymplan.tracker.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

class DTOTests {

    @Test
    void testAllDTOs() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserDTO.class);
    }
}
