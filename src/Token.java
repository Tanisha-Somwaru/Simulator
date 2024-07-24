public class Token {
    // Variable declarations.
    public enum KeyWord {REGISTER, NUMBER, NEWLINE, MATH, ADD, SUBTRACT, MULTIPLY, AND, OR, NOT, XOR, COPY, HALT, BRANCH, JUMP, CALL, PUSH, LOAD,
        RETURN, STORE, PEEK, POP, INTERRUPT, EQUAL, UNEQUAL, GREATER, LESS, GREATEROREQUAL, LESSOREQUAL, SHIFT, LEFT, RIGHT}
    private String tokenValue;
    private int number;
    private String register;
    int lineNumber;
    int charPosition;
    KeyWord type;

    /**
     * Constructor.
     * @param type
     */
    public Token (KeyWord type){
        this.type = type;
        number = 0;
        register = "R";
    }

    /**
     * Constructor.
     * @param tokenType
     * @param lineNum
     * @param position
     */
    public Token(KeyWord tokenType, int lineNum, int position){
        type = tokenType;
        lineNumber = lineNum;
        this.charPosition = position;
    }

    /**
     * Constructor.
     * @param tokenType
     * @param value
     * @param lineNum
     * @param position
     */
    public Token(KeyWord tokenType, String value, int lineNum, int position){
        type = tokenType;
        tokenValue = value;
        lineNumber = lineNum;
        this.charPosition = position;
    }

    /**
     * This method returns the string representation of the KeyWord and value of the token
     * which can be a number.
     * @return type
     */
    public String toString(){
        if (tokenValue == null){
            return type.toString();
        }
        return type + "(" + tokenValue + ")";
    }

    /**
     * Helper method.
     * @return
     */
    public String StringValue(){
        return tokenValue;
    }

    /**
     * Helper method that returns the token key word.
     * @return type
     */
    public Token.KeyWord getKeyWord(){
        return type;
    }
}

