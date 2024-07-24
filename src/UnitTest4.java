import org.junit.Test;
import org.junit.Assert;

public class UnitTest4 {
    /**
     * Instruction Dest Only 01
     */
    @Test
    public void TestInstruction1() throws Exception {
        // Test 1.
        Processor process1 = new Processor();
        String[] data1 = {"00000000000000000000000000000101", "00000000000000000000000000001010", "00000000000000000000000000000001", "00000000000000000000000000000100"};
        MainMemory.load(data1);
        process1.run();

        // Test 2.
        Processor process2 = new Processor();
        String[] data2 = {"00000000000000000000000001111011", "00000000000000000000000000011000", "00000000000000000000000001100101"};
        MainMemory.load(data2);
        process2.run();

        // Test 3.
        Processor process3 = new Processor();
        String[] data3 = {"00000000000000000000000001111001", "00000000000000000000000110100101"};
        MainMemory.load(data3);
        process3.run();
    }

    /**
     * Instruction No R 00
     */
    @Test
    public void TestInstruction2() throws Exception {
        // Test 1,
        Processor process1 = new Processor();
        String[] data1 = {"00000000000000000000000000000000", "00000000000000000000000010011000", "00000000000000000000000011111100"};
        MainMemory.load(data1);
        process1.run();

        // Test 2.
        Processor process2 = new Processor();
        String[] data2 = {"00000000000000000000000000111000", "00000000000000000000000001011010", "00000000000000000000000010010110"};
        MainMemory.load(data2);
        process2.run();

        // Test 3.
        Processor process3 = new Processor();
        String[] data3 = {"00000000000000000000000011100110", "00000000000000000000100011111100", "00000000000000001010101000101010", "00000000000000000000001101111010"};
        MainMemory.load(data3);
        process3.run();
    }

    /**
     * Instruction 3R 10
     */
    @Test
    public void TestInstruction3() throws Exception {
        // Test 1.
        Processor process1 = new Processor();
        String[] data1 = {"00000000000000000000001101111010", "00000000000000000000000001010010", "00000000000000000000000001011010", "00000000000000000000000100101110"};
        MainMemory.load(data1);
        process1.run();

        // Test 2.
        Processor process2 = new Processor();
        String[] data2 = {"00000000000000000000000110011010", "00000000000000000000000111000010", "00000000000000000000000111110100"};
        MainMemory.load(data2);
        process2.run();

        // Test 3.
        Processor process3 = new Processor();
        String[] data3 = {"00000000000000000000000111111110", "00000000000000000000001000100110", "00000000000000000000001010100011"};
        MainMemory.load(data3);
        process3.run();
    }

    /**
     * Instruction 2R 11
     */
    @Test
    public void TestInstruction4() throws Exception {
        // Test 1.
        Processor process1 = new Processor();
        String[] data1 = {"00000000000000000000001010100011", "00000000000000000000000001000011", "00000000000000000000000001000111"};
        MainMemory.load(data1);
        process1.run();

        // Test 2.
        Processor process2 = new Processor();
        String[] data2 = {"00000000000000000000000001011011", "11111111111111111111111110000111", "11111111111111111111110000101011"};
        MainMemory.load(data2);
        process2.run();

        // Test 3.
        Processor process3 = new Processor();
        String[] data3 = {"11111111111111111111110000010111", "11111111111111111111111111110110", "11111111111111111111111111110011"};
        MainMemory.load(data3);
        process3.run();
    }
}
