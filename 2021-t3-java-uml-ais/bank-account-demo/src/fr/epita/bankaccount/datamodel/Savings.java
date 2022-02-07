package fr.epita.bankaccount.datamodel;

import java.math.BigDecimal;

public class Savings extends Account {


    private BigDecimal interestRate;

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal computeEarnedInterests(){
        return this.interestRate.multiply(this.balance);
    }
}
