import java.util.Arrays;

public class Word {
    // Variable declaration.
    private Bit[] word = new Bit[32];

    /**
     * Constructor 1.
     */
    public Word(Bit[] wordArray){
        for (int i = 0; i < word.length; i++){
            word[i] = new Bit();
            word[i] = wordArray[i];
        }
    }

    /**
     * Constructor 2.
     */
    public Word(){
        for (int i = 0; i < word.length; i++){
            word[i] = new Bit();
        }
    }

    /**
     * This method gets a new Bit that has the same value as bit i.
     * @param i
     * @return bitValue
     */
    Bit getBit(int i){
        // Variable declaration.
        Bit bitValue = new Bit();

        for (int j = 0; j < word.length; j++){
            if (word[j].getValue().equals(word[i].getValue())){
                bitValue = new Bit(word[j].getValue());
            }
        }
        return bitValue;
    }

    /**
     * This method set bit i's value.
     * @param i
     * @param value
     */
    void setBit(int i, Bit value) {
        word[i] = new Bit(value.getValue());
    }

    /**
     * This method performs the 'and' operation using two words, it
     * returns a new Word.
     * @param other
     * @return andResult
     */
    Word and(Word other){
        // Variable declarations.
        Word andResult = new Word();

        for (int i = 0; i < word.length; i++){
            andResult.setBit(i, new Bit((word[i].and(other.getBit(i))).getValue()));
        }
        return andResult;
    }

    /**
     * This method performs the 'or' operation using two words, it
     * returns a new word.
     * @param other
     * @return orResult
     */
    Word or(Word other){
        // Variable declaration.
        Word orResult = new Word();

        for (int i = 0; i < word.length; i++){
            orResult.setBit(i, new Bit((word[i].or(other.getBit(i))).getValue()));
        }
        return orResult;
    }

    /**
     * This method performs the 'xor' using two words, it
     * returns a new Word.
     * @param other
     * @return xorResult
     */
    Word xor(Word other){
        // Variable declaration.
        Word xorResult = new Word();

        for (int i = 0; i < word.length; i++){
            xorResult.setBit(i, new Bit((word[i].xor(other.getBit(i))).getValue()));
        }
        return xorResult;
    }

    /**
     * This method 'negates' this word to create a Word, it
     * returns a new Word.
     * @return notResult
     */
    Word not(){
        // Variable declaration.
        Word notResult = new Word();

        for (int i = 0; i < word.length; i++){
            notResult.setBit(i, new Bit(word[i].not().getValue()));
        }
        return notResult;
    }

    /**
     * The method right shifts this word by specified amount of bits,
     * it returns the result as a new Word.
     * @param amount
     * @return new Word(copyWord)
     */
    Word rightShift(int amount){
        // Variable declaration.
        Bit[] copyWord = new Bit[32];
        int rightShift;
        int j = 0;

        for (int i = 0; i < word.length; i++){
            rightShift = (i + amount);
            copyWord[amount] = word[word.length - rightShift];

            while (rightShift < 32){
                copyWord[j] = word[rightShift++];
                j++;
            }

            if (rightShift == 32){
                break;
            }
        }

        for (int k = 0; k < copyWord.length; k++){
            if (copyWord[k] == null){
                copyWord[k] = new Bit(false);
            }
        }
        return new Word(copyWord);
    }

    /**
     * This method left shifts this word by specified amount bits,
     * it returns the result as a new Word.
     * @param amount
     * @return new Word(copyWord)
     */
    Word leftShift(int amount){
        // Variable declaration.
        Bit[] copyWord = new Bit[32];
        int leftShift;
        int j = 0;

        for (int i = word.length - 1; i >= 0; i--){
            leftShift = (i - amount);
            copyWord[amount] = word[word.length - leftShift];

            while (leftShift > 0) {
                copyWord[copyWord.length - j - 1] = word[leftShift--];
                j++;
            }

            if (leftShift == 0){
                break;
            }
        }

        for (int k = 0; k < copyWord.length; k++){
            if (copyWord[k] == null){
                copyWord[k] = new Bit(false);
            }
        }
        return new Word(copyWord);
    }

    /**
     * This method returns a comma separated string t’s and f’s.
     * @return stringWord
     */
    @Override
    public String toString(){
        // Variable declaration.
        String stringWord = "";

        for (int i = 0; i < word.length; i++){
            //if (word[word.length - 1].getValue().equals(false)){
                stringWord += word[word.length - i - 1] + ", ";
            //}
        }
        return stringWord;
    }

    /**
     * This method returns the value of this word as a long.
     * @return conversionResult
     */
    long getUnsigned(){
        // Variable declaration.
        long conversionResult = 0L;
        int twoPower = 1;
        for (int i = 0; i < word.length; i++){
            if (word[i].getValue().equals(true)){
                conversionResult += twoPower;
            }
            twoPower *= 2;
        }
        return conversionResult;
    }

    /**
     * This method returns the value of this word as an int.
     * @return conversionResult
     */
    int getSigned(){
        // Variable declaration.
        int conversionResult = 0;
        int value = 1;

        if (word[word.length - 1].getValue().equals(true)){
            var posVersion = this.not();
            for (int i = 0; i < word.length; i++){
                if (posVersion.getBit(i).getValue()) {
                    conversionResult += value;
                }
                value *= 2;
            }
            conversionResult = -conversionResult - 1;
        } else {
            for (int i = 0; i < word.length; i++){
                if (word[i].getValue().equals(true)){
                    value *= 2;
                    conversionResult += value;
                }
            }
        }
        return conversionResult;
    }

    /**
     * This method copies the values of the bits from another Word into this one.
     * @param other
     */
    void copy(Word other){
        for (int i = 0; i < word.length; i++){
            word[i] = other.getBit(i);
        }
    }

    /**
     * This method sets the value of the bits of this Word.
     * @param value
     */
    void set(int value){
        // Variable declarations.
        int math;
        int counter = 0;
        int negValue = value;

        while (value != 0){
            math = Math.abs(value) / 2;
            if (math >= 1){
                if (Math.abs(value) % 2 >= 1){
                    word[counter] = new Bit(true);
                    counter++;
                } else {
                    word[counter] = new Bit(false);
                    counter++;
                }
            // If the math results in a negative number.
            } else {
                int temp = Math.abs(value) % 2;
                if (temp <= 0){
                    word[counter] = new Bit(false);
                    counter++;
                } else {
                    word[counter] = new Bit(true);
                    counter++;
                }
            }
            value = value / 2;
        }

        if (negValue < 0) {
           int i = 0;

            while (i < word.length){
                word[i] = word[i].not();
                i++;
            }

            Bit carryOut = new Bit();
            carryOut.set(getBit(0).and(new Bit(true)).getValue());
            setBit(0, getBit(0).xor(new Bit(true)));

            for (int j = 1; j < 32; j++){
                Bit oldS = new Bit();
                oldS.set(getBit(j).getValue());
                setBit(j, getBit(j).xor(carryOut));
                carryOut.set(oldS.and(carryOut).getValue());
            }
        }
    }

    /**
     * This method increments the Word by one.
     */
    void increment(){
        // Variable declaration.
        Bit carryOut = new Bit();

        carryOut.set(getBit(0).and(new Bit(true)).getValue());
        setBit(0, getBit(0).xor(new Bit(true)));

        for (int j = 1; j < 32; j++){
            Bit oldS = new Bit();
            oldS.set(getBit(j).getValue());

            setBit(j, getBit(j).xor(carryOut));
            carryOut.set(oldS.and(carryOut).getValue());
        }
    }

    /**
     * This method decrements the Word by one.
     */
    void decrement(){
        // Variable declaration.
        Bit carryOut = new Bit();

        carryOut.set((getBit(0).not()).and(new Bit(true)).getValue());
        setBit(0, getBit(0).not());

        for (int i = 1; i < 32; i++) {
            Bit previousValue = new Bit();
            previousValue.set(getBit(i).getValue());

            setBit(i, getBit(i).xor(carryOut));
            carryOut.set((getBit(i)).and(previousValue.not()).getValue());
        }
    }
}
