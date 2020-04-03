package test;

import bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class FirstTest {
    private Account account;
    private Customer customer;
    private Bank bank;

    @BeforeEach
    public void set() throws Exception {
        bank = new Bank();
        account = new Account(500);
    }

    @Test
    public void deposit() {
        account.deposit(100);
        int expected = (int)account.getBalance();
        int actual = 600;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSavingAccount() {
        bank.addCustomer("NameF", "NameL");
        customer = bank.getCustomer(0);
        customer.setAccount(new SavingAccount(400.00, 0.03));
        customer = bank.getCustomer(0);
        account = customer.getAccount();
        account.withdraw(300);

        Assert.assertEquals((int) account.getBalance(), 100);
    }

    @Test
    public void testCheckingAccount() {
        bank.addCustomer("NameF", "NameL");
        customer = bank.getCustomer(0);
        customer.setAccount(new CheckingAccount(400.00, 300.00));
        customer = bank.getCustomer(0);
        account = customer.getAccount();
        account.withdraw(600);

        Assert.assertEquals((int) account.getBalance(), 0);
    }

    @Test
    public void testOverdraftLimit() {
        bank.addCustomer("NameF", "NameL");
        customer = bank.getCustomer(0);
        customer.setAccount(new CheckingAccount(500.00, 300.00));
        customer = bank.getCustomer(0);
        account = customer.getAccount();
        account.withdraw(800);
        Assert.assertEquals((int) account.getBalance(), 0);
    }

}