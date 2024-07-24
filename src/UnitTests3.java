import org.junit.Assert;
import org.junit.Test;

public class UnitTests3 {
    @Test
    public void TestIncrement(){
        // Test 1.
        Word test1 = new Word();
        test1.set(78);
        test1.increment();

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, t, t, t, t, ", test1.toString());

        // Test 2.
        Word test2 = new Word();
        test2.set(120);
        test2.increment();

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, f, t, ", test2.toString());

        // Test 3.
        Word test3 = new Word();
        test3.set(989);
        test3.increment();

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, t, t, t, t, f, ", test3.toString());

        // Test 4.
        Bit[] bits4 = {new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(false), new Bit(true),
                new Bit(false), new Bit(false)};
        Word test4 = new Word(bits4);
        Word reverse = new Word();

        for (int i = 0; i < 32; i++){
            reverse.setBit(i, new Bit(test4.getBit(32 - i - 1).getValue()));
        }
        reverse.increment();

        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, f, t, ", reverse.toString());
    }

    @Test
    public void TestRead() throws Exception {
        // Test 1.
        String[] data1 = {"00000000000000000000000000110110", "00000000000000000000000001000100", "00000000000000000000000001100100",
                "00000000000000000000000010011111", "11111111111111111111111111100100", "11111111111111111111111000001100"};
        MainMemory.load(data1);
        Word address1 = new Word();
        address1.set(54);
        Word test1 = MainMemory.read(address1);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, t, t, f, ", test1.toString());

        // Test 2.
        String[] data2 = {"11111111111111111111111110000101", "11111111111111111111111111101111", "00000000000000000001011000101110",
                "00000000000000000000001010100110", "11111111111111111111110111011110", "00000000000000000000000000101101",
                "00000000000000000000000001000101", "00000000000000000000001110111010", "00000000000000000000000001101110"};
        MainMemory.load(data2);
        Word address2 = new Word();
        address2.set(954);
        Word test2 = MainMemory.read(address2);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, f, t, t, t, f, t, f, ", test2.toString());

        // Test 3.
        String[] data3 = {"00000000000000000000000000000101", "00000000000000000000000000001010", "00000000000000000000000000001111",
                "00000000000000000000000000010100", "00000000000000000000000000011001", "00000000000000000000000000011110"};
        MainMemory.load(data3);
        Word address3 = new Word();
        address3.set(20);
        Word test3 = MainMemory.read(address3);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, f, ", test3.toString());

        // Test 4.
        String[] data4 = {"11111111111111111111111111001000", "11111111111111111111011011011001", "11111111111111111111110111100000"};
        MainMemory.load(data4);
        Bit[] bits4 = {new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(false), new Bit(true),
                new Bit(true), new Bit(false),

                new Bit(true), new Bit(true),
                new Bit(false), new Bit(true),

                new Bit(true), new Bit(false),
                new Bit(false), new Bit(true)};
        Word address4 = new Word(bits4);
        Word reverse = new Word();

        for (int i = 0; i < 32; i++){
            reverse.setBit(i, new Bit(address4.getBit(32 - i - 1).getValue()));
        }
        Word test4 = MainMemory.read(reverse);

        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, t, f, t, t, f, t, t, f, f, t, ", test4.toString());
    }

    @Test
    public void TestWrite() throws Exception {
        // Test 1.
        String[] data1 = {"00000000000000000000000000101100", "00000000000000000000001010001111", "00000000000000000000001110001100",
                          "11111111111111111111111101101010", "11111111111111111111111111100001"};
        MainMemory.load(data1);
        Word address1 = new Word();
        address1.set(44);
        Word value1 = new Word();
        value1.set(99);
        MainMemory.write(address1, value1);
        Word test1 = MainMemory.read(value1);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, f, t, t, ", test1.toString());

        // Test 2.
        String[] data2 = {"00000000000000000000000000111010", "00000000000000000000001001001101", "00000000000000000000001010100111",
                "11111111111111111111111001110000", "00000000000000000010001100101000", "00000000000000000000000011011100"};
        MainMemory.load(data2);
        Word address2 = new Word();
        address2.set(220);
        Word value2 = new Word();
        value2.set(150);
        MainMemory.write(address2, value2);
        Word test2 = MainMemory.read(value2);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, t, f, t, t, f, ", test2.toString());

        // Test 3.
        String[] data3 = {"11111111111111111111110000101100", "11111111111111111111101110001001", "00000000000000000000000000001001"};
        MainMemory.load(data3);
        Bit[] bits3 = {new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(false), new Bit(true),
                new Bit(true), new Bit(true)};
        Word value3 = new Word(bits3);
        Word reverseValue = new Word();
        Word address3 = new Word();
        address3.set(9);

        for (int i = 0; i < 32; i++){
            reverseValue.setBit(i, new Bit(value3.getBit(32 - i - 1).getValue()));
        }
        MainMemory.write(address3, reverseValue);
        Word test3 = MainMemory.read(reverseValue);

        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, t, t, ", test3.toString());
    }
}
