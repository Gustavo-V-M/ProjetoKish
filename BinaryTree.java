// Guilherme Florio Vieira 10409698
// Arthur Ryuiti Sato Furukawa 10409106
// Gabriel Fuentes de Freitas Yamashita 10408876
// Gustavo Vilela Mitraud 10400866

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node addRecursive(Node root, Token token, Node parent) {
        if (root == null) {
            root = new Node(token);
            root.setParent(parent);
            return root;
        }

        if (token.getId() < root.getToken().getId()) {
            root.setLeft(addRecursive(root.getLeft(), token, root));
        } else if (token.getId() > root.getToken().getId()) {
            root.setRight(addRecursive(root.getRight(), token, root));
        }

        return root;
    }

    public boolean addBST(Token token) {
        if (searchNode(this.root, token) == null) {
            root = addRecursive(root, token, null);
            return true;
        } else {
            System.out.println("O nó " + token.getValue() + " já existe na árvore.");
            return false;
        }
    }

    private Node rotateLeft(Node root){
        Node aux = root.getRight();
        root.setRight(aux.getLeft());
        if (aux.getLeft() != null) {
            aux.getLeft().setParent(root);
        }
        aux.setParent(root.getParent());
        if (root.getParent() == null) {
            setRoot(aux);
        } else if (root == root.getParent().getLeft()) {
            root.getParent().setLeft(aux);
        } else {
            root.getParent().setRight(aux);
        }
        aux.setLeft(root);
        root.setParent(aux);
        return aux;
    }
    public Node callRotateLeft(Node root) {
        return rotateLeft(root);
    }

    private Node rotateRight(Node root){
        Node aux = root.getLeft();
        root.setLeft(aux.getRight());
        if (aux.getRight() != null) {
            aux.getRight().setParent(root);
        }
        aux.setParent(root.getParent());
        if (root.getParent() == null) {
            setRoot(aux);
        } else if (root == root.getParent().getRight()) {
            root.getParent().setRight(aux);
        } else {
            root.getParent().setLeft(aux);
        }
        aux.setRight(root);
        root.setParent(aux);
        return aux;
    }
    public Node callRotateRight(Node root) {
        return rotateRight(root);
    }

    private Node rotateLeftRight(Node root) {
        root.setLeft(rotateLeft(root.getLeft()));
        return rotateRight(root);
    }
    public Node callRotateLeftRight(Node root) {
        return rotateLeftRight(root);
    }

    private Node rotateRightLeft(Node root){
        root.setRight(rotateRight(root.getRight()));
        return rotateLeft(root);
    }
    public Node callRotateRightLeft(Node root) {
        return rotateRightLeft(root);
    }

    public int getIdByKey(String key) {
        Token token = new Token(key, TokenType.IDENTIFIER);
        Node node = searchNode(root, token);
        if (node != null) {
            return node.getToken().getId();
        } else {
            System.out.println("Chave '" + key + "' não encontrada na árvore.");
            return -1; // Retorna -1 se a chave não for encontrada
        }
    }

    public Node balance(Node root) {
        int balanceFactor = root.getBalanceFactor(root);

        System.out.println("Fator de balanceamento do nó " + root.getValue() + ": " + balanceFactor);
        if (balanceFactor > 1) {
            if (root.getLeft().getBalanceFactor(root.getLeft()) >= 0) {
                rotateRight(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação LL");
            }
            else {
                rotateLeftRight(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação LR");
            }
        }
        else if (balanceFactor < -1) {
            if (root.getRight().getBalanceFactor(root.getRight()) <= 0) {
                rotateLeft(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação RR");
            }
            else {
                rotateRightLeft(root);
                System.out.println("Nó " + root.getValue() + " balanceado com rotação RL");
            }
        }
        return root;
    }

    public Node searchNode(Node current, Token token) {
        if (current == null) {
            return null;
        }
        if (current.getToken().getId() == token.getId()) {
            return current;
        }
        Node node = searchNode(current.getLeft(), token);
        if (node == null) {
            node = searchNode(current.getRight(), token);
        }
        return node;
    }
    public int searchNodeComparisons(Node current, Token token) {
        int count = 0;

        while (current != null) {
            count++;

            if (current.getToken().getId() == token.getId()) {
                return count;
            }

            if (token.getId() < current.getToken().getId()) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }

        return -1;
    }


    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            printNodeInfo(node);
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            printNodeInfo(node);
            inOrderTraversal(node.getRight());
        }
    }

    public void posOrderTraversal() {
        posOrderTraversal(root);
        System.out.println();
    }

    private void posOrderTraversal(Node node) {
        if (node != null) {
            posOrderTraversal(node.getLeft());
            posOrderTraversal(node.getRight());
            printNodeInfo(node);
        }
    }

    public void printNodeInfo(Node node) {
        Token token = node.getToken();
        System.out.println("Token: " + token.getValue() + " | Token type: " + token.getType() + " | ID: " + token.getId());
        System.out.println("Left:");
        printChildNodeInfo(node.getLeft());
        System.out.println("Right:");
        printChildNodeInfo(node.getRight());
        System.out.println("isLeaf? " + node.isLeaf());
        System.out.println("isRoot? " + node.isRoot());
        System.out.println("Degree:" + node.getDegree());
        System.out.println("Level:" + node.getLevel(this.root, 0));
        System.out.println("Height:" + node.getHeight(node));
        System.out.println();
    }


    private void printChildNodeInfo(Node childNode) {
        if (childNode != null) {
            Token token = childNode.getToken();
            System.out.println("  Token: " + token.getValue() + " | Token type: " + token.getType() + " | ID: " + token.getId());
            System.out.println("  Left: " + (childNode.getLeft() != null ? "Token: " + childNode.getLeft().getToken().getValue() + " | Token type: " + childNode.getLeft().getToken().getType() + " | ID: " + childNode.getLeft().getToken().getId() : "null"));
            System.out.println("  Right: " + (childNode.getRight() != null ? "Token: " + childNode.getRight().getToken().getValue() + " | Token type: " + childNode.getRight().getToken().getType() + " | ID: " + childNode.getRight().getToken().getId() : "null"));
        } else {
            System.out.println("  null");
        }
    }

}

