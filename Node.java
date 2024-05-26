public class Node {
    private Token token;
    private Node parent;
    private Node right;
    private Node left;
    private int balanceFactor;
    private int id;

    public Node(Token token) {
        this.token = token;
        right = null;
        left = null;
        parent = null;
        balanceFactor = 0;
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

    private boolean isLeaf() {
        if (getRight() == null && getLeft() == null) {
            return true;
        } else {
            return false;
        }
    }
    public int getBalanceFactor(Node node){
        if (node == null) {
            return 0;
        }
        return getNodeHeight(node.getLeft(), 0) - getNodeHeight(node.getRight(), 0);
    }

    public int updateBalanceFactor() {
        if (this == null) {
            return 0;
        }
        this.balanceFactor = getNodeHeight(this.getLeft(), 0) - getNodeHeight(this.getRight(), 0);
        return this.balanceFactor;
    }

    public int getNodeHeight(Node node, int height) {
        if (node == null) {
            return height;
        }

        int leftHeight = getNodeHeight(node.getLeft(), height + 1);
        int rightHeight = getNodeHeight(node.getRight(), height + 1);

        return Math.max(leftHeight, rightHeight);
    }

    public Token getToken() {
        return token;
    }
    public void setToken(Token token){
        this.token = token;
    }


    public int getDegree() {
        if (isLeaf() == true) {
            return -1;
        } else if (hasBothNodes() == true) {
            return 2;
        } else {
            return 1;
        }
    }

    public int getHeight() {
        if (isLeaf() == true) {
            return 0;
        } else if (right == null && left != null) {
            return 1 + left.getHeight();
        } else if (right != null && left == null) {
            return 1 + right.getHeight();
        } else if (right.getHeight() >= left.getHeight()) {
            return 1 + right.getHeight();
        } else {
            return 1 + left.getHeight();
        }
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
