import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final BinaryTree binaryTree;
    private final BST bst;
    private final AVL avl;

    private final List<Node> tokens;
    private final List<Token> tokensPreparse;

    public Tokenizer(BinaryTree binaryTree, BST bst, AVL avl) {
        this.bst = bst;
        this.avl = avl;
        this.binaryTree = binaryTree;
        tokens = new ArrayList<>();
        tokensPreparse = new ArrayList<>();
    }

    public List<Node> tokenize(String inputText) {
        String lines[] = inputText.split("\n");
        for (String line : lines) {
            TokenType type = getTokenType(line);
            Token curToken = new Token(line, type);
            tokensPreparse.add(curToken);
            System.out.println("Token: " + curToken.getValue() + " - Type: " + curToken.getType());
        }
        parse();
        return tokens;
    }


    private TokenType getTokenType(String value) {
        if (value.equals("#")) {
            return TokenType.COMMENT;
        } else if (value.equals("(") || value.strip().endsWith("(")) {
            return TokenType.SCOPE_START;
        } else if (value.equals(")")) {
            return TokenType.SCOPE_END;
        } else if (value.contains("=") || value.strip().endsWith(")")) {
            return TokenType.KEY;
        } else {
            return TokenType.IDENTIFIER;
        }
    }

    public List<Node> getTokens() {
        return tokens;
    }

    private void parse() {
        for (Token token : tokensPreparse) {
            Node newNode = new Node(token);
            if (binaryTree.getRoot() == null) {
                binaryTree.setRoot(newNode);
                System.out.println("Root: " + newNode.getToken().getValue());
            } else {
                bst.insert(binaryTree.getRoot(), newNode);
                avl.insert(binaryTree.getRoot(), newNode);
                System.out.println("Inserted: " + newNode.getToken().getValue());
            }
        }
        binaryTree.preOrderTraversal();
        binaryTree.inOrderTraversal();
        binaryTree.posOrderTraversal();
    }


    private Node parseKey(Token token) {
        int symbolPos = token.value.indexOf("=");
        String key = token.value.substring(0, symbolPos);
        String value = token.value.substring(symbolPos + 1);
        Node root = new Node(new Token("=", TokenType.SYMBOL));
        Node left = new Node(new Token(key, TokenType.IDENTIFIER));
        Node right = new Node(new Token(value, TokenType.VALUE));
        root.setLeft(left).setRight(right);
        return root;
    }

    private Node parseScope(Token token) {
        if (token == null) {
            return null;
        } else if (token.type == TokenType.SCOPE_END) {
            return null;
        } else if (token.type == TokenType.KEY) {
            return parseKey(token);
        } else {
            int tokenPos = tokensPreparse.indexOf(token);
            int scopeStartPos = token.value.indexOf("(");
            String indentifier = token.value.substring(0, scopeStartPos);
            Node root = new Node(new Token(indentifier, TokenType.IDENTIFIER));
            Node left = parseScope(tokensPreparse.get(tokenPos + 1));
            Node right = parseScope(tokensPreparse.get(tokenPos + 2));
            root.setLeft(left).setRight(right);
            return root;
        }
    }
}
