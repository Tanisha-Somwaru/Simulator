public class L2 {
    // Variable declarations.
    private static Word[] cacheL2 = new Word[32];
    public static Word address1 = new Word();
    public static Word address2 = new Word();
    public static Word address3 = new Word();
    public static Word address4 = new Word();

    /**
     * This method returns a new Word with the same value as the indexed memory.
     * @param address
     * @return result
     * @throws Exception
     */
    public static Word read(Word address) throws Exception {
        // Variable declarations.
        int index = (int) address.getUnsigned();
        Word result = new Word();

        for (int i = 0; i < cacheL2.length; i++) {
            if (cacheL2[i] != null) {
                if (cacheL2[i].getUnsigned() == index) {
                    result = cacheL2[i];
                    //Processor.currentClockCycle += 20;

                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();

                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();

                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();

                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();

                    break;
                } else {
                    int j = 0;
                    cacheL2[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cacheL2[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cacheL2[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cacheL2[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cacheL2[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cacheL2[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cacheL2[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cacheL2[j] = MainMemory.read(address);

                    //Processor.currentClockCycle += 350;
                    break;
                }
            } else {
                cacheL2[i] = new Word();
                break;
            }
        }
        return result;
    }

    /**
     * This method alters the indexed memory to have the same value as the Word passed in.
     * @param address
     * @param value
     */
    public static void write(Word address, Word value) {
        // Variable declaration.
        int index = (int) address.getUnsigned();

        for (int i = 0; i < cacheL2.length; i++) {
            if (cacheL2[i] != null){
                if (cacheL2[i].getUnsigned() == index){
                    cacheL2[i] = value;

                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();

                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();
                    address2.increment();

                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();
                    address3.increment();

                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    address4.increment();
                    break;
                }
            } else {
                cacheL2[i] = new Word();
            }
        }
    }
}
