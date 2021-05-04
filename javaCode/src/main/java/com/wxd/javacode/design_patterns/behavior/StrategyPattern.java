package com.wxd.javacode.design_patterns.behavior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

public class StrategyPattern {
    public static void main(String[] args) {
        String[] array = { "apple", "Pear", "Banana", "orange" };
        sort(array, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(array));

        DiscountContext ctx = new DiscountContext();

        // 默认使用普通会员折扣:
        BigDecimal pay1 = ctx.calculatePrice(BigDecimal.valueOf(105));
        System.out.println(pay1);

        // 使用满减折扣:
        ctx.setStrategy(new OverDiscountStrategy());
        BigDecimal pay2 = ctx.calculatePrice(BigDecimal.valueOf(105));
        System.out.println(pay2);

        // 使用Prime会员折扣:
        ctx.setStrategy(new PrimeDiscountStrategy());
        BigDecimal pay3 = ctx.calculatePrice(BigDecimal.valueOf(105));
        System.out.println(pay3);
    }

    static <T> void sort(T[] a, Comparator<? super T> c) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (c.compare(a[j], a[j + 1]) > 0) { // 注意这里比较两个元素的大小依赖传入的策略
                    T temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public interface DiscountStrategy {
        // 计算折扣额度:
        BigDecimal getDiscount(BigDecimal total);
    }

    public static class UserDiscountStrategy implements DiscountStrategy{

        @Override
        public BigDecimal getDiscount(BigDecimal total) {
            // 普通会员打九折:
            return total.multiply(new BigDecimal("0.1")).setScale(2, RoundingMode.DOWN);
        }
    }

    public static class OverDiscountStrategy implements DiscountStrategy {
        public BigDecimal getDiscount(BigDecimal total) {
            // 满100减20优惠:
            return total.compareTo(BigDecimal.valueOf(100)) >= 0 ? BigDecimal.valueOf(20) : BigDecimal.ZERO;
        }
    }

    public static class PrimeDiscountStrategy implements DiscountStrategy {

        @Override
        public BigDecimal getDiscount(BigDecimal total) {
            // Prime会员打七折:
            return total.multiply(new BigDecimal("0.3")).setScale(2, RoundingMode.DOWN);
        }
    }


    public class PrimeOverDiscountStrategy implements DiscountStrategy{
        @Override
        public BigDecimal getDiscount(BigDecimal total) {
            return total.compareTo(BigDecimal.valueOf(100)) >= 0 ?
                    total.subtract(BigDecimal.valueOf(20)).multiply(new BigDecimal("0.3")).setScale(2, RoundingMode.DOWN).add(BigDecimal.valueOf(20)) :
                    total.multiply(new BigDecimal("0.3")).setScale(2, RoundingMode.DOWN);
        }
    }

    public static class DiscountContext {
        // 持有某个策略:
        private DiscountStrategy strategy = new UserDiscountStrategy();

        // 允许客户端设置新策略:
        public void setStrategy(DiscountStrategy strategy) {
            this.strategy = strategy;
        }

        public BigDecimal calculatePrice(BigDecimal total) {
            return total.subtract(this.strategy.getDiscount(total)).setScale(2);
        }
    }
}
