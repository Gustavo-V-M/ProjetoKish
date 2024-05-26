public class Token {
    private static int nextId = 0;
    private int id;
    String value;
    TokenType type;

    public Token(String value, TokenType type) {
        this.id = nextId++;
        this.value = value;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String setValue(String value){
        return value;
    }

    public TokenType getType() {
        return type;
    }
    @Override
    public String toString() {
        return "Token(value='" + value + "', type=" + type + ")";
    }

}

enum TokenType {
    IDENTIFIER,
    SYMBOL,
    COMMENT,
    WHITESPACE,
    NEWLINE,
    SCOPE_START,
    SCOPE_END,
    KEY,
    VALUE
}

