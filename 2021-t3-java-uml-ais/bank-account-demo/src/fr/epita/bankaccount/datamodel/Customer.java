package fr.epita.bankaccount.datamodel;

import java.util.*;

public class Customer {
    private String name;
    private String address;
    private Set<Account> accountList = new LinkedHashSet<>();

    public Set<Account> getAccountList() {
        return new LinkedHashSet<>(accountList);
    }

    public void setAccountList(Set<Account> accountList) {
        this.accountList.addAll(accountList);
    }

    public void addAccount(Account account) {
        this.accountList.add(account);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(Objects.toString(name, "")).append('\"');
        sb.append(",\"address\":\"")
                .append(Objects.toString(address, "")).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
