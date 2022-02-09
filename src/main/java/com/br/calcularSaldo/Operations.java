package com.br.calcularSaldo;


import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
public class Operations {
    private DateTime date;
    private BankInfo bankInfo;
    private String operator;
    private String type;
    private Double value;

    public Operations(String[] array){

        this.date = new DateTime(array[0]);
        this.bankInfo = new BankInfo(array[1], array[2], array[3], array[4]);
        this.operator = array[5];
        this.type = array[6];
        this.value = Double.valueOf(array[7]);

    }

    @Override
    public String toString() {
        return "Operations{" +
                "date=" + date +
                ", bankInfo=" + bankInfo +
                ", operator='" + operator + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                "}";
    }
}