package com.wxd.javacode.multithread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinClass {
    public static void main(String[] args) throws Exception {
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);
        // fork/join:
        ForkJoinTask<Long> task = new SumTask(array,0,array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }

    static class SumTask extends RecursiveTask<Long> {

        static final int THRESHOLD = 500;
        long[] array;
        int start;
        int end;

        public SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // 如果任务足够小,直接计算:
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += this.array[i];
                    //故意放慢计算速度
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return sum;
            }
            //任务太大 一分为二
            int middle = (end + start) / 2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
            SumTask sumTask1 = new SumTask(this.array, start, middle);
            SumTask sumTask2 = new SumTask(this.array, middle, end);
            invokeAll(sumTask1, sumTask2);
            Long subResult1 = sumTask1.join();
            Long subResult2 = sumTask2.join();
            Long result = subResult1 + subResult2;
            System.out.println("result = " + subResult1 + " + " + subResult2 + " ==> " + result);
            return result;
        }

    }
}
