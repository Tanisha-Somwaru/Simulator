import org.junit.Test;
import org.junit.Assert;

public class UnitTests2 {
    /**
     * Test this method last.
     */
    @Test
    public void TestDoOperation(){
        // Test 1.
//        Bit[] bits1 = {new Bit(true), new Bit(false), new Bit(false), new Bit(false)};
//        Word word1 = new Word();
//        word1.set(45);
//        Word word2 = new Word();
//        word2.set(30);
//        ALU test1 = new ALU(word1, word2);
//        test1.doOperation(bits1);
//
//        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, ", test1.result.toString());
//
//        // Test 2.
//        Bit[] bits2 = {new Bit(true), new Bit(false), new Bit(true), new Bit(false)};
//        Word word3 = new Word();
//        word3.set(67);
//        Word word4 = new Word();
//        word4.set(79);
//        ALU test2 = new ALU(word3, word4);
//        test2.doOperation(bits2);
//
//        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, ", test2.result.toString());
//
//        // Test 3.
//        Bit[] bits3 = {new Bit(true), new Bit(false), new Bit(true), new Bit(true)};
//        Word word5 = new Word();
//        word5.set(98);
//        Word word6 = new Word();
//        word6.set(112);
//        ALU test3 = new ALU(word5, word6);
//        test3.doOperation(bits3);
//
//        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, f, t, t, t, f, t, ", test3.result.toString());
//
//        // Test 4.
//        Bit[] bits4 = {new Bit(true), new Bit(true), new Bit(false), new Bit(false)};
//        Word word7 = new Word();
//        word7.set(45);
//        Word word8 = new Word();
//        word8.set(30);
//        ALU test4 = new ALU(word7, word8);
//        test4.doOperation(bits4);
//
//        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, f, t, f, f, f, f, ", test4.result.toString());

        // Test 5.
//        Bit[] bits5 = {new Bit(true), new Bit(true), new Bit(false), new Bit(true)};
//        Word word9 = new Word();
//        word9.set(650);
//        Word word10 = new Word();
//        word10.set(342);
//        ALU test5 = new ALU(word9, word10);
//        test5.doOperation(bits5);
//
//        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, f, f, t, ", test5.result.toString());

        // Test 6.
        Bit[] bits6 = {new Bit(true), new Bit(true), new Bit(true), new Bit(false)};
        Word word11 = new Word();
        word11.set(76);
        Word word12 = new Word();
        word12.set(98);
        ALU test6 = new ALU(word11, word12);
        test6.doOperation(bits6);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, t, t, t, f, ", test6.result.toString());


        // Test 7.
        Bit[] bits7 = {new Bit(true), new Bit(true), new Bit(true), new Bit(true)};
        Word word13 = new Word();
        word13.set(89);
        Word word14 = new Word();
        word14.set(65);
        ALU test7 = new ALU(word13, word14);
        test7.doOperation(bits7);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, ", test7.result.toString());

        // Test 8.
        Bit[] bits8 = {new Bit(false), new Bit(true), new Bit(true), new Bit(true)};
        Word word15 = new Word();
        word15.set(450);
        Word word16 = new Word();
        word16.set(900);
        ALU test8 = new ALU(word15, word16);
        test8.doOperation(bits8);

        Assert.assertEquals("", test8.result.toString());
    }

    @Test
    public void TestAdd(){
        // Test 1.
        Word a1 = new Word();
        a1.set(0);
        Word b1 = new Word();
        b1.set(58);
        ALU test1 = new ALU();
        Word result1 = test1.add(a1, b1);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, f, t, f, ", result1.toString());

        // Test 2.
        Word a2 = new Word();
        a2.set(76);
        Word b2 = new Word();
        b2.set(98);
        ALU test2 = new ALU();
        Word result2 = test2.add(a2, b2);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, t, t, t, f, ", result2.toString());

        // Test 3.
        Word a3 = new Word();
        a3.set(540);
        Word b3 = new Word();
        b3.set(997);
        ALU test3 = new ALU();
        Word result3 = test3.add(a3, b3);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, f, f, f, f, f, f, t, ", result3.toString());

        // Test 4.
        Bit[] aBits4 = {new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(false),
                new Bit(true), new Bit(true),

                new Bit(false), new Bit(true),
                new Bit(false), new Bit(true),

                new Bit(false), new Bit(false),
                new Bit(false), new Bit(false)};
        Bit[] bBits4 = {new Bit(true), new Bit(true),
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
                new Bit(false), new Bit(false),

                new Bit(true), new Bit(false),
                new Bit(true), new Bit(false),

                new Bit(true), new Bit(false),
                new Bit(false), new Bit(true)};
        Word a4 = new Word(aBits4);
        Word b4 = new Word(bBits4);
        Word reverseA4 = new Word();
        Word reverseB4 = new Word();

        for (int i = 0; i < 32; i++){
            reverseA4.setBit(i, new Bit(a4.getBit(32 - i - 1).getValue()));
            reverseB4.setBit(i, new Bit(b4.getBit(32 - i - 1).getValue()));
        }

        ALU test4 = new ALU();
        Word result4 = test4.add(reverseA4, reverseB4);
        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, t, t, t, t, t, t, t, f, f, t, ", result4.toString());
    }

    @Test
    public void TestSubtract(){
        // Test 1.
        Word a1 = new Word();
        a1.set(0);
        Word b1 = new Word();
        b1.set(1000);
        ALU test1 = new ALU();
        Word result1 = test1.subtract(a1, b1);

        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, f, f, f, f, t, f, t, t, t, ", result1.toString());

        // Test 2.
        Word a2 = new Word();
        a2.set(89);
        Word b2 = new Word();
        b2.set(65);
        ALU test2 = new ALU();
        Word result2 = test2.subtract(a2, b2);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, ", result2.toString());

        // Test 3.
        Word a3 = new Word();
        a3.set(789);
        Word b3 = new Word();
        b3.set(584);
        ALU test3 = new ALU();
        Word result3 = test3.subtract(a3, b3);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, t, t, f, f, ", result3.toString());

        // Test 4.
        Bit[] aBits4 = {new Bit(true), new Bit(true),
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
                new Bit(true), new Bit(false),

                new Bit(false), new Bit(false),
                new Bit(true), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(false)};
        Bit[] bBits4 = {new Bit(true), new Bit(true),
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

                new Bit(true), new Bit(false),
                new Bit(false), new Bit(true),

                new Bit(true), new Bit(false),
                new Bit(true), new Bit(true)};
        Word a4 = new Word(aBits4);
        Word b4 = new Word(bBits4);
        Word reverseA4 = new Word();
        Word reverseB4 = new Word();

        for (int i = 0; i < 32; i++){
            reverseA4.setBit(i, new Bit(a4.getBit(32 - i - 1).getValue()));
            reverseB4.setBit(i, new Bit(b4.getBit(32 - i - 1).getValue()));
        }

        ALU test4 = new ALU();
        Word result4 = test4.subtract(reverseA4, reverseB4);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, f, f, t, f, ", result4.toString());
    }

    @Test
    public void TestAdd4(){
        // Test 1.
        Word a1 = new Word();
        a1.set(59);
        Word b1 = new Word();
        b1.set(15);
        Word c1 = new Word();
        c1.set(25);
        Word d1 = new Word();
        d1.set(36);
        ALU test1 = new ALU();
        Word result1 = test1.add4(a1, b1, c1, d1);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, t, t, t, ", result1.toString());

        //Test 2.
        Word a2 = new Word();
        a2.set(0);
        Word b2 = new Word();
        b2.set(120);
        Word c2 = new Word();
        c2.set(360);
        Word d2 = new Word();
        d2.set(750);
        ALU test2 = new ALU();
        Word result2 = test2.add4(a2, b2, c2, d2);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, f, t, f, t, f, t, f, f, f, ", result2.toString());

        // Test 3.
        Word a3 = new Word();
        a3.set(1300);
        Word b3 = new Word();
        b3.set(1400);
        Word c3 = new Word();
        c3.set(1500);
        Word d3 = new Word();
        d3.set(1600);
        ALU test3 = new ALU();
        Word result3 = test3.add4(a3, b3, c3, d3);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, f, t, f, t, f, t, f, f, f, ", result3.toString());

        // Test 4.
        Word a4 = new Word();
        a4.set(-45);
        Word b4 = new Word();
        b4.set(-90);
        Word c4 = new Word();
        c4.set(-130);
        Word d4 = new Word();
        d4.set(-980);
        ALU test4 = new ALU();
        Word result4 = test4.add4(a4, b4, c4, d4);

        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, t, f, f, t, f, f, f, t, t, ", result4.toString());
    }

    @Test
    public void TestMultiply(){
        // Test 1.
        Word a1 = new Word();
        a1.set(0);
        Word b1 = new Word();
        b1.set(49);
        ALU test1 = new ALU(a1, b1);
        Word result1 = test1.multiply(a1, b1);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result1.toString());


        // Test 2.
        Word a2 = new Word();
        a2.set(450);
        Word b2 = new Word();
        b2.set(900);
        ALU test2 = new ALU();
        Word result2 = test2.multiply(a2, b2);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, f, t, f, t, t, t, f, f, f, f, f, t, f, f, f, ", result2.toString());

        // Test 3.
        Word a3 = new Word();
        a3.set(1352);
        Word b3 = new Word();
        b3.set(1567);
        ALU test3 = new ALU(a3, b3);
        Word result3 = test3.multiply(a3, b3);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, f, t, f, t, t, t, f, f, f, f, f, t, f, f, f, ", result3.toString());

        // Test 4.
        Word a4 = new Word();
        a4.set(-25);
        Word b4 = new Word();
        b4.set(190);
        ALU test4 = new ALU(a4, b4);
        Word result4 = test4.multiply(a4, b4);

        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, t, f, t, f, t, t, t, f, f, t, f, ", result4.toString());
    }
}
