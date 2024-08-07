package com.gymplan.tracker.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class DTOTests {

    @Test
    public void testAllDTOs() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserDTO.class);
    }
}
