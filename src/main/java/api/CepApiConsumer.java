package api;

import com.google.gson.Gson;
import model.Address;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class CepApiConsumer {
    public Address getAddress(String cep) throws IOException, InterruptedException {
        //configura a URL da api
        var url = "http://viacep.com.br/ws/".concat(cep).concat("/json/");

        //cria o objeto responsavel pela request
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url));

        //cria o client para o request
        var httpCLient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(8))
                .build();

        //cria o objeto response da request
        var response = httpCLient.send(request.build(), HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        //biblioteca que converter o formato JSON em objeto Java
        var gson = new Gson();

        //conversao do formato JSON para o objeto address
        var address = gson.fromJson(response.body(), Address.class);

        return address;
    }
}
