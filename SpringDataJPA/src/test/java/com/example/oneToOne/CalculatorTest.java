package com.example.oneToOne;

import TestPractice.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {
    // Test Naming convention
    // test_<System/Method under test>_<Condition or State>_<Expected_result>
    @Test
    public void test_IntegerDivision_WhenTenIsDividedByFive_ShouldReturnTwo(){
        Calculator c = new Calculator();
        int result = c.integerDivision(10,5);
        Assertions.assertEquals(result,2,"10/5 did not produce 2");
    }

    @Test
    public void test_IntegerDivision_WhenDividedIsDividedByZero_ShouldThrowArithmeticException(){
        Calculator c = new Calculator();
        int result = c.integerDivision(10,0);
    }
}
