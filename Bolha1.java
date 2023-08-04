package nbaTentativa2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bolha1 {
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
            bubbleSort(novoVetor);

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
        String nomeArquivoLog = System.getProperty("user.dir") + "/" + "800815_bolha.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivoLog))) {
            // Escrever os dados no arquivo de log separados por tabulação
            writer.write("800815" + "\t" + executionTime + "\t" + numComparacoes + "\t" + numMovimentacoes);
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo de log: " + e.getMessage());
        }
    
    }

    private static void bubbleSort(Jogador[] jogadores) {
        int n = jogadores.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareJogadores(jogadores[j], jogadores[j + 1]) > 0) {
                    // Swap jogadores[j] with jogadores[j+1]
                    Jogador temp = jogadores[j];
                    jogadores[j] = jogadores[j + 1];
                    jogadores[j + 1] = temp;
                    numMovimentacoes++; // Incrementar o número de movimentações
                }
            }
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
        int comparacaoPorCidade = jogador1.getCidadeNascimento().compareTo(jogador2.getCidadeNascimento());
        if (comparacaoPorCidade == 0) {
            // Se houver empate na cidade de nascimento, comparar pelo atributo nome
            return jogador1.getNome().compareTo(jogador2.getNome());
        } else {
            return comparacaoPorCidade;
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