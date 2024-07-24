public class InstructionCache {
    // Variable declaration.
    private static Word[] cache = new Word[8];
    public static Word address1 = new Word();

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

        for (int i = 0; i < cache.length; i++) {
            if  (cache[i] != null) {
                if (cache[i].getUnsigned() == index) {
                    result = cache[i];

                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();
                    address1.increment();

                    Processor.currentClockCycle += 10;
                    break;
                } else {
                    int j = 0;
                    cache[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cache[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cache[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cache[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cache[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cache[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cache[j] = MainMemory.read(address);
                    j++;
                    address.increment();
                    cache[j] = MainMemory.read(address);

                    Processor.currentClockCycle += 350;
                    break;

                }
            } else {
               int k = 0;
               cache[k] = L2.read(address);
               k++;
               address.increment();
               cache[k] = L2.read(address);
               k++;
               address.increment();
               cache[k] = L2.read(address);
               k++;
               address.increment();
               cache[k] = L2.read(address);
               k++;
               address.increment();
               cache[k] = L2.read(address);
               k++;
               address.increment();
               cache[k] = L2.read(address);
               k++;
               address.increment();
               cache[k] = L2.read(address);
               k++;
               address.increment();
               cache[k] = L2.read(address);

               Processor.currentClockCycle += 50;
               break;

            }
        }
        return result;
    }
}
