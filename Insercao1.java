package nbaTentativa2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Insercao1 {
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

            Jogador[] novoVetor = new Jogador[5000];
            int novoIndice = 0;

            while (!input.equalsIgnoreCase("FIM")) {
                int numeroLido = Integer.parseInt(input);

                Jogador novoJogador = buscarJogadorPorId(meuVetor, numeroLido);

                if (novoJogador != null) {
                    novoVetor[novoIndice] = novoJogador;
                    novoIndice++;
                } else {
                    System.out.println("Jogador com o ID " + numeroLido + " não encontrado.");
                }

                input = scanner.nextLine(); // Ler próxima entrada
            }

            long startTime = System.currentTimeMillis(); // Tempo de início da ordenação
            insertionSort(novoVetor, novoIndice);

            long endTime = System.currentTimeMillis(); // Tempo de fim da ordenação
            executionTime = endTime - startTime; // Tempo de execução em milissegundos

            // Exibir os jogadores ordenados
            for (int i = 0; i < novoIndice; i++) {
                novoVetor[i].imprimir();
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV.");
        }
    }public static void insertionSort(Jogador[] vetor, int tamanho) {
        for (int i = 1; i < tamanho; i++) {
            Jogador chave = vetor[i];
            int j = i - 1;

            while (j >= 0 && compareJogadores(vetor[j], chave) > 0) {
                vetor[j + 1] = vetor[j];
                j--;
                numComparacoes++;
                numMovimentacoes++;
            }

            vetor[j + 1] = chave;
            numMovimentacoes++;
        }
    }

    public static int compareJogadores(Jogador jogador1, Jogador jogador2) {
        int compareAnoNascimento = Integer.compare(jogador1.getAnoNascimento(), jogador2.getAnoNascimento());
        if (compareAnoNascimento != 0) {
            return compareAnoNascimento;
        } else {
            return jogador1.getNome().compareTo(jogador2.getNome());
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

    public static String checkNull(String field) {
        return field.isEmpty() ? "não informado" : field;
    }}