package com.br.calcularSaldo;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.io.FileUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class FileManager {

    public static List readFile(String path){
        try {
            FileReader fileReader = new FileReader(path);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            String[] nextRecord;
            Set<String> duplicated = new HashSet<>();
            List<Operations> operations = new LinkedList<>();
            while ((nextRecord = csvReader.readNext()) != null){
                String aux = Arrays.toString(nextRecord);
                if (!duplicated.contains(aux)){
                    operations.add(new Operations(nextRecord));
                    duplicated.add(Arrays.toString(nextRecord));
                }
            }

            return operations;
//            for(Operations cell: operations){
//                    System.out.println(cell);
//                }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writeFile(ClientsOperations client, String path){
        DateTimeFormatter dateFormater = DateTimeFormat.forPattern("dd 'de' MMMM 'de' yyyy '(' HH:mm:ss ')'");
        Path pathTo = Paths.get(path, client.getId() +".txt");

        try {
            Files.writeString(pathTo,"ID da conta: " + client.getId() + System.lineSeparator(), CREATE, APPEND);
            Files.writeString(pathTo," " + System.lineSeparator(), CREATE, APPEND);
            Files.writeString(pathTo,"                DATA               - TRANSAÇÂO - VALOR  " + System.lineSeparator(), CREATE, APPEND);
            Files.writeString(pathTo,"--------------------------------------------------------" + System.lineSeparator(), CREATE, APPEND);
            client.getOperations().forEach(operations -> {
                try {
                    if(operations.getType().equalsIgnoreCase("saque")){
                        Files.writeString(pathTo,operations.getDate().toString(dateFormater)
                                + " -   " + operations.getType()
                                + "   - R$" + operations.getValue() + System.lineSeparator(), CREATE, APPEND);
                    }else{
                        Files.writeString(pathTo,operations.getDate().toString(dateFormater)
                                + " - " + operations.getType()
                                + "  - R$" + operations.getValue() + System.lineSeparator(), CREATE, APPEND);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Files.writeString(pathTo," " + System.lineSeparator(), CREATE, APPEND);
            Files.writeString(pathTo,"Saldo Atual: R$" + client.getBalance() + System.lineSeparator(), CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateFolder(String folderName){
        File folder = new File(folderName);
        if(folder.exists()){
            try {
                FileUtils.deleteDirectory(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        folder.mkdir();

    }
}
