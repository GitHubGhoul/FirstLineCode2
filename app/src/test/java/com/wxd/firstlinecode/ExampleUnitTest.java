package com.wxd.firstlinecode;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(Parameterized.class)
public class ExampleUnitTest {

    // 为测试类声明几个变量，分别用于存放期望值和测试所用数据
    private int x;
    private int expected;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    // 为测试类声明一个带有参数的公共构造函数，并在其中为变量赋值
    public ExampleUnitTest(int x,int expected) {
        this.x = x;
        this.expected = expected;
    }

    @Ignore
    @Test
    public void testBug101() {
        // 这个测试不会运行
    }

    @Test
    public void testNegative() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.fact(-1));
    }

    @Test
    public void testFact() {
        assertEquals(1, Factorial.fact(1));
        assertEquals(2, Factorial.fact(2));
        assertEquals(6, Factorial.fact(3));
        assertEquals(3628800, Factorial.fact(10));
        assertEquals(2432902008176640000L, Factorial.fact(20));
    }

    @Test
    public void testAbs() {
        assertEquals(expected, Math.abs(x));
    }

    @Test
    public void testAbsNegative() {
        assertEquals(expected, Math.abs(-x));
    }

    @Parameterized.Parameters
    public static Collection<Integer[]> prepareData() {
        Integer[][] object = {{2, 2}, {3, -3}, {0, 0}};
        return Arrays.asList(object);
    }

}