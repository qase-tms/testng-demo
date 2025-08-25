package com.example.tests;

import io.qase.commons.annotation.QaseId;
import org.testng.annotations.Test;

/**
 * Second test class to increase parallel execution pressure
 * and maximize chances of ConcurrentModificationException.
 */
public class ParallelTestClass2 {

    @Test
    @QaseId(2001)
    public void test2_1() {
        System.out.println("ParallelTestClass2.test2_1 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(6);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2002)
    public void test2_2() {
        System.out.println("ParallelTestClass2.test2_2 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(11);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2003)
    public void test2_3() {
        System.out.println("ParallelTestClass2.test2_3 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2004)
    public void test2_4() {
        System.out.println("ParallelTestClass2.test2_4 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2005)
    public void test2_5() {
        System.out.println("ParallelTestClass2.test2_5 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(9);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2006)
    public void test2_6() {
        System.out.println("ParallelTestClass2.test2_6 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(13);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2007)
    public void test2_7() {
        System.out.println("ParallelTestClass2.test2_7 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2008)
    public void test2_8() {
        System.out.println("ParallelTestClass2.test2_8 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2009)
    public void test2_9() {
        System.out.println("ParallelTestClass2.test2_9 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @QaseId(2010)
    public void test2_10() {
        System.out.println("ParallelTestClass2.test2_10 - Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(19);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
