package view;

import controller.SteamController;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        SteamController controller = new SteamController();
        Scanner scanner = new Scanner(System.in);
        controller.carregarJogos("C:\\Teste\\SteamCharts.csv");
        System.out.println("Digite o ano desejado:");
        int ano = scanner.nextInt();
        System.out.println("Digite o mês desejado:");
        String mes = scanner.next();
        System.out.println("Digite a media desejada:");
        double minAvg = scanner.nextDouble();
        controller.filtrarJogo(ano, mes, minAvg);

        System.out.println("Digite o ano que deseja salvar:");
        ano = scanner.nextInt();
        System.out.println("Digite o mês que desejada salvar:");
        mes = scanner.next();


       controller.filtrarSalvarArquvio(ano, mes, "C:\\Teste", "teste.csv");
    }
}
