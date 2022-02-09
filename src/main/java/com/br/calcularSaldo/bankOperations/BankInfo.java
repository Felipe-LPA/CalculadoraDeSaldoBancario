package com.br.calcularSaldo.bankOperations;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankInfo {
    private String id;
    private String bankName;
    private String agencyNumber;
    private String accountNumber;

    public BankInfo(String id, String bankName, String agencyNumber, String accountNumber){
        this.id = id;
        this.bankName = bankName;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankInfo{" +
                "id='" + id + '\'' +
                ", bankName='" + bankName + '\'' +
                ", agencyNumber='" + agencyNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
