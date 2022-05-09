package com.nanjolono.user.util;

import org.junit.Test;
import sun.security.action.GetPropertyAction;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;

public class ThreadTest   {

    @Test
    public void test_constr() throws InterruptedException {
        Thread thread = new Thread("UR-RAT");
        thread.start();
        Thread thread1 = new Thread("UR-PLO");
        thread1.start();
        System.out.println(thread);
        System.out.println(thread1);
        System.out.println(thread);
        System.out.println(thread1);
    }

}
