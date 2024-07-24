public class ALU {
    // Variable declarations.
    public Word op1;
    public Word op2;
    public Word result;

    /**
     * Constructor 1.
     * @param word1
     * @param word2
     */
    public ALU (Word word1, Word word2){
        op1 = word1;
        op2 = word2;
    }

    /**
     * Constructor 2.
     */
    public ALU (){
        op1 = new Word();
        op2 = new Word();
    }

    /**
     * This method looks at an array of bits and determines the operation.
     * @param operation
     */
    public void doOperation(Bit[] operation){
        // Determining op2 amount for shifting.
        int shiftAmount = 0;
        for (int j = 0; j < 6; j++){
            if (op2.getBit(j).getValue().equals(true)) {
                shiftAmount += 1;
            } else {
                shiftAmount += 0;
            }
        }

        for (int i = 0; i < operation.length; i++){
            if (operation[i].getValue().equals(true)){
                i++;
                if (operation[i].getValue().equals(false)){
                    i++;
                    if (operation[i].getValue().equals(false)){
                        i++;
                        if (operation[i].getValue().equals(false)){
                            result = op1.and(op2);
                        } else {
                            result = op1.or(op2);
                        }
                    } else {
                        if (operation[i].getValue().equals(false)){
                            result = op1.xor(op2);
                        } else {
                            result = op1.not();
                        }
                    }
                } else {
                    if (operation[i].getValue().equals(false)){
                        i++;
                        if (operation[i].getValue().equals(false)){
                            // Come back to leftShift later.
                            result =  op1.leftShift(shiftAmount);
                        } else {
                            // Come back to rightShift later.
                            result = op1.rightShift(shiftAmount);
                        }
                    } else {
                        if (operation[i].getValue().equals(true)){
                            i++;
                            if (operation[i].getValue().equals(true)){
                                i++;
                                if (operation[i].getValue().equals(false)){
                                    result = add(op1, op2);
                                } else {
                                    result = subtract(op1, op2);
                                }
                            }
                        }
                    }
                }
            } else {
                if (operation[i].getValue().equals(true)){
                    i++;
                    if (operation[i].getValue().equals(true)){
                        i++;
                        if (operation[i].getValue().equals(true)){
                            result = multiply(op1, op2);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method adds two Word objects together by calling add2().
     * @param a
     * @param b
     * @return addResult
     */
    Word add(Word a, Word b){
        // Variable declarations.
        int counter = 0;
        Word addResult = new Word();

        while (counter != 32){
            addResult.setBit(counter, add2(a, b).getBit(counter));
            counter++;
        }
        return addResult;
    }

    /**
     * This method subtracts two Word objects by negating Word b and calling add2().
     * @param a
     * @param b
     * @return subResult
     */
    Word subtract(Word a, Word b){
        // Variable declarations.
        int counter = 0;
        Word subResult = new Word();
        Word negateB = new Word();

        for (int i = 0; i < 32; i++){
            negateB.setBit(i, b.getBit(i).not());
        }

        while (counter != 32){
            subResult.setBit(counter, add2(a, negateB).getBit(counter));
            counter++;
        }
        return subResult;
    }

    /**
     * This method multiples two Word objects together and adds the resulting Words
     * created by calling add4() and add2() in rounds.
     * @param a
     * @param b
     * @return thirdRound
     */
    Word multiply(Word a, Word b){
        // Variable declarations.
        Word round1 = new Word();
        Word round2 = new Word();
        Word finalResult = new Word();

        // Round 1.
        for (int i = 0; i < 9; i++){
            round1 = add4(a, b, a, b);
        }

        // Round 2.
        for (int j = 0; j < 3; j++){
            round2 = add4(round1, round1, round1, round1);
        }

        // Round 3.
        finalResult = add2(round1, round2);

        return finalResult;
    }

    /**
     * This method does the addition to add both Word objects together using
     * binary math operations.
     * @param a
     * @param b
     * @return result
     */
    public Word add2(Word a, Word b){
        // Variable declarations.
        Word result = new Word();
        Word copyA = new Word();
        copyA.copy(a);
        Word copyB = new Word();
        copyB.copy(b);
        Bit carryIn = new Bit(false);

        for (int i = 0; i < 32; i++){
            result.setBit(i, (copyA.xor(copyB)).getBit(i).xor(carryIn));
            Bit temp = (copyA.xor(copyB)).getBit(i).and(carryIn);
            Bit carryOut = (copyA.and(copyB)).getBit(i).or(temp);
            carryIn = carryOut;
        }
        return result;
    }

    /**
     * This method does addition on four Word objects using binary math operations.
     * @param a
     * @param b
     * @param c
     * @param d
     * @return result
     */
    public Word add4(Word a, Word b, Word c, Word d){
        // Variable declarations.
        Word result = new Word();
        Bit carryIn1 = new Bit(false);
        Bit carryIn2 = new Bit(false);
        Bit carryIn3 = new Bit(false);

        for (int i = 0; i < 32; i++) {
            Bit math1 = new Bit();
            Bit math2 = new Bit();
            Bit sum = new Bit();

            math1 = (a.xor(b)).getBit(i).xor(carryIn1);
            Bit temp1 = (a.xor(b)).getBit(i).and(carryIn1);
            Bit carryOut1 = (a.and(b)).getBit(i).or(temp1);
            carryIn1 = carryOut1;

            math2 = (c.xor(d)).getBit(i).xor(carryIn2);
            Bit temp2 = (c.xor(d)).getBit(i).and(carryIn2);
            Bit carryOut2 = (c.and(d)).getBit(i).or(temp2);
            carryIn2 = carryOut2;

            sum = math1.xor(math2).xor(carryIn3);
            Bit temp3 = (math1.xor(math2)).and(carryIn3);
            Bit carryOut3 = (math1.and(math2)).or(temp3);
            carryIn3 = carryOut3;
            result.setBit(i, sum);
        }
        return result;
    }
}
