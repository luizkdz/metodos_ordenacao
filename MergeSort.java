package nbaTentativa2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    private static int numComparacoes = 0;
    private static int numMovimentacoes = 0;
    private static long executionTime;

    public static void main(String[] args) throws Exception {
        Jogador[] meuVetor = new Jogador[4000];
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
            Jogador[] novoVetor = new Jogador[5000]; // Novo vetor para armazenar os jogadores informados pelo usuário
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
            mergeSort(novoVetor, 0, novoIndice - 1); // Ordenar apenas os jogadores inseridos

            long endTime = System.currentTimeMillis(); // Tempo de fim da ordenação
            executionTime = endTime - startTime; // Tempo de execução em milissegundos

            // Exibir os jogadores ordenados
            for (Jogador jogador : novoVetor) {
                if (jogador != null) {
                    jogador.imprimir();
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());}}
            private static void mergeSort(Jogador[] vetor, int inicio, int fim) {
                if (inicio < fim) {
                    int meio = (inicio + fim) / 2;
                    mergeSort(vetor, inicio, meio);
                    mergeSort(vetor, meio + 1, fim);
                    merge(vetor, inicio, meio, fim);
                }
            }

            private static void merge(Jogador[] vetor, int inicio, int meio, int fim) {
                Jogador[] auxiliar = Arrays.copyOf(vetor, vetor.length);

                int i = inicio;
                int j = meio + 1;
                int k = inicio;

                while (i <= meio && j <= fim) {
                    int comparacaoUniversidade = auxiliar[i].getUniversidade().compareTo(auxiliar[j].getUniversidade());

                    if (comparacaoUniversidade < 0) {
                        vetor[k] = auxiliar[i];
                        i++;
                    } else if (comparacaoUniversidade > 0) {
                        vetor[k] = auxiliar[j];
                        j++;
                    } else {
                        int comparacaoNome = auxiliar[i].getNome().compareTo(auxiliar[j].getNome());
                        if (comparacaoNome < 0) {
                            vetor[k] = auxiliar[i];
                            i++;
                        } else {
                            vetor[k] = auxiliar[j];
                            j++;
                        }
                    }

                    k++;
                    numComparacoes++;
                    numMovimentacoes++;
                }

                while (i <= meio) {
                    vetor[k] = auxiliar[i];
                    i++;
                    k++;
                    numMovimentacoes++;
                }

                while (j <= fim) {
                    vetor[k] = auxiliar[j];
                    j++;
                    k++;
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