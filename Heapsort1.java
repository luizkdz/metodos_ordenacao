package nbaTentativa2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Heapsort1 {
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
            heapsort(novoVetor, novoIndice);

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

    private static void heapsort(Jogador[] jogadores, int n) {
        // Construir o heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(jogadores, n, i);
        }

        // Extrair os elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            // Mover a raiz (maior elemento) para o final do array
            Jogador temp = jogadores[0];
            jogadores[0] = jogadores[i];
            jogadores[i] = temp;
            numMovimentacoes++; // Incrementar o número de movimentações

            // Chamar heapify na subárvore reduzida
            heapify(jogadores, i, 0);
        }
    }

    private static void heapify(Jogador[] jogadores, int n, int i) {
        int largest = i; // Inicializar o maior como a raiz
        int left = 2 * i + 1; // Filho esquerdo
        int right = 2 * i + 2; // Filho direito

        // Se o filho esquerdo é maior que a raiz
        if (left < n && compareJogadores(jogadores[left], jogadores[largest]) > 0) {
            largest = left;
        }

        // Se o filho direito é maior que o maior atual
        if (right < n && compareJogadores(jogadores[right], jogadores[largest]) > 0) {
            largest = right;
        }

        // Se o maior não é a raiz
        if (largest != i) {
            // Trocar a raiz com o maior
            Jogador temp = jogadores[i];
            jogadores[i] = jogadores[largest];
            jogadores[largest] = temp;
            numMovimentacoes++; // Incrementar o número de movimentações

            // Chamar heapify recursivamente na subárvore afetada
            heapify(jogadores, n, largest);
        }
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
        int comparacaoPorAltura = Double.compare(jogador1.getAltura(), jogador2.getAltura());
        if (comparacaoPorAltura == 0) {
            // Se houver empate na altura, comparar pelo atributo nome
            return jogador1.getNome().compareTo(jogador2.getNome());
        } else {
            return comparacaoPorAltura;
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