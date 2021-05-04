package com.wxd.javacode.design_patterns.structural;

public class BridgePattern {
    public static void main(String[] args) {
        RefinedCar car = new BossCar(new HybridEngine());
        car.drive();
    }

    static abstract class Car {
        // 引用Engine:
        protected Engine engine;

        public Car(Engine engine) {
            this.engine = engine;
        }

        public abstract void drive();
    }

    public interface Engine {
        void start();
    }

    public static abstract class RefinedCar extends Car {
        public RefinedCar(Engine engine) {
            super(engine);
        }

        public void drive() {
            this.engine.start();
            System.out.println("Drive " + getBrand() + " car...");
        }

        public abstract String getBrand();
    }

    public static class BossCar extends RefinedCar {
        public BossCar(Engine engine) {
            super(engine);
        }

        public String getBrand() {
            return "Boss";
        }
    }

    public static class HybridEngine implements Engine {
        public void start() {
            System.out.println("Start Hybrid Engine...");
        }
    }

}
