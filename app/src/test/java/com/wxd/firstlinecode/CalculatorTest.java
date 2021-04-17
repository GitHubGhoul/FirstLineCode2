package com.wxd.firstlinecode;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorTest {

    enum Type {
        ADD, MINUS
    }

    private Type type;
    private int a;
    private int b;
    private int expected;
    private Calculator calculator;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
    }

    @After
    public void tearDown() {
        this.calculator = null;
    }

    public CalculatorTest(Type type, int a, int b, int expected) {
        this.type = type;
        this.a = a;
        this.b = b;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {Type.ADD, 2, 1, 3},
                        {Type.ADD, 2, 1, 3},
                        {Type.ADD, 5, 0, 5},
                        {Type.MINUS, 5, 2, 3},
                        {Type.MINUS, 7, 1, 6},
                        {Type.MINUS, -3, -4, 1}});
    }

    @Test
    public void testAdd() {
        assertEquals(100, this.calculator.add(100));
        assertEquals(150, this.calculator.add(50));
        assertEquals(130, this.calculator.add(-20));
    }

    @Test
    public void testSub() {
        assertEquals(-100, this.calculator.sub(100));
        assertEquals(-150, this.calculator.sub(50));
        assertEquals(-130, this.calculator.sub(-20));
    }

    @Test
    public void testMixAdd() {
        Assume.assumeTrue(type == Type.ADD);
        assertEquals(this.expected, calculator.add(a, b));
    }

    @Test
    public void testMixMinus() {
        Assume.assumeTrue(type == Type.MINUS);
        assertEquals(this.expected, calculator.minus(a, b));
    }
}
