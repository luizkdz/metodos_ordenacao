package nbaTentativa2; 

import java.util.Scanner;

public class Jogador {

private int id;

private String nome;

private int altura;

private int peso;

private String universidade;

private int anoNascimento;

private String cidadeNascimento;

private String estadoNascimento;

public Jogador() {
id = 0;
nome = "nulo";
altura = 0;
peso = 0;
universidade = "não informada";
anoNascimento = 0;
cidadeNascimento = "nao informada";
estadoNascimento = "nao informada";
}

public Jogador(String id, String nome, String altura, String peso, String universidade, String anoNascimento, String cidadeNascimento, String estadoNascimento) {
    this.setId(id);
    this.setNome(nome);
    this.setAltura(altura);
    this.setPeso(peso);
    this.setUniversidade(universidade);
    this.setAnoNascimento(anoNascimento);
    this.setCidadeNascimento(cidadeNascimento);
    this.setEstadoNascimento(estadoNascimento);
}

public int getId() {

return id;

}

public void setId(String idStr) {
    int idInt = Integer.parseInt(idStr);
    this.id = idInt;
}

public String getNome() {

return nome;

}

public void setNome(String nome) {

this.nome = nome;

}

public int getAltura() {

return altura;

}

public void setAltura(String alturaStr) {
    int alturaInt = -1;
    try {
        alturaInt = Integer.parseInt(alturaStr);
    } catch (NumberFormatException e) {
    	
    }
    this.altura = alturaInt;
}

public int getPeso() {

return peso;

}

public void setPeso(String pesoStr) {
    int pesoInt = -1;
    try {
        pesoInt = Integer.parseInt(pesoStr);
    } catch (NumberFormatException e) {

    }
    this.peso = pesoInt;
}

public String getUniversidade() {

return universidade;

}

public void setUniversidade(String universidade) {

this.universidade = universidade;

}

public int getAnoNascimento() {

return anoNascimento;

}

public void setAnoNascimento(String anoNascimentoStr) {
	int anoNascimentoInt = -1;
   
    try {
        anoNascimentoInt = Integer.parseInt(anoNascimentoStr);
    } catch (NumberFormatException e) {

    }
    this.anoNascimento = anoNascimentoInt;
}

public String getCidadeNascimento() {

return cidadeNascimento;

}

public void setCidadeNascimento(String cidadeNascimento) {

this.cidadeNascimento = cidadeNascimento;

}

public String getEstadoNascimento() {

return estadoNascimento;

}

public void setEstadoNascimento(String estadoNascimento) {

this.estadoNascimento = estadoNascimento;

}

public void imprimir() {
	String alturaStr = (altura != -1) ? Integer.toString(altura) : "nao informado";
    String pesoStr = (peso != -1) ? Integer.toString(peso) : "nao informado";
    String anoNascimentoStr = (anoNascimento != -1) ? Integer.toString(anoNascimento) : "nao informado";
	
	System.out.println("[" +this.id + "]" +" ## " + this.nome + " ## " + alturaStr + " ## " + pesoStr + " ## " +  anoNascimentoStr +" ## " + this.universidade  + " ## " + this.cidadeNascimento + " ## " + this.estadoNascimento +" ##");
}

}