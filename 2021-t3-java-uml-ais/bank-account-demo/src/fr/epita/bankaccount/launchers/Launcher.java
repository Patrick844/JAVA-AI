package fr.epita.bankaccount.launchers;

import java.math.BigDecimal;
import java.util.*;

import fr.epita.bankaccount.datamodel.Account;
import fr.epita.bankaccount.datamodel.Customer;
import fr.epita.bankaccount.datamodel.Savings;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Beginning of the application");

        System.out.println("Please enter customer name:");
        Scanner scanner = new Scanner(System.in);
        String customerName = scanner.nextLine();

        System.out.println("Please enter customer address:");
        String customerAddress = scanner.nextLine();

        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setAddress(customerAddress);


        //
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(20.0));
        Set<Account> accounts = new LinkedHashSet<>();
        accounts.add(account);

        Savings savings = new Savings();
        accounts.add(savings);


        customer.setAccountList(accounts);


        System.out.println(customer);







    }

}
