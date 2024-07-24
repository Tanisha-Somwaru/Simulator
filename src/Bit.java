public class Bit {
    // Variable declaration.
    private Boolean bit;

    /**
     * Constructor 1.
     * @param bitValue
     */
    public Bit(Boolean bitValue){
        bit = bitValue;
    }

    /**
     * Constructor 2.
     */
    public Bit(){
        bit = false;
    }

    /**
     * This method sets the value of the bit.
     * @param value
     */
    void set(Boolean value){
        bit = value;
    }

    /**
     * This method changes the value from true to false or false to true.
     */
    void toggle(){
        if (bit.equals(Boolean.TRUE)){
            bit = false;
        } else {
            bit = true;
        }
    }

    /**
     * This method sets the bit to true.
     */
    void set(){
        bit = true;
    }

    /**
     * This method sets the bit to false.
     */
    void clear(){
        bit = false;
    }

    /**
     * This method returns the current value.
     * @return Boolean.valueOf(bit)
     */
    Boolean getValue(){
        return Boolean.valueOf(bit);
    }

    /**
     * This method performs 'and' operation on two bits and returns a new bit set to the result.
     * @param other
     * @return andResult
     */
    Bit and(Bit other){
        // Variable declaration.
        Bit andResult = new Bit();

        if (bit.equals(Boolean.TRUE)){
            if (other.getValue().equals(Boolean.TRUE)){
                andResult = new Bit(true);
            } else {
                andResult = new Bit(false);
            }
        } else {
            if (other.getValue().equals(Boolean.FALSE)){
                andResult = new Bit(false);
            } else {
                andResult = new Bit(false);
            }
        }
        return andResult;
    }

    /**
     * This method performs the 'or' operation on two bits and returns a new bit set to the result.
     * @param other
     * @return orResul
     */
    Bit or(Bit other){
        // Variable declaration.
        Bit orResult = new Bit();

        if (bit.equals(true)){
            if (other.getValue().equals(false)){
                orResult = new Bit(true);
            } else {
                orResult = new Bit(true);
            }
        } else {
            if (other.getValue().equals(true)){
                orResult = new Bit(true);
            } else {
                orResult = new Bit(false);
            }
        }
        return orResult;
    }

    /**
     * This method performs 'xor' operation on two bits and returns a new bit set to the result.
     * @param other
     * @return xorResult
     */
    Bit xor(Bit other){
        // Variable declaration.
        Bit xorResult = new Bit();

        if (bit.equals(true)){
            if (other.getValue().equals(false)){
                xorResult = new Bit(true);
            } else {
                xorResult = new Bit(false);
            }
        } else {
            if (other.getValue().equals(true)){
                xorResult = new Bit(true);
            } else {
                xorResult = new Bit(false);
            }
        }
        return xorResult;
    }

    /**
     * This method performs the 'not' operation on the existing bit, returning the result as a new bit.
     * @return notResult
     */
    Bit not(){
        // Variable declaration.
        Bit notResult = new Bit();

        if (bit.equals(false)){
            notResult = new Bit(true);
        } else {
            notResult = new Bit(false);
        }
        return notResult;
    }

    /**
     * This method returns “t” or “f”
     * @return f
     */
    @Override
    public String toString(){
        if (bit.equals(true)){
            return "t";
        } else {
            return "f";
        }
    }
}
