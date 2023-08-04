package nbaTentativa2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Selecao {
    private static int numComparacoes = 0;
    private static int numMovimentacoes = 0;
    private static long executionTime;

    public static void main(String[] args) throws Exception {
        Jogador[] meuVetor = new Jogador[16000];
        int indice = 0;
        String path = "C:\\Users\\luizg\\Downloads\\jogadores.txt";
        BufferedReader conteudoCSV = null;
        String csvSeparadorCampo = ",";
        try {
            conteudoCSV = new BufferedReader(new FileReader(path));
            conteudoCSV.readLine();
            String linha;
            String[] vect;
            Scanner scanner = new Scanner(System.in);
            while ((linha = conteudoCSV.readLine()) != null) {
                vect = linha.split(csvSeparadorCampo, -1);
                Jogador newJogador = new Jogador(checkNull(vect[0]), checkNull(vect[1]), checkNull(vect[2]),
                        checkNull(vect[3]), checkNull(vect[4]), checkNull(vect[5]), checkNull(vect[6]), checkNull(vect[7]));
                meuVetor[indice] = newJogador;
                indice++;
            }
            conteudoCSV.close();
            String input = scanner.nextLine();
            Jogador[] novoVetor = new Jogador[14000]; // Apenas os jogadores inseridos pelo usuário
            int novoIndice = 0;
            while (!input.equalsIgnoreCase("FIM")) {
                int numeroLido = Integer.parseInt(input);

                // Adicionar jogador no índice encontrado
                Jogador novoJogador = buscarJogadorPorId(meuVetor, numeroLido);
                if (novoJogador != null) {
                    novoVetor[novoIndice] = novoJogador;
                    novoIndice++;
                } else {
                    System.out.println("Jogador com o ID " + numeroLido + " não encontrado.");
                }

                input = scanner.nextLine(); // Ler próxima entrada
            }

            if (novoIndice == 0) {
                System.out.println("Nenhum jogador adicionado. Encerrando o programa.");
                return;
            }

            long startTime = System.currentTimeMillis(); // Tempo de início da ordenação
            selectionSort(novoVetor, novoIndice);

            long endTime = System.currentTimeMillis(); // Tempo de fim da ordenação
            executionTime = endTime - startTime; // Tempo de execução em milissegundos

            // Exibir os jogadores ordenados
            for (Jogador jogador : novoVetor) {
                if (jogador != null) {
                    jogador.imprimir();
                }
            }

        }catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Tempo de execução: " + executionTime + " ms");
            System.out.println("Número de comparações: " + numComparacoes);
            System.out.println("Número de movimentações: " + numMovimentacoes);
        }
    }

    private static void selectionSort(Jogador[] jogadores, int tamanho) {
        for (int i = 0; i < tamanho - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tamanho; j++) {
                if (jogadores[j].getNome().compareToIgnoreCase(jogadores[minIndex].getNome()) < 0) {
                    minIndex = j;
                }
                numComparacoes++;
            }
            Jogador temp = jogadores[minIndex];
            jogadores[minIndex] = jogadores[i];
            jogadores[i] = temp;
            numMovimentacoes++;
        }
    }

    private static Jogador buscarJogadorPorId(Jogador[] jogadores, int id) {
        for (Jogador jogador : jogadores) {
            if (jogador != null && jogador.getId() == id) {
                return jogador;
            }
        }
        return null;
    }

    private static String checkNull(String value) {
        return value.isEmpty() ? "nao informado" : value;
    }
}