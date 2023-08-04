# metodos_ordenacao

Programa feito em java que ordena os registros de jogadores de um arquivo txt aplicando os algoritmos de ordenação e com base em critérios de ordenação.
Bolha
Utilizando vetores, ordene registros de jogadores aplicando o algoritmo de
ordenação
Bubblesort, considerando que a chave de pesquisa seja o atributo
cidadeNascimento do jogador. Em caso de empate, o segundo critério de
ordenação deve ser o atributo
nome do jogador.
Utilize a classe
Jogador especificada e desenvolvida em prática anterior.
Seu programa deve ler um arquivo-texto chamado jogadores.txt que, no
VERDE, localiza-se na pasta /tmp. Você deve preencher um vetor de objetos
da classe
Jogador com os dados dos diversos jogadores da NBA informados
nesse arquivo. Atenção para os dados de entrada, pois em alguns registros
faltam valores e esses devem ser substituídos pela
string “nao informado”, na
saída padrão.
Cada uma das linhas presentes no arquivo indica os dados de um jogador,
separados pelo símbolo ‘,’. Esses dados são, nessa ordem:
-
id do jogador;
- nome do jogador;
- sua altura;
- seu peso;
- universidade que o jogador representa;
- ano de nascimento do jogador;
- nome da cidade em que o jogador nasceu;
- estado em que o jogador nasceu.
Seu programa também deve ler e processar a entrada padrão que é composta
por várias linhas e cada uma contém uma
string indicando o
id do jogador cujos
dados devem ser inseridos no vetor de jogadores a ser ordenado. A última linha
da entrada contém a palavra FIM.
A saída padrão corresponde aos registros ordenados, um por linha. Em cada
linha de saída, escreva todos os dados do registro correspondente obedecendo o
seguinte formato:
[
id ## nome ## altura ## peso ## ano de nascimento ## universidade ##
cidade de nascimento ## estado de nascimento]
Além disso, crie um arquivo de
log na pasta corrente com o nome sua
matrícula_bolha.txt com uma única linha contendo: seu número de matrícula,
tempo de execução de seu algoritmo de ordenação (em milissegundos), número
de comparações realizadas entre os elementos do vetor de jogadores e número
de movimentações realizadas entre os elementos do vetor. Todas as informações
desse arquivo de
log devem ser separadas por uma tabulação ‘\t’.
2. Ordenação por seleção
Repita a questão de ordenação de jogadores da NBA por meio do método
Bubblesort, contudo, aplicando o algoritmo de ordenação por seleção,
considerando como chave de pesquisa o atributo
nome do jogador.
O nome do arquivo de
log dessa questão será sua matrícula_selecao.txt.
3. Ordenação por inserção
Repita a questão de ordenação de jogadores da NBA por meio do método
Bubblesort, contudo, aplicando o algoritmo de ordenação por inserção,
considerando como chave de pesquisa o atributo
anoNascimento. Em caso de
empate, o segundo critério de ordenação deve ser o atributo
nome do jogador.
O nome do arquivo de
log dessa questão será sua matrícula_insercao.txt.
4.
Heapsort
Repita a questão de ordenação de jogadores da NBA por meio do método
Bubblesort, contudo, aplicando o algoritmo de ordenação
heapsort,
considerando como chave de pesquisa o atributo
altura. Em caso de empate, o
segundo critério de ordenação deve ser o atributo
nome do jogador.
O nome do arquivo de
log dessa questão será sua matrícula_heapsort.txt.
5.
Mergesort
Repita a questão de ordenação de jogadores da NBA por meio do método
Bubblesort, contudo, aplicando o algoritmo de ordenação
mergesort,
considerando como chave de pesquisa o atributo
universidade. Em caso de
empate, o segundo critério de ordenação deve ser o atributo
nome do jogador.
O nome do arquivo de
log dessa questão será sua matrícula_mergesort.txt.
6.
Quicksort
Repita a questão de ordenação de jogadores da NBA por meio do método
Bubblesort, contudo, aplicando o algoritmo de ordenação
quicksort,
considerando como chave de pesquisa o atributo
estadoNascimento. Em caso
de empate, o segundo critério de ordenação deve ser o atributo
nome do
jogador.
O nome do arquivo de
log dessa questão será sua matrícula_quicksort.txt.
