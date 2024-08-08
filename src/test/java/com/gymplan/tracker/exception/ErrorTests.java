package com.gymplan.tracker.exception;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

class ErrorTests {

    @Test
    void testAllErrorObjects() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(BusinessError.class);
        beanTester.testBean(ValidationError.class);
    }

}
