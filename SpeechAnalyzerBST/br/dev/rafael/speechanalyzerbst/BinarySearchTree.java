/**
 *
 * @author Rafael Riki Nascimento de Oliveira
 */

import java.io.*;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    // Metodo para verificar se a árvore está vazia
    public boolean isEmpty() {
        return root == null;
    }

    // Metodo para inserir uma nova palavra na árvore
    public void insere(Palavra palavra) {
        Node novo = new Node(palavra);
        if (root == null) {
            root = novo;
        } else {
            insereRec(root, novo);
        }
    }

    // Metodo para inserir um novo nó na árvore
    private void insereRec(Node atual, Node novo) {
        int cmp = novo.elemento.palavra.compareTo(atual.elemento.palavra);
        if (cmp < 0) {
            if (atual.left == null) {
                atual.left = novo;
                novo.parent = atual;
            } else {
                insereRec(atual.left, novo);
            }
        } else if (cmp > 0) {
            if (atual.right == null) {
                atual.right = novo;
                novo.parent = atual;
            } else {
                insereRec(atual.right, novo);
            }
        } else {
            atual.elemento.ocorrencia++; // Palavra repetida
        }
    }

    // Metodo para exibir as palavras em ordem alfabética
    public void emOrdem(Node no) {
        if (no != null) {
            emOrdem(no.left);
            no.mostraNo();
            emOrdem(no.right);
        }
    }

    // Metodo para carregar discurso e inserir as palavras na árvore
    public void lerArq(String nomeArq) {
        try {
            FileReader fr = new FileReader(nomeArq);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.toLowerCase();
                String[] palavras = linha.split("\\s+");
                for (String texto : palavras) {
                    if (!texto.isEmpty()) {
                        insere(new Palavra(texto));
                    }
                }
            }
            br.close();
            System.out.println("Discurso carregado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Metodo para contar o numero de palavras na árvore
    public int contarPalavras() {
        return contarPalavrasRec(root);
    }

    private int contarPalavrasRec(Node no) {
        if (no == null) {
            return 0;
        }
        return no.elemento.ocorrencia +
                contarPalavrasRec(no.left) +
                contarPalavrasRec(no.right);
    }

    // Metodo para buscar uma palavra na árvore
    public Palavra buscarPalavra(String texto) {
        return buscarRec(root, texto.toLowerCase());
    }

    private Palavra buscarRec(Node no, String texto) {
        if (no == null) {
            return null;
        }

        int cmp = texto.compareTo(no.elemento.palavra);

        if (cmp == 0) {
            return no.elemento;
        } else if (cmp < 0) {
            return buscarRec(no.left, texto);
        } else {
            return buscarRec(no.right, texto);
        }
    }

    public int buscarPalavrasDepressao(Node no, BinarySearchTree outraArvore) {
        int count = 0;
        if (no != null) {
            count += buscarPalavrasDepressao(no.left, outraArvore);
            Palavra p = outraArvore.buscarPalavra(no.elemento.palavra);
            if (p != null) {
                System.out.println(no.elemento.palavra + ": " + p.ocorrencia);
                count++;
            }
            count += buscarPalavrasDepressao(no.right, outraArvore);
        }
        return count;
    }

    public Palavra encontrarMaisFrequente(Node no) {
        if (no == null) {
            return null;
        }
        Palavra esquerda = encontrarMaisFrequente(no.left);
        Palavra direita = encontrarMaisFrequente(no.right);
        Palavra maior = no.elemento;

        if (esquerda != null && esquerda.ocorrencia > maior.ocorrencia) {
            maior = esquerda;
        }
        if (direita != null && direita.ocorrencia > maior.ocorrencia) {
            maior = direita;
        }
        return maior;
    }

    public int contarUnicas(Node no) {
        if (no == null) {
            return 0;
        }
        int count = no.elemento.ocorrencia == 1 ? 1 : 0;
        return count + contarUnicas(no.left) + contarUnicas(no.right);
    }

    public int contarTresLetras(Node no) {
        if (no == null) {
            return 0;
        }
        int count = no.elemento.palavra.length() == 3 ? 1 : 0;
        return count + contarTresLetras(no.left) + contarTresLetras(no.right);
    }

}
