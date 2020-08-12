package com.company;

import java.util.Scanner;

public class Program {

    static int tamanho = 15;

    static hash[] tabela = new hash[tamanho];
    static Scanner entrada = new Scanner(System.in);

    public static void create(int key, int position, double note, String name) {
        hash novo;

        novo = new hash();

        novo.key = key;
        novo.note = note;
        novo.name = name;
        novo.next = tabela[position];

        tabela[position] = novo;
    }

    static int calcularPosicaoPorNome(String name) {
        int cod = 0;

        for (char letter : name.toCharArray()) { cod += letter; }

        return cod % tamanho;
    }

	private static void showPositionForUser(hash aux) {
		System.out.println(
			"\n Nome: " + aux.name
				+ "\n Nota: " + aux.note
				+ "\n"
		);
	}

    private static void cadastroAluno() {
        int codigo, posicao;
        double nota;
        String nome;

        System.out.print("Digite o código do aluno: ");
        codigo = entrada.nextInt();

        System.out.print("Digite o nome: ");
        entrada.nextLine();
        nome = entrada.nextLine();

        System.out.print("Digite a nota: ");
        nota = entrada.nextFloat();

        posicao = calcularPosicaoPorNome(nome);
        create(codigo, posicao, nota, nome);
    }

    static void mostrarAlunos() {
        hash auxiliar;

        for (int i = 0; i < tamanho; i++) {
            auxiliar = tabela[i];

            while (auxiliar != null) {
                System.out.println("Posição na lista: " + i);

				showPositionForUser(auxiliar);

				auxiliar = auxiliar.next;
            }
        }

    }

	static void alunosAprovados() {
        hash auxiliar;

        for (int i = 0; i < tamanho; i++) {

            auxiliar = tabela[i];

            while (auxiliar != null) {

                if (auxiliar.note >= 7) {
                    showPositionForUser(auxiliar);

					auxiliar = auxiliar.next;
                } else { return; }

            }

        }
    }

    public static void main(String[] args) {
        int option;

        do {
            System.out.println("\n -- Menu -- \n");

            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Consultar aprovados");
            System.out.println("3 - Consultar todos os alunos");
            System.out.println("4 - Sair \n");

            System.out.print("Escolha uma opção: ");
            option = entrada.nextInt();

            if (option < 1 || option > 4) {
                System.out.println("Opção Inválida!!");
            } else { optionsMenu(option); }

        } while (option != 4);
    }

    private static void optionsMenu(int option) {
        switch (option) {
            case 1:
                cadastroAluno();
            break;

            case 2:
                alunosAprovados();
            break;

            case 3:
                mostrarAlunos();
            break;

        }
    }

    public static class hash {
        int key;
        double note;
        hash next;
        String name;
    }

}
