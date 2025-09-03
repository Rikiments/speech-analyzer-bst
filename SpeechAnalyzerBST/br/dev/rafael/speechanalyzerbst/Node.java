/**
 *
 * @author Rafael Riki Nascimento de Oliveira
 */

public class Node {
    Palavra elemento;
    Node left, right, parent;

    public Node(Palavra elemento) {
        this.elemento = elemento;
        left = right = parent = null;
    }

    public void mostraNo() {
        System.out.println(elemento.palavra + " (" + elemento.ocorrencia + ")");
    }
}
