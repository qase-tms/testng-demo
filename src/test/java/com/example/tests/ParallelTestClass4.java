package com.example.tests;

import io.qase.commons.annotation.QaseId;
import org.testng.annotations.Test;

/**
 * Fourth test class with rapid-fire tests to maximize concurrent access
 * to the shared ArrayList in the Qase reporter.
 */
public class ParallelTestClass4 {

    @Test
    @QaseId(4001)
    public void test4_1() {
        System.out.println("ParallelTestClass4.test4_1 - Thread: " + Thread.currentThread().getName());
        // Very short execution time to increase concurrency pressure
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4002)
    public void test4_2() {
        System.out.println("ParallelTestClass4.test4_2 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4003)
    public void test4_3() {
        System.out.println("ParallelTestClass4.test4_3 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4004)
    public void test4_4() {
        System.out.println("ParallelTestClass4.test4_4 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4005)
    public void test4_5() {
        System.out.println("ParallelTestClass4.test4_5 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4006)
    public void test4_6() {
        System.out.println("ParallelTestClass4.test4_6 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4007)
    public void test4_7() {
        System.out.println("ParallelTestClass4.test4_7 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4008)
    public void test4_8() {
        System.out.println("ParallelTestClass4.test4_8 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4009)
    public void test4_9() {
        System.out.println("ParallelTestClass4.test4_9 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4010)
    public void test4_10() {
        System.out.println("ParallelTestClass4.test4_10 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4011)
    public void test4_11() {
        System.out.println("ParallelTestClass4.test4_11 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4012)
    public void test4_12() {
        System.out.println("ParallelTestClass4.test4_12 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4013)
    public void test4_13() {
        System.out.println("ParallelTestClass4.test4_13 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4014)
    public void test4_14() {
        System.out.println("ParallelTestClass4.test4_14 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(4015)
    public void test4_15() {
        System.out.println("ParallelTestClass4.test4_15 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
