public class Node {
    private Token token;
    private Node parent;
    private Node right;
    private Node left;
    private int balanceFactor;
    private int id;
    private final Node root;

    public Node(Token token) {
        this.token = token;
        right = null;
        left = null;
        parent = null;
        balanceFactor = 0;
        root = null;
    }

    public Node setRight(Node right) {
        this.right = right;
        if (right != null) {
            this.right.setParent(right);
        }
        return this;
    }

    public Node setLeft(Node left) {
        this.left = left;
        if (left != null) {
            this.left.setParent(this);
        }
        return this;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {return parent;}

    public void setId(int id) {
        this.id = id;
    }
    public void setBalanceFactor(int balanceFactor){
        this.balanceFactor = balanceFactor;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getRoot() {
        return root;
    }

    public String getValue() {
        return token.getValue();
    }

    private boolean hasBothNodes() {
        if (getRight() != null && getLeft() != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        if (right == null && left == null) {
            return true;
        }
        return false;
    }
    public int getBalanceFactor(Node node){
        if (node == null) {
            return 0;
        }
        return getNodeHeight(node.getLeft()) - getNodeHeight(node.getRight());
    }

    public int getDegree() {
        if (isLeaf() == true) {
            return 0;
        } else if (hasBothNodes() == true) {
            return 2;
        } else {
            return 1;
        }
    }

    public int getLevel(Node node, int level) {
        if (node == null) {
            return 0;
        }
        if (node.getValue().equals(this.token.getValue())) {
            return level;
        }
        int downlevel = getLevel(node.left, level + 1);
        if (downlevel != 0) {
            return downlevel;
        }
        downlevel = getLevel(node.right, level + 1);
        return downlevel;
    }

    public int updateBalanceFactor() {
        if (this == null) {
            return 0;
        }
        this.balanceFactor = getNodeHeight(this.getLeft()) - getNodeHeight(this.getRight());
        return this.balanceFactor;
    }

    public int getNodeHeight(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getNodeHeight(node.getLeft());
        int rightHeight = getNodeHeight(node.getRight());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public Token getToken() {
        return token;
    }
    public void setToken(Token token){
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        print.append("Token: ")
                .append(token.value)
                .append(" | ")
                .append("Token type: ")
                .append(token.type)
                .append(" | ")
                .append(id)
                .append("\n")
                .append("Left: ")
                .append((left == null) ? "null\n" : left.toString())
                .append("Right: ")
                .append((right == null) ? "null\n" : right.toString());

        return print.toString();
    }

}