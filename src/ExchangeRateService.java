import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {


        private static final String API_KEY = "623c999455944d92120e0542";
        private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
        private final HttpClient httpClient;

        public ExchangeRateService() {
            this.httpClient = HttpClient.newHttpClient();
        }

        /**
         * Converte um valor de uma moeda para outra.
         *
         * @param from   Moeda de origem.
         * @param to     Moeda de destino.
         * @param amount Valor a ser convertido.
         * @return Valor convertido ou -1 em caso de erro.
         */
        public double convert(Currency from, Currency to, double amount) {
            try {
                String url = BASE_URL + API_KEY + "/pair/" + from + "/" + to;
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    String responseBody = response.body();
                    // Usa Gson para converter o JSON para objeto
                    Gson gson = new Gson();
                    ExchangeRateResponse exchangeRateResponse = gson.fromJson(responseBody, ExchangeRateResponse.class);

                    if (exchangeRateResponse != null && exchangeRateResponse.getConversionRate() > 0) {
                        double result = exchangeRateResponse.getConversionRate() * amount;

                        // Exibição formatada
                        System.out.printf(
                                "Moeda de origem: %s (%.2f)%nMoeda de destino: %s%nTaxa de conversão: %.4f%nValor convertido: %.2f%n",
                                exchangeRateResponse.getBaseCode(),
                                amount,
                                exchangeRateResponse.getTargetCode(),
                                exchangeRateResponse.getConversionRate(),
                                result
                        );

                        return result;
                    } else {
                        throw new IllegalArgumentException("Taxa de câmbio inválida.");
                    }
                } else {
                    System.out.println("Erro ao obter taxa de câmbio. Código: " + response.statusCode());
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            return -1;
        }
}
