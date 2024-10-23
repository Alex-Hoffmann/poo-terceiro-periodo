package ALEX_HOFFMANN.segundob.Lista2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetornaMoedas {
    public static void main(String[] args) throws IOException {
        String urlString = "https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        System.out.println(content.toString());
    }
}
