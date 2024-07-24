import org.junit.Assert;
import org.junit.Test;

public class UnitTest5 {
    @Test
    public void TestDecrement() {
        // Test 1.
        Word test1 = new Word();
        test1.set(2);
        test1.decrement();

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, ", test1.toString());

        // Test 2.
        Word test2 = new Word();
        test2.set(4);
        test2.decrement();

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, ", test2.toString());

        // Test 3.
        Word test3 = new Word();
        test3.set(35);
        test3.decrement();

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, t, f, ", test3.toString());
    }

    @Test
    public void TestBranch() throws Exception {
        // Test 1 No R.
        Processor processor1 = new Processor();
        String[] data1 = {"00000000000000000000000000000000", "00000000000000000000000000000111", "00000000000000000000000000011000",};
        MainMemory.load(data1);
        processor1.run();

        // Test 2 Dest Only.
        Processor processor2 = new Processor();
        String[] data2 = {"00000000000000000000000000010001", "00000000000000000000000000011101", "00000000000000000000000000000000"};
        MainMemory.load(data2);
        processor2.run();

        // Test 3 3R.
        Processor processor3 = new Processor();
        String[] data3 = {"00000000000000000000000000111010", "00000000000000000000000000010010", "00000000000000000000000000111110",
                "00000000000000000000000000000000"};
        MainMemory.load(data3);
        processor3.run();

        // Test 4 2R.
        Processor processor4 = new Processor();
        String[] data4 = {"00000000000000000000000000000000", "00000000000000000000000001101111", "00000000000000000000000001111011"};
        MainMemory.load(data4);
        processor4.run();
    }

    @Test
    public void TestCall() throws Exception {
        // Test 1 No R.
        Processor processor1 = new Processor();
        String[] data1 = {"00000000000000000000000000000000", "00000000000000000000000010010110", "00000000000000000000000111000010"};
        MainMemory.load(data1);
        processor1.run();

        // Test 2 Dest Only.
        Processor processor2 = new Processor();
        String[] data2 = {"00000000000000000000000111000100", "00000000000000000000001001011101", "00000000000000000000000000000000",
        "00000000000000000000000111000101"};
        MainMemory.load(data2);
        processor2.run();

        // Test 3 3R.
        Processor processor3 = new Processor();
        String[] data3 = {"00000000000000000000000111000110", "00000000000000000000000100101110", "00000000000000000000000000000000"};
        MainMemory.load(data3);
        processor3.run();

        // Test 4 2R.
        Processor processor4 = new Processor();
        String[] data4 = {"00000000000000000000000100110111", "00000000000000000000000110000111", "00000000000000000000000000000000"};
        MainMemory.load(data4);
        processor4.run();
    }

    @Test
    public void TestPush() throws Exception {
        // Test 1 Dest Only.
        Processor processor1 = new Processor();
        String[] data1 = {"00000000000000000000001111010101", "00000000000000000000000111011101", "00000000000000000000000111110001"};
        MainMemory.load(data1);
        processor1.run();

        // Test 2 3R.
        Processor processor2 = new Processor();
        String[] data2 = {"00000000000000000000000111110010", "00000000000000000000000111001010", "00000000000000000000001000110111"};
        MainMemory.load(data2);
        processor2.run();

        // Test 3 2R.
        Processor processor3 = new Processor();
        String[] data3 = {"00000000000000000000001100010111", "00000000000000000000001101111010", "00000000000000000000001110000011"};
        MainMemory.load(data3);
        processor3.run();
    }

    @Test
    public void TestLoad() throws Exception {
        // Test 1 No R.
        Processor processor1 = new Processor();
        String[] data1 = {"00000000000000000000000000101101", "00000000000000000000001010100111", "00000000000000000000000001000011"};
        MainMemory.load(data1);
        processor1.run();

        // Test 2 Dest Only.
        Processor processor2 = new Processor();
        String[] data2 = {"00000000000000000000000010001101", "00000000000000000000000010010101", "00000000000000000000000000001101"};
        MainMemory.load(data2);
        processor2.run();

        // Test 3 3R.
        Processor processor3 = new Processor();
        String[] data3 = {"00000000000000000000000000010110", "00000000000000000000000000011110", "00000000000000000000000000000001"};
        MainMemory.load(data3);
        processor3.run();

        // Test 4 2R.
        Processor processor4 = new Processor();
        String[] data4 = {"00000000000000000000000000111011", "00000000000000000000000010010001", "00000000000000000000000010010011"};
        MainMemory.load(data4);
        processor4.run();
    }

    @Test
    public void TestStore() throws Exception {
        // Test 1 Dest Only.
        Processor processor1 = new Processor();
        String[] data1 = {"00000000000000000000000000000001", "00000000000000000000000000000101", "00000000000000000000000000001101"};
        MainMemory.load(data1);
        processor1.run();

        // Test 2 3R.
        Processor processor2 = new Processor();
        String[] data2 = {"00000000000000000000000000001110", "00000000000000000000000000010010", "00000000000000000000000000011110"};
        MainMemory.load(data2);
        processor2.run();

        // Test 3 2R.
        Processor processor3 = new Processor();
        String[] data3 = {"00000000000000000000000000010011", "00000000000000000000000000000111", "00000000000000000000000000001011"};
        MainMemory.load(data3);
        processor3.run();
    }

    @Test
    public void TestPop() throws Exception {
        // Test 1 No R.
        Processor processor1 = new Processor();
        String[] data1 = {"00000000000000000000000000000000", "00000000000000000000000001011010", "00000000000000000000000000000000"};
        MainMemory.load(data1);
        processor1.run();

        // Test 2 Dest Only.
        Processor processor2 = new Processor();
        String[] data2 = {"00000000000000000000000001011001", "00000000000000000000000100100001", "00000000000000000000000000000000"};
        MainMemory.load(data2);
        processor2.run();

        // Test 3 3R.
        Processor processor3 = new Processor();
        String[] data3 = {"00000000000000000000000011111010", "00000000000000000000001101101100", "00000000000000000000001111010110"};
        MainMemory.load(data3);
        processor3.run();

        // Test 4 2R.
        Processor processor4 = new Processor();
        String[] data4 = {"00000000000000000000000100110011", "00000000000000000000001110001100", "00000000000000000000001111010111"};
        MainMemory.load(data4);
        processor4.run();
    }
}
