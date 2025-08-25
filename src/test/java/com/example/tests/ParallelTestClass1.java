package com.example.tests;

import io.qase.commons.annotation.QaseId;
import org.testng.annotations.Test;

/**
 * Test class designed to trigger ConcurrentModificationException
 * when run in parallel with other test classes.
 * 
 * Each test method is lightweight and completes quickly to maximize
 * the chance of concurrent access to the shared results ArrayList.
 */
public class ParallelTestClass1 {

    @Test
    @QaseId(1001)
    public void test1_1() {
        System.out.println("ParallelTestClass1.test1_1 - Thread: " + Thread.currentThread().getName());
        // Simulate minimal work
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1002)
    public void test1_2() {
        System.out.println("ParallelTestClass1.test1_2 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1003)
    public void test1_3() {
        System.out.println("ParallelTestClass1.test1_3 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1004)
    public void test1_4() {
        System.out.println("ParallelTestClass1.test1_4 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1005)
    public void test1_5() {
        System.out.println("ParallelTestClass1.test1_5 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(8);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1006)
    public void test1_6() {
        System.out.println("ParallelTestClass1.test1_6 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(12);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1007)
    public void test1_7() {
        System.out.println("ParallelTestClass1.test1_7 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(7);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1008)
    public void test1_8() {
        System.out.println("ParallelTestClass1.test1_8 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(18);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1009)
    public void test1_9() {
        System.out.println("ParallelTestClass1.test1_9 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(1010)
    public void test1_10() {
        System.out.println("ParallelTestClass1.test1_10 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(14);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
