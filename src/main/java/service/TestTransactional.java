package service;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Damager1 on 20.11.2014.
 */

@EnableTransactionManagement
public class TestTransactional {
    @Transactional(propagation = Propagation.SUPPORTS)
    private static void transaction2() {
        transaction1();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private static void transaction1() {

    }

    public static void main(String[] args) {
        try {
            System.out.println("1");
            transaction1();
        } catch (Exception e) {
            System.out.println("3");
            System.out.println(e.getStackTrace());
        }
        try {

            System.out.println("2");
            transaction2();
        } catch (Exception e) {
            System.out.println("4");
            System.out.println(e.getStackTrace());
        }
    }
}
