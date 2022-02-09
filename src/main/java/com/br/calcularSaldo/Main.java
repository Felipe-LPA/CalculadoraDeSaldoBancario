package com.br.calcularSaldo;

import com.br.calcularSaldo.bankOperations.ClientsOperations;
import com.br.calcularSaldo.bankOperations.Operations;
import com.br.calcularSaldo.fileManager.FileManager;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Operations> operationsList = FileManager.readFile("bankoperations.csv");

        Set<String> bankClients = new HashSet<>();

        assert operationsList != null;
        for (Operations client: operationsList) {
                bankClients.add(client.getBankInfo().getId());
            }

        System.out.println(bankClients);
        List<ClientsOperations> clients = new ArrayList<>();
        bankClients.forEach(client -> clients.add(new ClientsOperations(client)));
        operationsList.forEach(operations -> clients.forEach(client -> {
            if (Objects.equals(client.getId(), operations.getBankInfo().getId())){
                client.setOperations(operations);
            }
        }));
        clients.forEach(client -> {
            client.getOperations().sort(Comparator.comparing(Operations::getDate));
            client.getOperations().forEach(operations -> {
                if(operations.getType().equalsIgnoreCase("saque")){
                    client.setBalance(-operations.getValue());
                }else{
                    client.setBalance(+operations.getValue());
                }
            });
        });
        FileManager.CreateFolder("Extract");
        clients.forEach(client -> FileManager.writeFile(client, "Extract"));
    }
}
