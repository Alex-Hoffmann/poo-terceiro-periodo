package br.com.arhoffmann.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvenioService {
    public static String listConvenios(String type, String uf) throws Exception {
        String token = GenerateAuthenticationToken.getToken();

        StringBuilder url = new StringBuilder("https://sandbox.openfinance.celcoin.dev/v5/transactions/institutions?");
        url.append("Type=").append(type);
        url.append("&UF=").append(uf);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .header("accept", "application/json")
                .header("authorization", "Bearer " + token)
                .GET().build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Caso de errado uma exceção é lançada
        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao gerar token! Status: " + response.statusCode() + "\n Body:" + response.body());
        } else {
            return response.body();
        }

    }
}
