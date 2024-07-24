import java.util.HashMap;
import java.util.LinkedList;

public class Lexer {
    // Variable declarations.
    private int number = 0;
    private int position = 1;
    int lineNumber = 1;
    private StringHandler item;
    private LinkedList<Token> tokens = new LinkedList();
    private HashMap<String, Token.KeyWord> keywords;

    /**
     * Constructor.
     * @param assemblyFile
     */
    public Lexer(String assemblyFile) {
        item = new StringHandler(assemblyFile);
        keywords = AddWordsHelper();
    }

    /**
     * This method breaks the data from the StringHandler class up into a LinkedList of tokens
     * which is returned at the end.
     * @return tokens
     * @throws Exception
     */
    public LinkedList<Token> Lex() throws Exception {
        while (!item.isDone()) {
            if (item.Peek(0) == ' ') {
                item.Swallow(1);
                position++;
            } else if (item.Peek(0) == '\n') {
                tokens.add(new Token(Token.KeyWord.NEWLINE, lineNumber, position));
                item.Swallow(1);
                lineNumber++;
                position = 0;
            } else if (item.Peek(0) == 'R') {
                item.Swallow(1);
                tokens.add(ProcessRegister());
            } else if (item.Peek(0) >= 'a' && item.Peek(0) <= 'z' || item.Peek(0) >= 'A' && item.Peek(0) <= 'Z') {
                tokens.add(ProcessWord());
            } else if (item.Peek(0) >= '0' && item.Peek(0) <= '9') {
                tokens.add(ProcessNumber());
            } else {
                throw new Exception("Unrecognizable character!");
            }
        }
        return tokens;
    }

    /**
     * This method takes letters, numbers, and underscores and turns them into a string word
     * and returns it as a new Token.
     * @return new Token(Token.TokenType.WORD, word, lineNumber, position)
     */
    public Token ProcessWord() {
        // Variable declaration.
        String word = "";

        while (!item.isDone()) {
            if (Character.isLetter(item.Peek(0)) || Character.isDigit(item.Peek(0)) || item.Peek(0) == '_') {
                word += item.Peek(0);
                item.Swallow(1);
                position++;
            } else {
                break;
            }
        }
        //if (keywords.containsKey(word)){
        return new Token(keywords.get(word), lineNumber, position);
        //}
        //return new Token(Token.KeyWord.REGISTER, number, lineNumber, position);
    }

    /**
     * This method checks if there is a digit following 'R' to see if it is a register,
     * and returns it as a new token.
     * @return
     */
    public Token ProcessRegister() {
        String regist = "";

        while (!item.isDone()) {
            if (Character.isDigit(item.Peek(0))) {
                regist += item.Peek(0);
                item.Swallow(1);
                position++;
            } else {
                break;
            }
        }
        return new Token(Token.KeyWord.REGISTER, regist, lineNumber, position);
    }

    /**
     * This method takes numbers from zero to nine and one period and turns them into a string number
     * and returns it as new Token.
     * @return new Token(Token.TokenType.NUMBER, number, lineNumber, position)
     */
    public Token ProcessNumber() {
        // Variable declaration.
        String number = "";

        while (!item.isDone()){
            if (Character.isDigit(item.Peek(0)) || item.Peek(0) == '.'){
                number += item.Peek(0);
                item.Swallow(1);
                position++;
            } else {
                break;
            }
        }
        return new Token(Token.KeyWord.NUMBER, number, lineNumber, position);
    }

    /**
     * Helper method for the keywords hashMap.
     * @return addWords
     */
    public HashMap<String, Token.KeyWord> AddWordsHelper(){
        // Variable declaration.
        HashMap<String, Token.KeyWord> addWords = new HashMap<>();

        addWords.put("math", Token.KeyWord.MATH);
        addWords.put("R", Token.KeyWord.REGISTER);
        addWords.put("add", Token.KeyWord.ADD);
        addWords.put("subtract", Token.KeyWord.SUBTRACT);
        addWords.put("multiply", Token.KeyWord.MULTIPLY);
        addWords.put("and", Token.KeyWord.AND);
        addWords.put("or", Token.KeyWord.OR);
        addWords.put("not", Token.KeyWord.NOT);
        addWords.put("xor", Token.KeyWord.XOR);
        addWords.put("copy", Token.KeyWord.COPY);
        addWords.put("halt", Token.KeyWord.HALT);
        addWords.put("branch", Token.KeyWord.BRANCH);
        addWords.put("jump", Token.KeyWord.JUMP);
        addWords.put("call", Token.KeyWord.CALL);
        addWords.put("push", Token.KeyWord.PUSH);
        addWords.put("load", Token.KeyWord.LOAD);
        addWords.put("return", Token.KeyWord.RETURN);
        addWords.put("store", Token.KeyWord.STORE);
        addWords.put("peek", Token.KeyWord.PEEK);
        addWords.put("pop", Token.KeyWord.POP);
        addWords.put("interrupt", Token.KeyWord.INTERRUPT);
        addWords.put("equal", Token.KeyWord.EQUAL);
        addWords.put("unequal", Token.KeyWord.UNEQUAL);
        addWords.put("greater", Token.KeyWord.GREATER);
        addWords.put("less", Token.KeyWord.LESS);
        addWords.put("greaterOrEqual", Token.KeyWord.GREATEROREQUAL);
        addWords.put("lessOrEqual", Token.KeyWord.LESSOREQUAL);
        addWords.put("shift", Token.KeyWord.SHIFT);
        addWords.put("left", Token.KeyWord.LEFT);
        addWords.put("right", Token.KeyWord.RIGHT);

        return addWords;
    }

//    /**
//     * Helper method for operations in the Assembler.
//     * @return oneChar
//     */
//    public HashMap<String, Token.KeyWord> HandlePatternHelper2(){
//        // Variable declaration.
//        HashMap<String, Token.KeyWord> oneChar = new HashMap<>();
//
//        oneChar.put("[", Token.KeyWord.OPENBRACKET);
//        oneChar.put("]", Token.KeyWord.CLOSEDBRACKET);
//        oneChar.put("+", Token.KeyWord.PLUS);
//        oneChar.put("-", Token.KeyWord.MINUS);
//        oneChar.put("*", Token.KeyWord.ASTERISK);
//        oneChar.put("/", Token.KeyWord.FRONTSLASH);
//        oneChar.put("\n", Token.KeyWord.NEWLINE);
//
//        return oneChar;
//    }
}
