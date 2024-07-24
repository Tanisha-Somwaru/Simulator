import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

public class UnitTest7 {

    @Test
    public void Test1() throws Exception {
        // Variable declarations.
        Random random = new Random();
        Word address = new Word();
        address.set(150);

        // Set up Main Memory.
        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(10) + 1;
            Word word = new Word();
            word.set(number);
            MainMemory.write(address, word);
            address.increment();
        }

        String assembleAvengers1 = "copy R1 1\n" + "copy R2 501\n" + "copy R3 20\n" + "call less R1 R3 1\n" + "halt\n" + "load R2 R5\n" + "math R2 R5\n" + "math R2 R3\n" + "return";
        Lexer lexer1 = new Lexer(assembleAvengers1);
        LinkedList<Token> tokens1 = new LinkedList<>(lexer1.Lex());
        Parser parse1 = new Parser(tokens1);
        String[] dataParse1 = new String[1024];
        parse1.Parse(dataParse1);

        // Load the parsing data into Memory.
        Processor processor1 = new Processor();
        MainMemory.load(dataParse1);
        processor1.run();
    }

    @Test
    public void Test2() throws Exception {
        // Variable declarations.
        Random random = new Random();
        Word address = new Word();
        address.set(175);

        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(10) + 1;
            Word word = new Word();
            word.set(number);
            MainMemory.write(word, address);
        }

        String assembleAvengers2 = "copy R3 1\n" + "copy R2 501\n" + "copy R1 20\n" + "call less R3 R1 1\n" + "halt\n" + "load R2 R5\n" + "math R2 R5\n" + "math R2 R3\n" + "return";
        Lexer lexer2 = new Lexer(assembleAvengers2);
        LinkedList<Token> tokens2 = new LinkedList<>(lexer2.Lex());
        Parser parse2 = new Parser(tokens2);
        String[] dataParse2 = new String[1024];
        parse2.Parse(dataParse2);

        // Load the parsing data into Memory.
        Processor processor2 = new Processor();
        MainMemory.load(dataParse2);
        processor2.run();
    }

    @Test
    public void Test3() throws Exception {
        // Variable declarations.
        Random random = new Random();
        Word address = new Word();
        address.set(230);

        for (int i = 20 - 1; i >= 0; i--) {
            int number = random.nextInt(10) + 1;
            Word word = new Word();
            word.set(number);
            MainMemory.write(address, word);
        }

        String assembleAvengers3 = "copy R4 1\n" + "copy R5 230\n" + "copy R6 20\n" + "copy R8 0\n" + "copy R7 math R6 R4\n" + "call greaterOrEqual R7 R8 1\n" + "halt\n" + "load R5 R10\n" + "math R5 R10\n" +
                "math R5 R4\n" + "return";
        Lexer lexer3 = new Lexer(assembleAvengers3);
        LinkedList<Token> tokens3 = new LinkedList<>(lexer3.Lex());
        Parser parse3 = new Parser(tokens3);
        String[] dataParse3 = new String[1024];
        parse3.Parse(dataParse3);

        // Load the parsing data into Memory.
        Processor processor3 = new Processor();
        MainMemory.load(dataParse3);
        processor3.run();
    }
}
