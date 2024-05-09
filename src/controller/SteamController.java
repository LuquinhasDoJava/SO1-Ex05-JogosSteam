package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SteamController {
    private List<SteamGame> games;

    public SteamController() {
        games = new ArrayList<>();
    }

    public void carregarJogos(String patch) {
        int x = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(patch))) {
            String linha;
            boolean firstlinha = true;
            while ((linha = reader.readLine()) != null) {
                x++;
                if (firstlinha) {
                    firstlinha = false;
                    continue; // Ignorar o cabeçalho
                }
                String[] partes = linha.split(",");
                String nome = partes[0];
                int ano;
                try {
                    ano = Integer.parseInt(partes[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido na linha "+x+": " + linha);
                    continue;
                }
                String mes = partes[2].toLowerCase();
                double avgPlayers;
                if (partes[3].equalsIgnoreCase("N/A")) {
                    avgPlayers = -1; // Ou qualquer outro valor que represente "N/A"
                } else {
                    try {
                        avgPlayers = Double.parseDouble(partes[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Média de jogadores inválida na linha "+x+": " + linha);
                        continue;
                    }
                }
                games.add(new SteamGame(nome, ano, mes, avgPlayers));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void filtrarJogo(int ano, String mes, double minAvg) {
        for (SteamGame game : games) {
            if (game.getYear() == ano && game.getMonth().equalsIgnoreCase(mes) && game.getAvgPlayers() >= minAvg) {
                System.out.println(game.getName() + " | " + game.getAvgPlayers());
            }
        }
    }

    public void filtrarSalvarArquvio(int ano, String mes, String diretorio, String nomeArq) {
        File dir = new File(diretorio);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Diretório inválido.");
            return;
        }

        File arq = new File(dir, nomeArq);
        try (PrintWriter writer = new PrintWriter(new FileWriter(arq))) {
            for (SteamGame game : games) {
                if (game.getYear() == ano && game.getMonth().equalsIgnoreCase(mes)) {
                    writer.println(game.getName() + ";" + game.getAvgPlayers());
                }
            }
            System.err.println("Arquivo salvo com sucesso: " + arq.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}