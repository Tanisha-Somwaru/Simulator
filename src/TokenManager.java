import java.util.LinkedList;
import java.util.Optional;

public class TokenManager extends LinkedList<Token> {
    // Variable declaration.
    private LinkedList<Token> tokens = new LinkedList<>();

    /**
     * Constructor.
     * @param tokenList
     */
    public TokenManager(LinkedList<Token> tokenList){
        tokens = tokenList;
    }

    /**
     * This method peeks “j” tokens ahead and returns the token if we aren’t past the end of the token list.
     * @param j
     * @return Optional.empty
     */
    public Optional<Token> Peek(int j){
        if (j < tokens.size()){
            return Optional.of(tokens.get(j));
        }
        return Optional.empty();
    }

    /**
     * This method returns true if the token list is not empty.
     * @return true
     */
    boolean MoreTokens(){
        if (!tokens.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method looks at the head of the list. It checks if the token type of the head is the same
     * as what was passed in, and removes that token from the list and returns it.
     * In all other cases, it returns an empty Optional.
     * @param t
     * @return Optional.of(head)
     */
    Optional<Token> MatchAndRemove(Token.KeyWord t){
        // Variable declaration.

        if (MoreTokens()){
            Token head = tokens.getFirst();

            if (head.getKeyWord() == t){
                tokens.remove();
                return Optional.of(head);
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
