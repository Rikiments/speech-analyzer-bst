/**
 *
 * @author Rafael Riki Nascimento de Oliveira
 */

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        menu(binarySearchTree);
    }

    public static void menu(BinarySearchTree binarySearchTree) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Carregar discurso");
            System.out.println("2. Contador de palavras");
            System.out.println("3. Buscar palavra");
            System.out.println("4. Exibir as palavras do discurso em ordem alfabética");
            System.out.println("5. Verificar sinais de depressão");
            System.out.println("6. Estatísticas sobre o texto");
            System.out.println("7. Sair ");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    binarySearchTree.lerArq("Discurso.txt");
                    break;
                case 2:
                    if (!binarySearchTree.isEmpty()) {
                        int total = binarySearchTree.contarPalavras();
                        System.out.println("Total de palavras no discurso: " + total);
                    } else {
                        System.out.println("Você precisa carregar o discurso primeiro.");
                    }
                    break;
                case 3:
                    if (!binarySearchTree.isEmpty()) {
                        System.out.print("Digite a palavra a buscar: ");
                        String palavraBusca = scanner.next().toLowerCase();
                        Palavra resultado = binarySearchTree.buscarPalavra(palavraBusca);

                        if (resultado != null) {
                            System.out.println("A palavra | " + resultado.palavra + " | apareceu "
                                    + resultado.ocorrencia + " vez(es).");
                        } else {
                            System.out.println("Palavra não encontrada no discurso.");
                        }
                    } else {
                        System.out.println("Você precisa carregar o discurso primeiro.");
                    }
                    break;
                case 4:
                    if (!binarySearchTree.isEmpty()) {
                        System.out.println("Palavras em ordem alfabética:\n");
                        binarySearchTree.emOrdem(binarySearchTree.getRoot());
                    } else {
                        System.out.println("Você precisa carregar o discurso primeiro.");
                    }
                    break;
                case 5:
                    if (!binarySearchTree.isEmpty()) {
                        // Cria uma outra árvore para as palavras de depressão
                        BinarySearchTree arqDepressao = new BinarySearchTree();
                        arqDepressao.lerArq("PalavrasDepressao.txt");

                        System.out.println("\n=== Verificação de sinais de depressão ===");

                        // Percorre a árvore de palavras de depressão e verificar se existem no discurso
                        int palavrasEncontradas = arqDepressao.buscarPalavrasDepressao(arqDepressao.getRoot(),
                                binarySearchTree);

                        if (palavrasEncontradas == 0) {
                            System.out.println("Nenhuma palavra associada à depressão foi encontrada.");
                        }
                    } else {
                        System.out.println("Você precisa carregar o discurso primeiro.");
                    }
                    break;

                case 6:
                    if (!binarySearchTree.isEmpty()) {
                        Palavra maisFrequente = binarySearchTree.encontrarMaisFrequente(binarySearchTree.getRoot());
                        int palavrasUnicas = binarySearchTree.contarUnicas(binarySearchTree.getRoot());
                        int palavrasTresLetras = binarySearchTree.contarTresLetras(binarySearchTree.getRoot());

                        System.out.println("\n=== Estatísticas do texto ===");
                        System.out.println("Palavra mais frequente: " + maisFrequente.palavra + " ("
                                + maisFrequente.ocorrencia + " vezes)");
                        System.out.println("Quantidade de palavras únicas: " + palavrasUnicas);
                        System.out.println("Quantidade de palavras com 3 letras: " + palavrasTresLetras);
                    } else {
                        System.out.println("Você precisa carregar o discurso primeiro.");
                    }
                    break;
                case 7:
                    System.out.println("\n=== INTEGRANTES ===");
                    System.out.println("Vitor Costa Lemos - 10438932");
                    System.out.println("Jose Pedro Bitetti Tkatchuk - 10427372");
                    System.out.println("Rafael Riki - 10418331");
                    System.out.println("Link do video aki!");
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 7);

        scanner.close();
    }
}