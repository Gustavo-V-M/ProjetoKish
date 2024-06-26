// Guilherme Florio Vieira 10409698
// Arthur Ryuiti Sato Furukawa 10409106
// Gabriel Fuentes de Freitas Yamashita 10408876
// Gustavo Vilela Mitraud 10400866

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
    }
    parse();
    return tokens;
  }

  private TokenType getTokenType(String value) {
    if (value.equals("#")) {
      return TokenType.COMMENT;
    } else if (value.equals("(") || value.strip().endsWith("(")) {
      return TokenType.SCOPE_START;
    } else if (value.strip().endsWith(")")) {
      return TokenType.SCOPE_END;
    } else if (value.contains("=")) {
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
      Node newNode;
      if (token.getType() == TokenType.SCOPE_START) {
        newNode = parseScope(token);
      } else if (token.getType() == TokenType.KEY) {
        newNode = parseKey(token);
      } else {
        newNode = new Node(token);
      }

      if (binaryTree.getRoot() == null) {
        binaryTree.setRoot(newNode);
      } else {
        bst.insert(binaryTree.getRoot(), newNode);
        avl.insertBalance(binaryTree.getRoot(), newNode);
      }
    }
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
    } else if (token.type == TokenType.IDENTIFIER) {
      return null;
    }
     else {
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
