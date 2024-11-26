package br.com.arhoffmann.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GenerateAuthenticationToken {
    private static String token;

    // Com base em "https://developers.celcoin.com.br/reference/post_v5-token"
    private static final String CLIENT_ID = "41b44ab9a56440.teste.celcoinapi.v5";
    private static final String CLIENT_SECRET = "e9d15cde33024c1494de7480e69b7a18c09d7cd25a8446839b3be82a56a044a3";
    private static final String TOKEN_URL = "https://sandbox.openfinance.celcoin.dev/v5/token";

    // Gerando token quando null or empty
    public static String getToken() throws Exception {
        if (token == null || token.isEmpty()) {
            token = generateAuthenticationToken();

            // Caso não seja possivel gerar o token
            if (token == null || token.isEmpty()) {
                throw new RuntimeException("Não foi possível gerar o Token!");
            }
        }
        return token;
    }

    // Pegando somente o token do json
    private static String parseToken2(String responseBody) throws Exception {
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.get("access_token").getAsString();
    }

    // Consumindo API "Gera o token para autenticação dos endpoints da API"
    private static String generateAuthenticationToken() throws Exception {
        String boundary = "-----011000010111000001101001";
        String body = String.format(
                "--%s\r\nContent-Disposition: form-data; name=\"client_id\"\r\n\r\n%s\r\n--%s\r\nContent-Disposition: form-data; name=\"grant_type\"\r\n\r\n%s\r\n--%s\r\nContent-Disposition: form-data; name=\"client_secret\"\r\n\r\n%s\r\n--%s--",
                boundary, CLIENT_ID, boundary, "client_credentials", boundary, CLIENT_SECRET, boundary
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOKEN_URL))
                .header("accept", "application/json")
                .header("content-type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Caso de errado uma exceção é lançada
        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao gerar token! Status: " + response.statusCode() + "\n Body:" + response.body());
        } else {
            return parseToken2((response.body()));
        }

    }
}
