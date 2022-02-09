package com.br.calcularSaldo.bankOperations;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter


public class ClientsOperations {

    private Double balance = 0D;
    private final List<Operations> operations = new LinkedList<>();
    private final String id;
    public ClientsOperations(String id){
        this.id = id;
    }
    public void setOperations(Operations operations) {
        this.operations.add(operations);
    }
    public void setBalance(Double balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "ClientsOperations{" +
                "balance=" + balance +
                ", operations=" + operations +
                ", id='" + id + '\'' +
                "}";
    }
}
