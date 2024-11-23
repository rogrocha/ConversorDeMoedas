import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        while (true) {
            System.out.println("\nConversor de Moedas:");
            System.out.println("1. Converter de Dólar para Real");
            System.out.println("2. Converter de Real para Dólar");
            System.out.println("3. Converter de Dólar para Euro");
            System.out.println("4. Converter de Euro para Dólar");
            System.out.println("5. Converter de Dólar para Libra Esterlina");
            System.out.println("6. Converter de Libra Esterlina para Dólar");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Concluído");
                break;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();


            double resultado = 0;
            switch (opcao) {
                case 1 -> resultado = exchangeRateService.convert(Currency.USD, Currency.BRL, valor);
                case 2 -> resultado = exchangeRateService.convert(Currency.BRL, Currency.USD, valor);
                case 3 -> resultado = exchangeRateService.convert(Currency.USD, Currency.EUR, valor);
                case 4 -> resultado = exchangeRateService.convert(Currency.EUR, Currency.USD, valor);
                case 5 -> resultado = exchangeRateService.convert(Currency.USD, Currency.GBP, valor);
                case 6 -> resultado = exchangeRateService.convert(Currency.GBP, Currency.USD, valor);
                default -> System.out.println("Opção inválida.");
            }

            if (resultado != -1) {
                System.out.printf("Resultado da conversão: %.2f%n", resultado);
            }
        }

        scanner.close();
    }
}
