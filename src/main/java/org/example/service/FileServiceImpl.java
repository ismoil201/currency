package org.example.service;

import java.io.*;

public class FileServiceImpl implements  FileService{
    @Override
    public void fileRead(String text) {

        File file = new File("currency.txt");

        try {
            file.createNewFile();

            InputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String row;
            StringBuilder stringBuilder = new StringBuilder();
            while ((row=bufferedReader.readLine())!=null){
                stringBuilder.append(row);
            }
            System.out.println(stringBuilder.toString());

        } catch (Exception e) {
          e.printStackTrace();
        }

    }

    @Override
    public void fileWrite(String text) {

        File file = new File("currency.txt");
        try {
            file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            byte[] byte1= text.getBytes();

            outputStream.write(byte1);
            outputStream.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
