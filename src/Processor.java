public class Processor {
    // Variable declarations.
    private Word PC = new Word();
    private Word SP = new Word();
    private Word currentInstruction = new Word();
    private Word[] registers = new Word[32];
    private ALU executeOP = new ALU();
    private Word executeResult = new Word();
    private Word Rd = new Word();
    private Word Rs = new Word();
    private Word Rs1 = new Word();
    private Word Rs2 = new Word();
    private Word immediateValue = new Word();
    private Bit[] opBits = new Bit[5];
    public static int currentClockCycle = 0;
    private Bit halted = new Bit(true);

    /**
     * Constructor.
     */
    public Processor(){
        PC.set(0);
        SP.set(1024);

        for (int i = 0; i < registers.length; i++) {
            registers[i] = new Word();
        }
    }

    /**
     * This method checks the halted bit before calling fetch(), decode(), execute(), and store()
     * in the proper order.
     */
    void run() throws Exception {
        // Variable declaration.
        while (halted.getValue() != false){
            fetch();
            decode();
            execute();
            store();
        }
    }

    /**
     * This method gets all the operands and puts them in internal variables.
     */
    void decode(){
        // Variable declarations.
        Bit[] array = {new Bit(false), new Bit(false),
                      new Bit(false), new Bit(false),

                    new Bit(false), new Bit(false), new Bit(false),
                    new Bit(false), new Bit(false), new Bit(false),

                    new Bit(false), new Bit(false), new Bit(false),
                    new Bit(false), new Bit(false), new Bit(false),

                    new Bit(false), new Bit(false), new Bit(false),
                    new Bit(false), new Bit(false), new Bit(false),

                    new Bit(false), new Bit(false), new Bit(false),
                    new Bit(false), new Bit(false), new Bit(true),

                    new Bit(true), new Bit(true),
                    new Bit(true), new Bit(true)};
        Word andCalculation = new Word(array);

        Word andResult = new Word();
        Word shiftResult = new Word();
        if (currentInstruction.getBit(31).getValue().equals(false)) {
            currentClockCycle += 300;
            if (currentInstruction.getBit(30).getValue().equals(false)) {
                // Get immediate value.
                currentClockCycle += 300;
                shiftResult = currentInstruction.rightShift(5);
                currentClockCycle += 2;
                immediateValue = shiftResult;

                currentInstruction.leftShift(5);
                currentClockCycle += 2;
            } else {
                // Get Rd value.
                andResult = currentInstruction.and(andCalculation);
                currentClockCycle += 2;

                andResult.rightShift(5);
                currentClockCycle += 2;
                Rd = andResult;

                currentInstruction.leftShift(5);
                currentClockCycle += 2;

                // Get immediate value.
                currentInstruction.rightShift(14);
                currentClockCycle += 2;
                immediateValue = currentInstruction;

                currentInstruction.leftShift(14);
                currentClockCycle += 2;
            }
        } else {
            if (currentInstruction.getBit(30).getValue().equals(false)) {
                // Get Rs value.
                andResult = currentInstruction.and(andCalculation);
                currentClockCycle += 2;

                shiftResult = andResult.rightShift(5);
                currentClockCycle += 2;

                Rs = shiftResult;
                currentInstruction.leftShift(5);
                currentClockCycle += 2;

                // Get Rd value.
                andResult = currentInstruction.and(andCalculation);
                currentClockCycle += 2;

                shiftResult = andResult.rightShift(14);
                currentClockCycle += 2;

                Rd = shiftResult;
                currentInstruction.leftShift(14);
                currentClockCycle += 2;

                // Get immediate value.
                shiftResult = currentInstruction.rightShift(19);
                currentClockCycle += 2;

                immediateValue = shiftResult;
                currentInstruction.leftShift(19);
                currentClockCycle += 2;
            } else {
                // Get Rs1 value.
                andResult = currentInstruction.and(andCalculation);
                currentClockCycle += 2;

                shiftResult = andResult.rightShift(5);
                currentClockCycle += 2;
                Rs1 = shiftResult;

                currentInstruction.leftShift(5);
                currentClockCycle += 2;

                // Get Rs2 value.
                andResult = currentInstruction.and(andCalculation);
                currentClockCycle += 2;

                shiftResult = andResult.rightShift(10);
                currentClockCycle += 2;
                Rs2 = shiftResult;

                currentInstruction.leftShift(10);
                currentClockCycle += 2;

                // Get Rd value.
                andResult = currentInstruction.and(andCalculation);
                currentClockCycle += 2;

                shiftResult = andResult.rightShift(5);
                currentClockCycle += 2;
                Rd = shiftResult;

                currentInstruction.leftShift(5);
                currentClockCycle += 2;

                // Get immediate value.
                shiftResult = currentInstruction.rightShift(24);
                currentClockCycle += 2;
                immediateValue = shiftResult;

                currentInstruction.leftShift(24);
                currentClockCycle += 2;
            }
        }
    }

    /**
     * This method checks for opcode and passes the function values into ALU,
     * It copies the values into ALU.
     */
    void execute() throws Exception {
        // Variable declarations.
        Word andResult1 = new Word();
        Word shiftValue = new Word(); // Opcode value
        Bit[] array = {new Bit(false), new Bit(false),
                new Bit(false), new Bit(false),

                new Bit(false), new Bit(false), new Bit(false),
                new Bit(false), new Bit(false), new Bit(false),

                new Bit(false), new Bit(false), new Bit(false),
                new Bit(false), new Bit(false), new Bit(false),

                new Bit(false), new Bit(false), new Bit(false),
                new Bit(false), new Bit(false), new Bit(false),

                new Bit(false), new Bit(false), new Bit(false),
                new Bit(false), new Bit(false), new Bit(true),

                new Bit(true), new Bit(true),
                new Bit(true), new Bit(true)};
        Word andCalculation = new Word(array);

        Word function = new Word();
        int i = 0;
        // Opcode value.
        andResult1 = currentInstruction.and(andCalculation);
        currentClockCycle += 2;

        shiftValue = andResult1.rightShift(9);
        currentClockCycle += 2;

        andResult1.leftShift(9);
        currentClockCycle += 2;

        // Function Bit code.
        andResult1 = currentInstruction.and(andCalculation);
        currentClockCycle += 2;

        function = andResult1.rightShift(18);
        currentClockCycle += 2;

        andResult1.leftShift(18);
        currentClockCycle += 2;

        // Math opcode.
        if (shiftValue.getBit(31).getValue().equals(false)){
            currentClockCycle += 300;
            if (shiftValue.getBit(30).getValue().equals(false)){
                currentClockCycle += 300;
                if (shiftValue.getBit(29).getValue().equals(false)){
                    currentClockCycle += 300;
                    // Check Math OP.
                    if (shiftValue.getBit(28).getValue().equals(false)){
                        currentClockCycle += 300;
                        if (shiftValue.getBit(27).getValue().equals(false)){
                            halted = new Bit(false);
                            currentClockCycle += 300;
                            System.out.println("Clock cycle: " + currentClockCycle);
                        } else {
                            Rd.copy(immediateValue);
                            currentClockCycle += 2;
                        }
                    } else {
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            // Function Bit code.
                            andResult1 = currentInstruction.and(andCalculation);
                            currentClockCycle += 2;

                            function = andResult1.rightShift(18);
                            currentClockCycle += 2;

                            registers[i].leftShift(18);
                            currentClockCycle += 2;

                            // Math opcode checks.
                            if (function.getBit(31).getValue().equals(true)) {
                                opBits[i] = new Bit(true);
                                i++;
                                if (function.getBit(30).getValue().equals(false)) {
                                    opBits[i] = new Bit(false);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(false);
                                        i++;
                                        if (shiftValue.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        }
                                    } else {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        }
                                    }
                                } else {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(false);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        }
                                    } else {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rs1, Rs2);
                                        }
                                    }
                                }
                            } else {
                                opBits[i] = new Bit(false);
                                i++;
                                if (function.getBit(30).getValue().equals(true)) {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(true)) {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(true)) {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rs2, Rs1);
                                        }
                                    }
                                }
                            }
                        } else {
                            // Math opcode checks.
                            if (function.getBit(31).getValue().equals(true)) {
                                opBits[i] = new Bit(true);
                                i++;
                                if (function.getBit(30).getValue().equals(false)) {
                                    opBits[i] = new Bit(false);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(false);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        }
                                    } else {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        }
                                    }
                                } else {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(false);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        }
                                    } else {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        } else {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        }
                                    }
                                }
                            } else {
                                opBits[i] = new Bit(false);
                                i++;
                                if (function.getBit(30).getValue().equals(true)) {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(true)) {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(true)) {
                                            opBits[i] = new Bit(true);
                                            // Call Math Op.
                                            Math(Rd, Rs);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // Check branch.
                    if (shiftValue.getBit(28).getValue().equals(false)) {
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            PC = immediateValue;
                            currentClockCycle += 300;
                        } else {
                            PC = executeOP.add(PC, immediateValue);
                            currentClockCycle += 2;
                        }
                    } else {
                        if (shiftValue.getBit(27).getValue().equals(false)) {

                            // Check BOP code.
                            if (function.getBit(31).getValue().equals(false)) {
                                opBits[i] = new Bit(true);
                                i++;
                                if (function.getBit(30).getValue().equals(false)) {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Equals.
                                            if (BOP(Rs1, Rs2).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }

                                        } else {
                                            // Not equals.
                                            if (BOP(Rs1, Rs2).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        }
                                    } else {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Less than.
                                            if (BOP(Rs1, Rs2).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        } else {
                                            // Greater than or equal.
                                            if (BOP(Rs1, Rs2).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        }
                                    }
                                } else {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Greater than.
                                            if (BOP(Rs1, Rs2).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        } else {
                                            opBits[i] = new Bit(false);
                                            // Less than or equal to.
                                            if (BOP(Rs1, Rs2).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            // Check BOP code.
                            if (function.getBit(31).getValue().equals(false)) {
                                opBits[i] = new Bit(true);
                                i++;
                                if (function.getBit(30).getValue().equals(false)) {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Equals.
                                            if (BOP(Rs, Rd).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        } else {
                                            opBits[i] = new Bit(false);
                                            // Not equals.
                                            if (BOP(Rs, Rd).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        }
                                    } else {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Less than.
                                            if (BOP(Rs, Rd).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        } else {
                                            opBits[i] = new Bit(false);
                                            // Greater than or equal.
                                            if (BOP(Rs, Rd).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        }
                                    }
                                } else {
                                    opBits[i] = new Bit(true);
                                    i++;
                                    if (function.getBit(29).getValue().equals(false)) {
                                        opBits[i] = new Bit(true);
                                        i++;
                                        if (function.getBit(28).getValue().equals(false)) {
                                            opBits[i] = new Bit(false);
                                            // Greater than.
                                            if (BOP(Rs, Rd).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        } else {
                                            opBits[i] = new Bit(false);
                                            // Less than or equal to.
                                            if (BOP(Rs, Rd).equals(true)) {
                                                PC = executeOP.result;
                                                currentClockCycle += 2;
                                            } else {
                                                PC = PC;
                                                currentClockCycle += 300;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                opBits[i] = new Bit(function.getBit(31).getValue());
                i++;
                if (shiftValue.getBit(29).getValue().equals(false)) {
                    opBits[i] = new Bit(function.getBit(30).getValue());
                    i++;
                    // Check call. (Ask in morning)
                    if (shiftValue.getBit(28).getValue().equals(false)) {
                        opBits[i] = new Bit(function.getBit(29).getValue());
                        i++;

                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            // No R 00
                            Word previousPC = PC;
                            PC = immediateValue;
                            currentClockCycle += 300;

                            // Push previous PC to the stack.
                            SP.decrement();
                            L2.write(SP, previousPC);
                            currentClockCycle += 300;
                        } else {
                            // Dest Only 01
                            // Push previous PC to the stack.
                            Word previousPC = PC;
                            executeOP = new ALU(Rd, immediateValue);
                            executeOP.add(Rd, immediateValue);
                            currentClockCycle += 2;
                            PC = executeOP.result;

                            SP.decrement();
                            L2.write(SP, previousPC);
                            currentClockCycle += 300;
                        }
                    } else {
                        opBits[i] = new Bit(function.getBit(29).getValue());
                        i++;
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            opBits[i] = new Bit(function.getBit(28).getValue());
                            // 3R 10
                            if (BOP(Rs1, Rs2).equals(true)) {
                                // Push previous PC to the stack.
                                Word previousPC = PC;
                                SP.decrement();
                                L2.write(SP, previousPC);
                                currentClockCycle += 300;
                            }
                            executeOP = new ALU(Rd, immediateValue);
                            PC = executeOP.add(Rd, immediateValue);
                            currentClockCycle += 2;
                        } else {
                            // 2R 11
                            if (BOP(Rs, Rd).equals(true)) {
                                // Push previous PC to the stack.
                                Word previousPC = PC;
                                SP.decrement();
                                L2.write(SP, previousPC);
                                currentClockCycle += 300;
                            }
                            executeOP = new ALU(PC, immediateValue);
                            PC = executeOP.add(PC, immediateValue);
                            currentClockCycle += 2;
                        }
                    }
                } else {
                    opBits[i] = new Bit(function.getBit(30).getValue());
                    i++;
                    // Check push.
                    if (shiftValue.getBit(28).getValue().equals(false)) {
                        opBits[i] = new Bit(function.getBit(29).getValue());
                        i++;
                        if (shiftValue.getBit(27).getValue().equals(true)) {
                            opBits[i] = new Bit(function.getBit(28).getValue());
                            // Dest Only 01
                            Math(Rd, immediateValue);
                            SP.decrement();
                            L2.write(SP, executeResult);
                            currentClockCycle += 300;
                        }
                    } else {
                        opBits[i] = new Bit(function.getBit(29).getValue());
                        i++;
                        if (shiftValue.getBit(28).getValue().equals(false)) {
                            opBits[i] = new Bit(function.getBit(28).getValue());
                            // 3R 10
                            Math(Rs1, Rs2);
                            SP.decrement();
                            L2.write(SP, executeResult);
                            currentClockCycle += 300;
                        } else {
                            opBits[i] = new Bit(function.getBit(28).getValue());
                            // 2R 11
                            Math(Rd, Rs);
                            SP.decrement();
                            L2.write(SP, executeResult);
                            currentClockCycle += 300;
                        }
                    }
                }
            }
        } else {
            if (shiftValue.getBit(30).getValue().equals(false)) {
                opBits[i] = new Bit(true);
                i++;
                if (shiftValue.getBit(29).getValue().equals(false)) {
                    opBits[i] = new Bit(true);
                    i++;

                    // Check load.
                    if (shiftValue.getBit(28).getValue().equals(false)) {
                        opBits[i] = new Bit(true);
                        i++;
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            opBits[i] = new Bit(false);
                            // Return (idk if this is correct).
                            SP.increment();
                            L2.read(SP);
                            currentClockCycle += 300;
                            PC = Rd;
                        } else {
                            opBits[i] = new Bit(false);
                            // Dest 01.
                            executeOP = new ALU(Rd, immediateValue);
                            executeOP.doOperation(opBits);
                            currentClockCycle += 2;
                            Word mathResult = executeOP.result;
                            currentClockCycle += 2;
                            Rd = L2.read(mathResult);
                            currentClockCycle += 300;
                        }
                    } else {
                        opBits[i] = new Bit(true);
                        i++;
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            opBits[i] = new Bit(false);
                            // 3R 10.
                            executeOP = new ALU(Rs1, Rs2);
                            executeOP.doOperation(opBits);
                            currentClockCycle += 2;
                            Word mathResult = executeOP.result;
                            currentClockCycle += 2;
                            Rd = L2.read(mathResult);
                            currentClockCycle += 300;
                        } else {
                            opBits[i] = new Bit(false);
                            // 2R 11.
                            executeOP = new ALU(Rs, immediateValue);
                            executeOP.doOperation(opBits);
                            currentClockCycle += 2;
                            Word mathResult = executeOP.result;
                            currentClockCycle += 2;
                            Rd = L2.read(mathResult);
                            currentClockCycle += 300;
                        }
                    }
                } else {
                    opBits[i] = new Bit(true);
                    i++;

                    // Check store.
                    if (shiftValue.getBit(28).getValue().equals(false)) {
                        if (shiftValue.getBit(27).getValue().equals(true)) {
                            // Dest Only 01
                            L2.write(Rd, immediateValue);
                            currentClockCycle += 300;
                        }
                    } else {
                        opBits[i] = new Bit(true);
                        i++;
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            opBits[i] = new Bit(false);
                            // 3R 10
                            executeOP = new ALU(Rd, Rs1);
                            executeOP.doOperation(opBits);
                            currentClockCycle += 2;
                            Word mathResult = executeOP.result;
                            currentClockCycle += 2;
                            L2.write(mathResult, Rs2);
                            currentClockCycle += 300;
                        } else {
                            opBits[i] = new Bit(true);
                            // 2R 11
                            executeOP = new ALU(Rd, immediateValue);
                            executeOP.doOperation(opBits);
                            currentClockCycle += 2;
                            Word mathResult = executeOP.result;
                            currentClockCycle += 2;
                            L2.write(mathResult, Rs);
                            currentClockCycle += 300;
                        }
                    }
                }
            } else {
                opBits[i] = new Bit(true);
                i++;
                if (shiftValue.getBit(29).getValue().equals(false)) {
                    opBits[i] = new Bit(true);
                    i++;

                    // Check pop.
                    if (shiftValue.getBit(28).getValue().equals(false)) {
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            // Interrupt is not implemented for now.
                        } else {
                            // Dest Only 01.
                            SP.increment();
                            Rd = L2.read(SP);
                            currentClockCycle += 300;
                        }
                    } else {
                        opBits[i] = new Bit(true);
                        i++;
                        if (shiftValue.getBit(27).getValue().equals(false)) {
                            opBits[i] = new Bit(false);
                            i++;
                            // 3R 10.
                            executeOP = new ALU(Rs1, Rs2);
                            executeOP.doOperation(opBits);
                            currentClockCycle += 2;
                            Word mathResult = executeOP.subtract(SP, executeOP.result);
                            currentClockCycle += 2;
                            Rd = L2.read(mathResult);
                            currentClockCycle += 300;
                        } else {
                            opBits[i] = new Bit(false);
                            // 2R 11.
                            executeOP = new ALU(Rs, immediateValue);
                            executeOP.doOperation(opBits);
                            currentClockCycle += 2;
                            Word mathResult = executeOP.subtract(SP, executeOP.result);
                            currentClockCycle += 2;
                            Rd = L2.read(mathResult);
                            currentClockCycle += 300;
                        }
                    }
                }
            }
        }

    }

    /**
     * Math OP is written as a separate method here, so it can be used for Call and Push.
     * @param word1
     * @param word2
     */
    void Math(Word word1, Word word2) {
        for (int i = 0; i < opBits.length; i++) {
            if (opBits[i].getValue().equals(true)) {
                i++;
                if (opBits[i].getValue().equals(false)) {
                    i++;
                    if (opBits[i].getValue().equals(false)) {
                        i++;
                        if (opBits[i].getValue().equals(false)) {
                            // And.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        } else {
                            // Or.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        }
                    } else {
                        i++;
                        if (opBits[i].getValue().equals(false)) {
                            // Xor.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        } else {
                            // Not.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        }
                    }
                } else {
                    i++;
                    if (opBits[i].getValue().equals(false)) {
                        i++;
                        if (opBits[i].getValue().equals(false)) {
                            // Left Shift.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        } else {
                            // Right shift.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        }
                    } else {
                        i++;
                        if (opBits[i].getValue().equals(false)) {
                            // Add.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        } else {
                            // Subtract.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 2;
                        }
                    }
                }
            } else {
                i++;
                if (opBits[i].getValue().equals(true)) {
                    i++;
                    if (opBits[i].getValue().equals(true)) {
                        i++;
                        if (opBits[i].getValue().equals(true)) {
                            // Multiply.
                            executeOP = new ALU(word1, word2);
                            executeOP.doOperation(opBits);
                            executeResult = executeOP.result;
                            currentClockCycle += 10;
                        }
                    }
                }
            }
        }
    }

    /**
     * BOP is written as a separate method since BOP is used in Call and Push.
     * @param word1
     * @param word2
     */
    Boolean BOP(Word word1, Word word2) {
        // Variable declaration.
        Boolean resultBOP = false;

        for (int i = 0; i < opBits.length; i++) {
            if (opBits[i].getValue().equals(false)) {
                i++;
                if (opBits[i].getValue().equals(false)) {
                    i++;
                    if (opBits[i].getValue().equals(false)) {
                        i++;
                        if (opBits[i].getValue().equals(false)) {
                            // Equals.
                            if (word1.equals(word2)){
                                executeOP = new ALU(PC, immediateValue);
                                executeOP.doOperation(opBits);
                                currentClockCycle += 2;
                                resultBOP = true;
                            } else {
                                resultBOP = false;
                            }
                        } else {
                            // Not equal.
                            if (!(word1.equals(word2))){
                                executeOP = new ALU(PC, immediateValue);
                                executeOP.doOperation(opBits);
                                currentClockCycle += 2;
                                resultBOP = true;
                            } else {
                                resultBOP = false;
                            }
                        }
                    } else {
                        i++;
                        if (opBits[i].getValue().equals(false)) {
                            // Less than.
                            Word subtractionResult = executeOP.subtract(word1, word2);

                            if (subtractionResult.getBit(31).getValue().equals(true)) {
                                executeOP = new ALU(PC, immediateValue);
                                executeOP.doOperation(opBits);
                                currentClockCycle += 2;
                                resultBOP = true;
                            } else {
                                resultBOP = false;
                            }
                        } else {
                            // Greater than or equal.
                            Word subtractionResult = executeOP.subtract(word1, word2);

                            if (!(subtractionResult.getBit(31).getValue().equals(true)) || word1.equals(word2)) {
                                executeOP = new ALU(PC, immediateValue);
                                executeOP.doOperation(opBits);
                                currentClockCycle += 2;
                                resultBOP = true;
                            } else {
                                resultBOP = false;
                            }
                        }
                    }
                } else {
                    i++;
                    if (opBits[i].getValue().equals(false)) {
                        i++;
                        if (opBits[i].getValue().equals(false)) {
                            // Greater than.
                            Word subtractionResult = executeOP.subtract(word1, word2);
                            currentClockCycle += 2;

                            if (!(subtractionResult.getBit(31).getValue().equals(true))) {
                                executeOP = new ALU(PC, immediateValue);
                                executeOP.doOperation(opBits);
                                currentClockCycle += 2;
                                resultBOP = true;
                            } else {
                                resultBOP = false;
                            }
                        } else {
                            // Less than or equal.
                            Word subtractionResult = executeOP.subtract(word1, word2);
                            currentClockCycle += 2;

                            if (subtractionResult.getBit(31).getValue().equals(true) || word1.equals(word2)) {
                                executeOP = new ALU(PC, immediateValue);
                                executeOP.doOperation(opBits);
                                currentClockCycle += 2;
                                resultBOP = true;
                            } else {
                                resultBOP = false;
                                currentClockCycle += 300;
                            }
                        }
                    }
                }
            }
        }
        return resultBOP;
    }

    /**
     * This method copies the result from ALU into the appropriate register.
     */
    void store(){
        for (int i = 0; i < registers.length; i++) {
            // Variable declarations.
            int conversionResult = 0;
            int value = 1;
            if (registers[i].getBit(31).getValue().equals(false)) {
                if (registers[i].getBit(30).getValue().equals(false)) {
                    new Bit(false);
                    currentClockCycle += 300;
                } else {
                    var posVersion = this.Rd.not();
                    currentClockCycle += 2;

                    for (int j = 0; j < 32; j++){
                        if (posVersion.getBit(i).getValue()) {
                            conversionResult += value;
                        }
                        value *= 2;
                    }

                    // R0 stuff.
                    if (conversionResult == 0) {
                        registers[conversionResult] = new Word();
                    }
                    registers[conversionResult].copy(executeResult);
                    currentClockCycle += 2;
                }
            } else {
                if (registers[i].getBit(30).getValue().equals(false)) {
                    var posVersion = this.Rd.not();
                    currentClockCycle += 2;

                    for (int j = 0; j < 32; j++){
                        if (posVersion.getBit(i).getValue()) {
                            conversionResult += value;
                        }
                        value *= 2;
                    }

                    // R0 stuff.
                    if (conversionResult == 0) {
                        registers[conversionResult] = new Word();
                    }
                    registers[conversionResult].copy(executeResult);
                    currentClockCycle += 2;
                } else {
                    for (int j = 0; j < 32; j++){
                        if (Rd.getBit(j).getValue().equals(true)){
                            value *= 2;
                            conversionResult += value;
                        }
                    }

                    // R0 stuff.
                    if (conversionResult == 0) {
                        registers[conversionResult] = new Word();
                    }
                    registers[conversionResult].copy(executeResult);
                    currentClockCycle += 2;
                }
            }
        }
    }

    /**
     * This method fetches the next instruction before calling increment() on PC.
     */
    void fetch() throws Exception {
        if (PC.equals(InstructionCache.address1)) {
            currentInstruction = InstructionCache.read(PC);
            PC.increment();
            currentClockCycle += 10;
        } else {
            currentClockCycle += 350;
        }

        if (PC.equals(L2.address1) || PC.equals(L2.address2) || PC.equals(L2.address3) || PC.equals(L2.address4)) {
            currentInstruction = L2.read(PC);
            PC.increment();
            currentClockCycle += 50;
        } else {
            currentClockCycle += 20;
        }

        if (PC.equals(MainMemory.read(PC))) {
            currentInstruction = MainMemory.read(PC);
            PC.increment();
            currentClockCycle += 300;
        } else {
            currentClockCycle += 600;
        }
    }
}