package br.com.arhoffmann.service;

import br.com.arhoffmann.model.CodigoBoleto;
import br.com.arhoffmann.model.CodigoBoletoRequisicao;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BoletoService {
    public static String consultBoleto(String externalTerminal, Long externalNSU, Integer type, String digitable) throws Exception {
        String token = GenerateAuthenticationToken.getToken();


        CodigoBoleto codigoBoleto = new CodigoBoleto(type, digitable);
        CodigoBoletoRequisicao requestObject = new CodigoBoletoRequisicao(externalTerminal, externalNSU, codigoBoleto);

        Gson gson = new Gson();
        String body = gson.toJson(requestObject);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.openfinance.celcoin.dev/v5/transactions/billpayments/authorize"))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao gerar token! Status: " + response.statusCode() + "\n Body:" + response.body());
        } else {
            return response.body();
        }

    }
}
