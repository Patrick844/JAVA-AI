package fr.epita.bankaccount.datamodel;

import java.math.BigDecimal;

/**
 * This represents the Account model, the balance is initially set to {@link BigDecimal}.ZERO
 * <pre><code>
 * Account account = new Account();
 * System.out.println(account.getBalance());
 * //should print "0"
 * </code></pre>
 */
public class Account {
    //level : access
    //private: only the declaring class
    //(nothing) : only classes of the same package
    //protected : inheriting classes + classes of the same package
    //public : all classes

    protected BigDecimal balance = BigDecimal.ZERO;

    public BigDecimal getBalance() {
        return balance;
    }

    /**
     *
     * @param balance should not be null
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
