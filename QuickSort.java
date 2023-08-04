package nbaTentativa2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class QuickSort {
    private static int numComparacoes = 0;
    private static int numMovimentacoes = 0;
    private static long executionTime;

    public static void main(String[] args) throws Exception {
        Jogador[] meuVetor = new Jogador[16000];
        int indice = 0;
        String path = "C:\\Users\\luizg\\Downloads\\jogadores (1).txt";
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
            Jogador[] novoVetor = new Jogador[468]; // Apenas os jogadores inseridos pelo usuário
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
            quickSort(novoVetor, 0, novoIndice - 1);

            long endTime = System.currentTimeMillis(); // Tempo de fim da ordenação
            executionTime = endTime - startTime; // Tempo de execução em milissegundos

            // Exibir os jogadores ordenados
            for (Jogador jogador : novoVetor) {
                if (jogador != null) {
                    jogador.imprimir();
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Tempo de execução: " + executionTime + " ms");
            System.out.println("Número de comparações: " + numComparacoes);
            System.out.println("Número de movimentações: " + numMovimentacoes);
        }
    }

    private static void quickSort(Jogador[] jogadores, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = partition(jogadores, inicio, fim);
            quickSort(jogadores, inicio, indicePivo - 1);
            quickSort(jogadores, indicePivo + 1, fim);
        }
    }

    private static int partition(Jogador[] jogadores, int inicio, int fim) {
        Jogador pivo = jogadores[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (compareJogadores(jogadores[j], pivo) <= 0) {
                i++;
                swap(jogadores, i, j);
            }
        }
        swap(jogadores, i + 1, fim);
        return i + 1;
    }

    private static void swap(Jogador[] jogadores, int i, int j) {
        Jogador temp = jogadores[i];
        jogadores[i] = jogadores[j];
        jogadores[j] = temp;
        numMovimentacoes++; // Incrementar o número de movimentações
    }

    private static int compareJogadores(Jogador jogador1, Jogador jogador2) {
        // Verificar nulidade dos jogadores
        if (jogador1 == null && jogador2 == null) {
            numComparacoes++;
            return 0;
        } else if (jogador1 == null) {
            numComparacoes++;
            return 1;
        } else if (jogador2 == null) {
            numComparacoes++;
            return -1;
        }

        numComparacoes++;
        int comparacaoPorEstado = jogador1.getEstadoNascimento().compareTo(jogador2.getEstadoNascimento());
        if (comparacaoPorEstado == 0) {
            // Se houver empate no estado de nascimento, comparar pelo atributo nome
            return jogador1.getNome().compareTo(jogador2.getNome());
        } else {
            return comparacaoPorEstado;
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