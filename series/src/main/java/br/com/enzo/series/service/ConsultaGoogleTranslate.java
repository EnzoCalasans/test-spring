package br.com.enzo.series.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaGoogleTranslate {

    private static final String API_KEY = System.getenv("GOOGLETRANSLATE_APIKEY"); // coloque sua chave da API aqui

    public static String obterTraducao(String texto) {
        try {
            String url = "https://translation.googleapis.com/language/translate/v2"
                    + "?key=" + API_KEY
                    + "&q=" + URLEncoder.encode(texto, "UTF-8")
                    + "&target=pt";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Usa Gson para interpretar o JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            return jsonObject
                    .getAsJsonObject("data")
                    .getAsJsonArray("translations")
                    .get(0)
                    .getAsJsonObject()
                    .get("translatedText")
                    .getAsString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao traduzir: " + e.getMessage();
        }
    }
}
