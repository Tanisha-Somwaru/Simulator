import java.util.LinkedList;
import java.util.Optional;

public class Parser {
    // Variable declaration.
    private TokenManager handleToken;
    private String finalRegister = "";
    private String finalInstruction = "";
    private Optional<Token> valRegister = Optional.empty();
    private Optional<Token> number = Optional.empty();

    /**
     * Constructor.
     * @param tokens
     */
    public Parser(LinkedList<Token> tokens) {
        handleToken = new TokenManager(tokens);
    }

    /**
     * This method produces the output file after the program is done parsing.
     * @param outputFile
     * @return outputFile
     */
    public String[] Parse(String[] outputFile) {
        int i = 0;
        while (handleToken.MoreTokens()) {
            outputFile[i] = Program();
            i++;
            finalRegister = "";
            finalInstruction = "";
        }
        return outputFile;
    }

    /**
     * This method calls Statements() and returns its result.
     * @return
     */
    String Program() {
        // Variable declaration.
        String programResult = "";

        AcceptNewlines();
        programResult = Statements();

        return programResult;
    }

    /**
     * This method returns the binary conversion of the registers.
     * @return parseResult
     */
    String ParseRegisters() {
        // Variable declaration.
        int counter = 0;
        int tracker = 0;
        String result = "";
        finalRegister += BinaryConversion(valRegister);
        StringBuffer addToFinal = new StringBuffer(finalRegister);

        while (addToFinal.length() < 32) {
            if (tracker < finalRegister.length() && (addToFinal.charAt(tracker) == '0' || addToFinal.charAt(tracker) == '1')) {
                tracker++;
            } else {
                addToFinal.insert(counter, '0');
                counter++;
            }
        }
        result = addToFinal.toString();

        return result;
    }

    /**
     * This method is a helper method to convert the registers into binary and numbers into binary.
     * @param registerVal
     * @return convertedRegVals
     */
    String BinaryConversion(Optional<Token> registerVal) {
        // Variable declarations.
        int math;
        int value = Integer.valueOf(registerVal.get().StringValue());
        String result = "";

        if (value == 0) {
            result = "0";
        }

        while (value != 0){
            math = Math.abs(value) / 2;
            if (math >= 1){
                if (Math.abs(value) % 2 >= 1){
                    result += "1";
                } else {
                    result += "0";
                }
            } else {
                int temp = Math.abs(value) % 2;
                if (temp <= 0){
                    result += "0";
                } else {
                    result += "1";
                }
            }
            value = value / 2;
        }
        return result;
    }

    /**
     * This method accepts any number of newlines and returns true if it
     * finds at least one.
     * @return true
     */
    boolean AcceptNewlines() {
        // Variable declaration.
        int tracker = 0;

        while (handleToken.Peek(tracker).isPresent()){
            tracker++;

            if (handleToken.MatchAndRemove(Token.KeyWord.NEWLINE).isPresent()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks for the instruction tokens and removes it before properly
     * calling its designated instruction parsing method before converting the registers to
     * binary.
     * @return statement
     */
    String Statements() {
        // Variable declaration.
        String statement = "";

        if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.MATH) {
            handleToken.MatchAndRemove(Token.KeyWord.MATH);

            Math();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.BRANCH) {
            handleToken.MatchAndRemove(Token.KeyWord.BRANCH);

            BooleanOperations();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.HALT) {
            handleToken.MatchAndRemove(Token.KeyWord.HALT);

            Halt();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.COPY) {
            handleToken.MatchAndRemove(Token.KeyWord.COPY);

            Copy();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.JUMP) {
            handleToken.MatchAndRemove(Token.KeyWord.JUMP);

            Jump();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.CALL) {
            handleToken.MatchAndRemove(Token.KeyWord.CALL);

            BooleanOperations();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.PUSH) {
            handleToken.MatchAndRemove(Token.KeyWord.PUSH);

            Push();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.POP) {
            handleToken.MatchAndRemove(Token.KeyWord.POP);

            Pop();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.LOAD) {
            handleToken.MatchAndRemove(Token.KeyWord.LOAD);

            Load();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.STORE) {
            handleToken.MatchAndRemove(Token.KeyWord.STORE);

            Store();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.RETURN) {
            handleToken.MatchAndRemove(Token.KeyWord.RETURN);

            Return();
            finalInstruction += ParseRegisters();
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.PEEK) {
            handleToken.MatchAndRemove(Token.KeyWord.PEEK);

            Peek();
            finalInstruction += ParseRegisters();
        } else {
            handleToken.MatchAndRemove(Token.KeyWord.INTERRUPT);

            Interrupt();
            finalInstruction += ParseRegisters();
        }
        return finalInstruction;
    }

    /**
     * This method checks for the jump token for 1R and 0R before returning the binary of its
     * instruction.
     * @return finalRegister
     */
    String Jump() {
        // Variable declarations.
        String jumpResult = "";
        String numberResult = "";
        int countRegisters = CountRegisters();
        int countNumbers = 0;

        if (countRegisters == 1) {
            jumpResult = "00101";
            finalRegister += jumpResult;
        } else {
            jumpResult = "00100";
            finalRegister += jumpResult;
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for the push token 1R, 2R, and 3R before returning the binary of its instruction.
     * @return finalRegister
     */
    String Push() {
        // Variable declarations.
        String pushResult = "";
        String numberResult =  "";
        int countRegisters = CountRegisters();

        if (countRegisters == 3) {
            // 3R.
            pushResult = "01110";
            finalRegister += pushResult;
        } else if (countRegisters == 2) {
            // 2R.
            pushResult = "01111";
            finalRegister += pushResult;
        } else if (countRegisters == 1) {
            // 1R.
            pushResult = "01101";
            finalRegister += pushResult;
        } else {
            // 0R.
            pushResult = "01100";
            finalRegister += pushResult;
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for the pop token 1R before returning the binary of its instruction.
     * @return finalRegister
     */
    String Pop() {
        // Variable declarations.
        String popResult = "";
        String numberResult = "";
        int countRegisters = CountRegisters();

        if (countRegisters == 1) {
            // 1R.
            popResult = "11001";
            finalRegister += popResult;
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for the load token for 3R, 2R, and 1R before returning its instruction in binary.
     * @return finalRegister
     */
    String Load() {
        // Variable declarations.
        String loadResult = "";
        String numberResult = "";
        int countRegisters = CountRegisters();

        if (countRegisters == 3) {
            // 3R.
            loadResult = "10010";
            finalRegister += loadResult;
        } else if (countRegisters == 2) {
            // 2R.
            loadResult = "10011";
            finalRegister += loadResult;
        } else {
            // 1R.
            loadResult = "10001";
            finalRegister += loadResult;
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for the store token for 3R, 2R, 1R, and 0R before returning the binary of its instruction.
     * @return finalRegister
     */
    String Store() {
        // Variable declarations.
        String storeResult = "";
        String numberResult = "";
        int countRegisters = CountRegisters();

        if (countRegisters == 3) {
            // 3R.
            storeResult = "10110";
            finalRegister += storeResult;
        } else if (countRegisters == 2) {
            // 2R.
            storeResult = "10111";
            finalRegister += storeResult;
        } else if (countRegisters == 1) {
            // 1R.
            storeResult = "10101";
            finalRegister += storeResult;
        } else {
            // 0R.
            storeResult = "10100";
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for the return token for 0R before returning the binary of its instruction.
     * @return finalRegister
     */
    String Return() {
        // Variable declarations.
        String returnResult = "";
        String numberResult = "";
        int countRegisters = CountRegisters();

        if (countRegisters == 0) {
            returnResult = "10000";
            finalRegister += returnResult;
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for the peek token for 3R and 2R before returning the binary of its instruction.
     * @return finalRegister
     */
    String Peek() {
        // Variable declarations.
        String peekResult = "";
        String numberResult = "";
        int countRegisters = CountRegisters();

        if (countRegisters == 3) {
            // 3R.
            peekResult = "11010";
            finalRegister += peekResult;
        } else {
            // 2R.
            peekResult = "11011";
            finalRegister += peekResult;
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for the interrupt token for 0R before returning the binary of the instruction.
     * @return finalRegister
     */
    String Interrupt() {
        // Variable declarations.
        String interrResult = "";
        int counter = CountRegisters();

        if (counter == 0) {
            interrResult = "11000";
            finalRegister += interrResult;
        }
        return finalRegister;
    }

    /**
     * This method checks for boolean operation tokens before returning its binary instruction.
     * @return booleanResult
     */
    String BooleanOperations() {
        String booleanResult = "";
        String numberResult = "";
        int countRegisters = 0;

        if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.LESS) {
            handleToken.MatchAndRemove(Token.KeyWord.LESS);
            countRegisters = CountRegisters();

            if (countRegisters == 3) {
                // 3R.
                booleanResult = "001001010";
                finalRegister += booleanResult;
            } else {
                // 2R.
                booleanResult = "001001011";
                finalRegister += booleanResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.GREATER) {
            handleToken.MatchAndRemove(Token.KeyWord.GREATER);
            countRegisters = CountRegisters();

            if (countRegisters == 3) {
                // 3R.
                booleanResult = "001010010";
                finalRegister += booleanResult;
            } else {
                // 2R.
                booleanResult = "001010011";
                finalRegister += booleanResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.LESSOREQUAL) {
            handleToken.MatchAndRemove(Token.KeyWord.LESSOREQUAL);
            countRegisters = CountRegisters();

            if (countRegisters == 3) {
                // 3R.
                booleanResult = "001010110";
                finalRegister += booleanResult;
            } else {
                // 2R.
                booleanResult = "001010111";
                finalRegister += booleanResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.GREATEROREQUAL) {
            handleToken.MatchAndRemove(Token.KeyWord.GREATEROREQUAL);
            countRegisters = CountRegisters();

            if (countRegisters == 3) {
                // 3R.
                booleanResult = "001001110";
                finalRegister += booleanResult;
            } else {
                // 2R.
                booleanResult = "001001111";
                finalRegister += booleanResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.EQUAL) {
            handleToken.MatchAndRemove(Token.KeyWord.EQUAL);
            countRegisters = CountRegisters();

            if (countRegisters == 3) {
                // 3R.
                booleanResult = "001000010";
                finalRegister += booleanResult;
            } else {
                // 2R.
                booleanResult = "001000011";
                finalRegister += booleanResult;
            }
        } else {
            handleToken.MatchAndRemove(Token.KeyWord.UNEQUAL);
            countRegisters = CountRegisters();

            if (countRegisters == 3) {
                // 3R.
                booleanResult = "001000110";
                finalRegister += booleanResult;
            } else {
                // 2R.
                booleanResult = "001000111";
                finalRegister += booleanResult;
            }
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * Helper method to count the amount of registers.
     * @return counter
     */
    int CountRegisters() {
        // Variable declaration.
        int counter = 0;

        while (handleToken.MoreTokens() && (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.REGISTER)) {
            AcceptNewlines();
            if ((valRegister = handleToken.MatchAndRemove(Token.KeyWord.REGISTER)).isPresent()) {
                counter++;
                valRegister.get().StringValue();
            } else {
                break;
            }
        }
        return counter;
    }

    /**
     * This method checks for the math token and math operation tokens before returning its binary instruction.
     * @return finalRegister
     */
    String Math() {
        // Variable declarations.
        String numberResult = "";
        String mathResult = "";
        int countRegisters = 0;

        if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.ADD) {
            handleToken.MatchAndRemove(Token.KeyWord.ADD);
            countRegisters = CountRegisters();

            if (countRegisters == 2) {
                mathResult = "000111011";
                finalRegister += mathResult;
            } else {
                mathResult = "000111010";
                finalRegister += mathResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.SUBTRACT) {
            handleToken.MatchAndRemove(Token.KeyWord.SUBTRACT);
            countRegisters = CountRegisters();

            if (countRegisters == 2) {
                mathResult = "000111111";
                finalRegister += mathResult;
            } else {
                mathResult = "000111110";
                finalRegister += mathResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.MULTIPLY) {
            handleToken.MatchAndRemove(Token.KeyWord.MULTIPLY);
            countRegisters = CountRegisters();

            if (countRegisters == 2) {
                mathResult += "000011111";
                finalRegister += mathResult;
            } else {
                mathResult = "000011110";
                finalRegister += mathResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.SHIFT) {
            handleToken.MatchAndRemove(Token.KeyWord.SHIFT);

            if (handleToken.MatchAndRemove(Token.KeyWord.RIGHT).isPresent()) {
                handleToken.MatchAndRemove(Token.KeyWord.RIGHT);
                countRegisters = CountRegisters();

                if (countRegisters == 2) {
                    mathResult = "000110111";
                    finalRegister += mathResult;
                } else {
                    mathResult = "000110110";
                    finalRegister += mathResult;
                }
            } else {
                handleToken.MatchAndRemove(Token.KeyWord.LEFT);
                countRegisters = CountRegisters();

                if (countRegisters == 2) {
                    mathResult = "000110011";
                    finalRegister += mathResult;
                } else {
                    mathResult = "000110010";
                    finalRegister += mathResult;
                }
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.AND) {
            handleToken.MatchAndRemove(Token.KeyWord.AND);
            countRegisters = CountRegisters();

            if (countRegisters == 2) {
                mathResult = "000100011";
                finalRegister += mathResult;
            } else {
                mathResult = "000100010";
                finalRegister += mathResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.OR) {
            handleToken.MatchAndRemove(Token.KeyWord.AND);
            countRegisters = CountRegisters();

            if (countRegisters == 2) {
                mathResult = "000100111";
                finalRegister += mathResult;
            } else {
                mathResult = "000100110";
                finalRegister += mathResult;
            }
        } else if (handleToken.Peek(0).get().getKeyWord() == Token.KeyWord.XOR) {
            handleToken.MatchAndRemove(Token.KeyWord.XOR);
            countRegisters = CountRegisters();

            if (countRegisters == 2) {
                mathResult = "000101011";
                finalRegister += mathResult;
            } else {
                mathResult = "000101010";
                finalRegister += mathResult;
            }
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult += BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method returns destOnly().
     * @return finalRegister += copyResult
     */
    String Copy() {
        // Variable declaration.
        String copyResult = "";
        String numberResult = "";
        int countRegisters = CountRegisters();

        if (countRegisters == 1) {
            copyResult = destOnly();
            finalRegister += copyResult;
        }

        // Check for numbers.
        if ((number = handleToken.MatchAndRemove(Token.KeyWord.NUMBER)).isPresent()) {
            number.get().StringValue();
            numberResult = BinaryConversion(number);
            finalRegister += numberResult;
        }
        return finalRegister;
    }

    /**
     * This method returns the binary instructor for halt.
     * @return 00
     */
    String Halt() {
        return finalRegister += "00";
    }

    /**
     * This method returns the binary instruction for 2R.
     * @return 11
     */
    String twoR() {
        return "11";
    }

    /**
     * This method returns the binary instruction for 3R.
     * @return 10
     */
    String threeR() {
        return "10";
    }

    /**
     * This method returns the binary instruction for 1R.
     * @return 01
     */
    String destOnly() {
        return "01";
    }

    /**
     * This method returns the binary instruction for 0R.
     * @return 00
     */
    String noR() {
        return "00";
    }
}