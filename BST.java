public class BST {

    private final BinaryTree bsttree;

    public BST(BinaryTree binaryTree) {
        this.bsttree = binaryTree;
    }

    public void setRoot(Node root) {
        bsttree.setRoot(root);
    }

    private void insertNodeBST(Node root, Node newNode) {
        if (root == null) {
            return;
        }

        if (newNode.getToken().getId() < root.getToken().getId()) {
            if (root.getLeft() == null) {
                root.setLeft(newNode);
            } else {
                insertNodeBST(root.getLeft(), newNode);
            }
        } else if (newNode.getToken().getId() > root.getToken().getId()) {
            if (root.getRight() == null) {
                root.setRight(newNode);
            } else {
                insertNodeBST(root.getRight(), newNode);
            }
        } else {
            // O novo nó já existe na árvore, não é necessário inserir novamente
            System.out.println("O nó " + newNode.getToken().getValue() + " já existe na árvore.");
        }
    }

    public Node insert(Node root, Node newNode) {
        insertNodeBST(root, newNode);
        return root;
    }

    public Node delete(Node root, int id) {
        return deleteNodeBST(root, id);
    }

    public Node deleteNodeBST(Node root, int id) {
        if(root == null){return root;}
        // Se o id a ser deletado é menor do que a raiz, vá para a subárvore esquerda
        if (id < root.getToken().getId()) {
            root.setLeft(deleteNodeBST(root.getLeft(), id));
        }
        // Se o id a ser deletado é maior do que a raiz, vá para a subárvore direita
        else if (id > root.getToken().getId()) {
            root.setRight(deleteNodeBST(root.getRight(), id));
        }
        // Se o id é o mesmo que o id da raiz, este é o nó a ser deletado
        else {
            // Nó com apenas um filho ou sem filhos
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            // Nó com dois filhos: Obtenha o sucessor (menor na subárvore direita)
            root.setToken(nodeSucessor(root.getRight()).getToken());

            // Delete o sucessor
            root.setRight(deleteNodeBST(root.getRight(), root.getToken().getId()));
        }
        return root;
    }

    private Node nodeSucessor(Node node) {
        Node current = node;
        // Encontre o nó mais à esquerda
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public Node updateBST(Node root, int id, String newValue) {
        if (root == null) {
            System.out.println("Nó com o ID " + id + " não encontrado na árvore.");
            return null;
        }

        if (id < root.getToken().getId()) {
            return updateBST(root.getLeft(), id, newValue);
        } else if (id > root.getToken().getId()) {
            return updateBST(root.getRight(), id, newValue);
        } else {
            // Encontrou o nó com o ID desejado
            root.getToken().setValue(newValue);
            System.out.println("Nó com o ID " + id + " alterado para o valor: " + newValue);
            return root;
        }
    }

    public Node update(Node root, int id, String newValue) {
        Node foundNode = updateBST(root, id, newValue);
        if (foundNode != null) {
            System.out.println("Nó com o ID " + id + " encontrado na árvore.");
            return foundNode;
        } else {
            System.out.println("Nó com o ID " + id + " não encontrado na árvore.");
            return null;
        }
    }
}
