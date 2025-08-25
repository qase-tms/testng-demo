package com.example.tests;

import io.qase.commons.annotation.QaseId;
import org.testng.annotations.Test;

/**
 * Third test class to further increase parallel execution pressure.
 * Uses thread pool size and invocation count to create high concurrency.
 */
public class ParallelTestClass3 {

    @Test(threadPoolSize = 4, invocationCount = 8)
    @QaseId(3001)
    public void test3_concurrent_1() {
        System.out.println("ParallelTestClass3.test3_concurrent_1 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(threadPoolSize = 4, invocationCount = 8)
    @QaseId(3002)
    public void test3_concurrent_2() {
        System.out.println("ParallelTestClass3.test3_concurrent_2 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(threadPoolSize = 4, invocationCount = 8)
    @QaseId(3003)
    public void test3_concurrent_3() {
        System.out.println("ParallelTestClass3.test3_concurrent_3 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(7);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(threadPoolSize = 4, invocationCount = 8)
    @QaseId(3004)
    public void test3_concurrent_4() {
        System.out.println("ParallelTestClass3.test3_concurrent_4 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(threadPoolSize = 4, invocationCount = 8)
    @QaseId(3005)
    public void test3_concurrent_5() {
        System.out.println("ParallelTestClass3.test3_concurrent_5 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
