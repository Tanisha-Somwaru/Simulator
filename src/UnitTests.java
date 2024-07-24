import org.junit.Test;
import org.junit.Assert;

public class UnitTests {

    /**
     * This tests the set method that sets the value
     * of the bit variable in Bit class.
     */
    @Test
    public void TestSet1(){
        // Test 1.
        Bit bit1 = new Bit(true);
        bit1.set(false);

        Assert.assertFalse(bit1.getValue());

        // Test 2.
        Bit bit2 = new Bit(false);
        bit2.set(true);

        Assert.assertTrue(bit2.getValue());

        // Test 3.
        Bit bit3 = new Bit();
        bit3.set(true);

        Assert.assertTrue(bit3.getValue());
    }

    @Test
    public void TestToggle(){
        // Test 1.
        Bit bit1 = new Bit(true);
        bit1.toggle();

        Assert.assertFalse(bit1.getValue());

        // Test 2.
        Bit bit2 = new Bit(false);
        bit2.toggle();

        Assert.assertTrue(bit2.getValue());
    }

    /**
     * This tests the set method that sets the bit variable
     * to true.
     */
    @Test
    public void TestSet2(){
        // Test 1.
        Bit bit1 = new Bit();
        bit1.set();

        Assert.assertTrue(bit1.getValue());
    }

    @Test
    public void TestClear(){
        // Test 1.
        Bit bit1 = new Bit();
        bit1.clear();

        Assert.assertFalse(bit1.getValue());
    }

    @Test
    public void TestGetValue(){
        // Test 1.
        Bit bit1 = new Bit();
        bit1.set();
        bit1.set(false);

        Assert.assertFalse(bit1.getValue());

        // Test 2.
        Bit bit2 = new Bit(true);

        Assert.assertTrue(bit2.getValue());

        // Test 3.
        Bit bit3 = new Bit();
        bit3.clear();

        Assert.assertFalse(bit3.getValue());
    }

    /**
     * This tests the 'and' method of Bit class.
     */
    @Test
    public void TestAnd1(){
        // Test 1.
        Bit bit1 = new Bit(false);
        Bit otherBit1= new Bit(false);
        Bit result1 = bit1.and(otherBit1);

        Assert.assertFalse(result1.getValue());

        // Test 2.
        Bit bit2 = new Bit(false);
        Bit otherBit2 = new Bit(true);
        Bit result2 = bit2.and(otherBit2);

        Assert.assertFalse(result2.getValue());

        // Test 3.
        Bit bit3 = new Bit(true);
        Bit otherBit3 = new Bit(false);
        Bit result3 = bit3.and(otherBit3);

        Assert.assertFalse(result3.getValue());

        // Test 4.
        Bit bit4 = new Bit(true);
        Bit otherBit4 = new Bit(true);
        Bit result4 = bit4.and(otherBit4);

        Assert.assertTrue(result4.getValue());
    }

    /**
     * This tests the 'or' method of Bit class.
     */
    @Test
    public void TestOr1(){
        // Test 1.
        Bit bit1 = new Bit(false);
        Bit otherBit1 = new Bit(false);
        Bit result1 = bit1.or(otherBit1);

        Assert.assertFalse(result1.getValue());

        // Test 2.
        Bit bit2 = new Bit(false);
        Bit otherBit2 = new Bit(true);
        Bit result2 = bit2.or(otherBit2);

        Assert.assertTrue(result2.getValue());

        // Test 3.
        Bit bit3 = new Bit(true);
        Bit otherBit3 = new Bit(false);
        Bit result3 = bit3.or(otherBit3);

        Assert.assertTrue(result3.getValue());

        // Test 4.
        Bit bit4 = new Bit(true);
        Bit otherBit4 = new Bit(true);
        Bit result4 = bit4.or(otherBit4);

        Assert.assertTrue(result4.getValue());
    }

    /**
     * This tests the 'xor' method of Bit class.
     */
    @Test
    public void TestXor1(){
        // Test 1.
        Bit bit1 = new Bit(false);
        Bit otherBit1 = new Bit(false);
        Bit result1 = bit1.xor(otherBit1);

        Assert.assertFalse(result1.getValue());

        // Test 2.
        Bit bit2 = new Bit(false);
        Bit otherBit2 = new Bit(true);
        Bit result2 = bit2.xor(otherBit2);

        Assert.assertTrue(result2.getValue());

        // Test 3.
        Bit bit3 = new Bit(true);
        Bit otherBit3 = new Bit(false);
        Bit result3 = bit3.xor(otherBit3);

        Assert.assertTrue(result3.getValue());

        // Test 4.
        Bit bit4 = new Bit(true);
        Bit otherBit4 = new Bit(true);
        Bit result4 = bit4.xor(otherBit4);

        Assert.assertFalse(result4.getValue());
    }

    /**
     * This tests the 'not' method of Bit class.
     */
    @Test
    public void TestNot1(){
        // Test 1.
        Bit bit1 = new Bit(false);
        Bit result1 = bit1.not();

        Assert.assertTrue(result1.getValue());

        // Test 2.
        Bit bit2 = new Bit(true);
        Bit result2 = bit2.not();

        Assert.assertFalse(result2.getValue());
    }

    /**
     * This tests the toString method of Bit class.
     */
    @Test
    public void TestToString(){
        // Test 1.
        Bit bit1 = new Bit(true);

        Assert.assertEquals("t", bit1.toString());

        // Test 2.
        Bit bit2 = new Bit(false);

        Assert.assertEquals("f", bit2.toString());
    }

    @Test
    public void TestGetBit(){
        // Test 1.
        Word test1 = new Word();
        test1.set(7);

        Assert.assertEquals("t", test1.getBit(0).toString());
        Assert.assertEquals("t", test1.getBit(1).toString());

        // Test 2.
        Word test2 = new Word();
        test2.set(13);

        Assert.assertEquals("f", test2.getBit(7).toString());
        Assert.assertEquals("t", test2.getBit(3).toString());

        // Test 3.
        Word test3 = new Word();
        test3.set(32);

        Assert.assertEquals("f", test3.getBit(10).toString());
        Assert.assertEquals("f", test3.getBit(31).toString());
    }

    @Test
    public void TestSetBit(){
        // Test 1.
        Word test1 = new Word();
        Bit set1 = new Bit(true);
        test1.setBit(5, set1);

        Assert.assertTrue(test1.getBit(5).getValue());

        // Test 2.
        Word test2 = new Word();
        Bit set2 = new Bit(false);
        test2.setBit(31, set2);

        Assert.assertFalse(test2.getBit(31).getValue());
    }

    @Test
    public void TestAnd2(){
        // Test 1 for Word and() method.
        Word test1 = new Word();
        test1.set(21);
        Word otherWord1 = new Word();
        otherWord1.set(24);
        Word result1 = test1.and(otherWord1);

        Assert.assertEquals("f, f, f, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result1.toString());

        //Test 2. (edit result)
        Word test2 = new Word();
        test2.set(31);
        Word otherWord2 = new Word();
        otherWord2.set(30);
        Word result2 = test2.and(otherWord2);

        Assert.assertEquals("f, t, t, t, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result2.toString());

        // Test 3. (edit result)
        Word test3 = new Word();
        test3.set(10);
        Word otherWord3 = new Word();
        otherWord3.set(15);
        Word result3 = test3.and(otherWord3);

        Assert.assertEquals("f, t, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result3.toString());
    }

    @Test
    public void TestOr2(){
        // Test 1.
        Word test1 = new Word();
        test1.set(51);
        Word otherWord1 = new Word();
        otherWord1.set(27);
        Word result1 = test1.or(otherWord1);

        Assert.assertEquals("t, t, f, t, t, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result1.toString());

        // Test 2.
        Word test2 = new Word();
        test2.set(29);
        Word otherWord2 = new Word();
        otherWord2.set(15);
        Word result2 = test2.or(otherWord2);

        Assert.assertEquals("t, t, t, t, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result2.toString());

        // Test 3.
        Word test3 = new Word();
        test3.set(17);
        Word otherWord3 = new Word();
        otherWord3.set(18);
        Word result3 = test3.or(otherWord3);

        Assert.assertEquals("t, t, f, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result3.toString());
    }

    @Test
    public void TestXor2(){
        // Test 1.
        Word test1 = new Word();
        test1.set(32);
        Word otherWord1 = new Word();
        otherWord1.set(15);
        Word result1 = test1.xor(otherWord1);

        Assert.assertEquals("t, t, t, t, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result1.toString());

        // Test 2.
        Word test2 = new Word();
        test2.set(54);
        Word otherWord2 = new Word();
        otherWord2.set(48);
        Word result2 = test2.xor(otherWord2);

        Assert.assertEquals("f, t, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result2.toString());

        // Test 3.
        Word test3 = new Word();
        test3.set(76);
        Word otherWord3 = new Word();
        otherWord3.set(70);
        Word result3 = test3.xor(otherWord3);

        Assert.assertEquals("f, t, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", result3.toString());
    }

    @Test
    public void TestNot2(){
        // Test 1.
        Word test1 = new Word();
        test1.set(10);
        Word result1 = test1.not();

        Assert.assertEquals("t, f, t, f, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, ", result1.toString());

        // Test 2.
        Word test2 = new Word();
        test2.set(22);
        Word result2 = test2.not();

        Assert.assertEquals("t, f, f, t, f, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, ", result2.toString());

        // Test 3.
        Word test3 = new Word();
        test3.set(56);
        Word result3 = test3.not();

        Assert.assertEquals("t, t, t, f, f, f, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, ", result3.toString());

    }

    @Test
    public void TestRightShift(){
        // Test 1.
        Word test1 = new Word();
        test1.set(1002000040);
        Word shift1 = test1.rightShift(4);

        Assert.assertEquals("f, f, f, f, f, f, t, t, t, f, t, t, t, f, t, t, t, f, f, t, f, t, f, f, t, t, t, f, t, f, t, f, ", shift1.toString());

        // Test 2.
        Word test2 = new Word();
        test2.set(12);
        Word shift2 = test2.rightShift(2);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, ", shift2.toString());

        // Test 3.
        Word test3 = new Word();
        test3.set(13);
        Word shift3 = test3.rightShift(10);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", shift3.toString());
    }

    @Test
    public void TestLeftShift(){
         //Test 1.
        Word test1 = new Word();
        test1.set(1002000040);
        Word shift1 = test1.leftShift(1);

        Assert.assertEquals("f, t, t, t, f, t, t, t, f, t, t, t, f, f, t, f, t, f, f, t, t, t, f, t, f, t, f, t, f, f, f, f, ", shift1.toString());
                                   // 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0,
        //:D
        // Test 2.
        Word test2 = new Word();
        test2.set(2002000040);
        Word shift2 = test2.leftShift(12);

        Assert.assertEquals("f, t, f, f, f, f, f, t, t, f, f, f, t, f, t, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", shift2.toString());
                            // 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

        // Test 3.
        Word test3 = new Word();
        test3.set(256);
        Word shift3 = test3.leftShift(5);

        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, ", shift3.toString());
                            // 00000000000000000000000100000000
    }

    @Test
    public void TestGetUnsigned(){
        // Test 1.
        Word test1 = new Word();
        test1.set(79);
        long result1 = test1.getUnsigned();

        Assert.assertEquals(79L, result1);
                            //

        // Test 2.
        Word test2 = new Word();
        test2.set(180);
        long result2 = test2.getUnsigned();

        Assert.assertEquals(180L, result2);
    }

    @Test
    public void TestGetSigned(){
        // Test 1.
        Word test1 = new Word();
        test1.set(460);
        int result1 = test1.getSigned();

        Assert.assertEquals(62, result1);

        // Test 2.
        Word test2 = new Word();
        test2.set(500);
        int result2 = test2.getSigned();

        Assert.assertEquals(126, result2);
    }

    @Test
    public void TestCopy(){
        // Test 1.
        Word test1 = new Word();
        test1.set(4570);
        Word copy1 = new Word();
        copy1.copy(test1);

        int counter1 = 0;
        String result1 = "";
        while (counter1 < 32){
            result1 = copy1.toString();
            counter1++;
        }
        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, t, t, t, f, t, t, f, t, f, ", copy1.toString());

        // Test 1.
        Word test2 = new Word();
        test2.set(435678);
        Word copy2 = new Word();
        copy2.copy(test2);

        int counter2 = 0;
        String result2 = "";
        while (counter2 < 32){
            result2 = copy2.toString();
            counter2++;
        }
        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, t, f, t, f, f, t, f, t, t, t, f, t, t, t, t, f, ", copy2.toString());
    }

    @Test
    public void TestSet3(){
        Word test1 = new Word();
        test1.set(240);
        String result1 = test1.toString();
        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, f, f, f, ", result1);

        Word test2 = new Word();
        test2.set(435678);
        String result2 = test2.toString();
        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, t, f, t, f, f, t, f, t, t, t, f, t, t, t, t, f, ", result2);

        Word test3 = new Word();
        test3.set(16);
        String result3 = test3.toString();
        Assert.assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, ", result3);

        Word test4 = new Word();
        test4.set(-4);
        String result4 = test4.toString();
        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, f, ", result4);

        Word test5 = new Word();
        test5.set(-550);
        String result5 = test5.toString();
        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, t, t, f, t, t, f, t, f, ", result5);

        Word test6 = new Word();
        test6.set(-1800);
        String result6 = test6.toString();
        Assert.assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, f, f, t, t, t, t, t, f, f, f, ", result6);
    }
}
