public class MainMemory {
    // Variable declaration.
    private static Word[] DRAM = new Word[1024];

    /**
     * This method returns a new Word with the same value as the indexed memory.
     * @param address
     * @return
     */
    public static Word read(Word address) throws Exception {
        // Variable declarations.
        int index = (int) address.getUnsigned();
        Word wordValue = new Word();

        for (int i = 0; i < DRAM.length; i++){
            if (DRAM[i] != null){
                int check = (int) DRAM[i].getUnsigned();
                if (check == index){
                    wordValue = DRAM[i];
                    break;
                }
            } else {
                DRAM[i] = new Word();
            }
        }
        return wordValue;
    }

    /**
     * This method alters the indexed memory to have the same value as the Word passed in.
     * @param address
     * @param value
     */
    public static void write(Word address, Word value) throws Exception {
        // Variable declarations.
        int index = (int) address.getUnsigned();

        for (int i = 0; i < DRAM.length; i++){
            if (DRAM[i] != null){
                if (DRAM[i].getUnsigned() == index){
                    DRAM[i] = value;
                    break;
                }
            } else {
                DRAM[i] = new Word();
            }
        }
    }

    /**
     * This method loops through the String array of data and converts the 0's and 1's
     * into true and false before populating the memory.
     * @param data
     */
    public static void load(String[] data){
        // Variable declaration.
        Word convert = new Word();
        int index = 0;

        for (int i = 0; i < data.length; i++){
            if (data[i] == null) {
                break;
            }
            String word = data[i];

            // Converting to true and false.
            for (int k = 0; k < word.length(); k++){
                if (word.charAt(k) == '0'){
                    convert.setBit(word.length() - k - 1, new Bit(false));
                } else if (word.charAt(k) == '1'){
                    convert.setBit(word.length() - k - 1, new Bit(true));
                } else {
                    break;
                }
            }
            DRAM[index] = convert;
            index++;
        }
    }
}
