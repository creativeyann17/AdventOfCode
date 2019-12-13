package year2019;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

  public static final int OPERATION_EXIT = 99;
  public static final int OPERATION_ADD = 1;
  public static final int OPERATION_MULT = 2;
  public static final int OPERATION_INPUT = 3;
  public static final int OPERATION_OUTPUT = 4;
  public static final int OPERATION_JUMP_IF_TRUE = 5;
  public static final int OPERATION_JUMP_IF_FALSE = 6;
  public static final int OPERATION_LESS_THAN = 7;
  public static final int OPERATION_EQUALS = 8;
  public static final int OPERATION_RELATIVE_ADDR = 9;
  public static BigDecimal OUTPUT = new BigDecimal(-1);

  public static void main(String[] args) throws IOException {
    String input = Utils.readFile("./data/input9.txt");
    execute(parseInstructions(input), BigDecimal.ZERO, 2);
  }

  public static List<BigDecimal> parseInstructions(String instructions) {
    return Arrays.asList(instructions.split(",")).stream()
        .map(instruction -> new BigDecimal(instruction))
        .collect(Collectors.toList());
  }

  public static List<BigDecimal> execute(
      List<BigDecimal> instructions, BigDecimal relativeAddr, int... inputs) {
    List<BigDecimal> instructionCopy = new ArrayList<>(instructions);
    int inc;
    int inputIndex = 0;
    for (int i = 0; i < instructionCopy.size(); i += inc) {
      BigDecimal code = instructionCopy.get(i);
      Day9.Parameter operation = Day9.Parameter.parse(code);

      System.out.println(instructionCopy);

      inc = 1;

      if (!operation.isValid()) {
        continue;
      }

      if (code.equals(new BigDecimal(OPERATION_EXIT))) {
        break;
      }

      if (operation.code == OPERATION_INPUT) {
        BigDecimal operandResult =
            getValueByMode(instructionCopy, Parameter.MODE_IMMEDIATE, relativeAddr, i + 1);
        int input = inputs[inputIndex++];
        setValueWithPadding(
            instructionCopy,
            new BigDecimal(input),
            operandResult,
            operation.param1Mode,
            relativeAddr);
        System.out.println("Input: " + input);
        inc = 2;
      } else if (operation.code == OPERATION_OUTPUT) {
        BigDecimal operandResult =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        OUTPUT = operandResult; // instructionCopy.get(operandResult.intValue());
        System.out.println("Output: " + OUTPUT);
        inc = 2;
      } else if (operation.code == OPERATION_RELATIVE_ADDR) {
        BigDecimal operandResult =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        relativeAddr = relativeAddr.add(operandResult);
        System.out.println("Relative Addr: " + relativeAddr);
        inc = 2;
      } else if (operation.code == OPERATION_ADD) {
        BigDecimal operandLeft =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        BigDecimal operandRight =
            getValueByMode(instructionCopy, operation.param2Mode, relativeAddr, i + 2);
        BigDecimal operandResult =
            getValueByMode(instructionCopy, Parameter.MODE_IMMEDIATE, relativeAddr, i + 3);
        setValueWithPadding(
            instructionCopy,
            operandLeft.add(operandRight),
            operandResult,
            operation.param3Mode,
            relativeAddr);
        inc = 4;
      } else if (operation.code == OPERATION_MULT) {
        BigDecimal operandLeft =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        BigDecimal operandRight =
            getValueByMode(instructionCopy, operation.param2Mode, relativeAddr, i + 2);
        BigDecimal operandResult =
            getValueByMode(instructionCopy, Parameter.MODE_IMMEDIATE, relativeAddr, i + 3);
        setValueWithPadding(
            instructionCopy,
            operandLeft.multiply(operandRight),
            operandResult,
            operation.param3Mode,
            relativeAddr);
        inc = 4;
      } else if (operation.code == OPERATION_JUMP_IF_TRUE) {
        BigDecimal operandLeft =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        BigDecimal operandRight =
            getValueByMode(instructionCopy, operation.param2Mode, relativeAddr, i + 2);
        if (operandLeft.compareTo(BigDecimal.ZERO) != 0) {
          i = operandRight.intValue();
          inc = 0;
        }
      } else if (operation.code == OPERATION_JUMP_IF_FALSE) {
        BigDecimal operandLeft =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        BigDecimal operandRight =
            getValueByMode(instructionCopy, operation.param2Mode, relativeAddr, i + 2);
        if (operandLeft.compareTo(BigDecimal.ZERO) == 0) {
          i = operandRight.intValue();
          inc = 0;
        }
      } else if (operation.code == OPERATION_LESS_THAN) {
        BigDecimal operandLeft =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        BigDecimal operandRight =
            getValueByMode(instructionCopy, operation.param2Mode, relativeAddr, i + 2);
        BigDecimal operandResult =
            getValueByMode(instructionCopy, Parameter.MODE_IMMEDIATE, relativeAddr, i + 3);
        setValueWithPadding(
            instructionCopy,
            operandLeft.compareTo(operandRight) < 0 ? BigDecimal.ONE : BigDecimal.ZERO,
            operandResult,
            operation.param3Mode,
            relativeAddr);
        inc = 4;
      } else if (operation.code == OPERATION_EQUALS) {
        BigDecimal operandLeft =
            getValueByMode(instructionCopy, operation.param1Mode, relativeAddr, i + 1);
        BigDecimal operandRight =
            getValueByMode(instructionCopy, operation.param2Mode, relativeAddr, i + 2);
        BigDecimal operandResult =
            getValueByMode(instructionCopy, Parameter.MODE_IMMEDIATE, relativeAddr, i + 3);
        setValueWithPadding(
            instructionCopy,
            operandLeft.compareTo(operandRight) == 0 ? BigDecimal.ONE : BigDecimal.ZERO,
            operandResult,
            operation.param3Mode,
            relativeAddr);
        inc = 4;
      }
    }
    return instructionCopy;
  }

  private static void fillMemory(List<BigDecimal> instructions, BigDecimal paddingSize) {
    for (int i = instructions.size(); i <= paddingSize.intValue(); i++) {
      instructions.add(BigDecimal.ZERO);
    }
  }

  private static void setValueWithPadding(
      List<BigDecimal> instructions,
      BigDecimal value,
      BigDecimal index,
      int mode,
      BigDecimal relativeAddr) {
    if (mode == Parameter.MODE_RELATIVE) {
      index = index.add(relativeAddr);
    }
    fillMemory(instructions, index);
    instructions.set(index.intValue(), value);
  }

  private static BigDecimal getValueByMode(
      List<BigDecimal> instructions, int paramMode, BigDecimal relativeAddr, int index) {
    fillMemory(instructions, new BigDecimal(index));
    BigDecimal value = instructions.get(index);
    if (paramMode == Day9.Parameter.MODE_POSITION) {
      fillMemory(instructions, value);
      value = instructions.get(value.intValue());
    } else if (paramMode == Parameter.MODE_RELATIVE) {
      fillMemory(instructions, relativeAddr.add(value));
      value = instructions.get(relativeAddr.add(value).intValue());
    }
    return value;
  }

  private static class Parameter {
    public static final int MODE_POSITION = 0;
    public static final int MODE_IMMEDIATE = 1;
    public static final int MODE_RELATIVE = 2;

    public int code;
    public int param1Mode = MODE_POSITION;
    public int param2Mode = MODE_POSITION;
    public int param3Mode = MODE_POSITION;

    public static Day9.Parameter parse(BigDecimal value) {
      Day9.Parameter param = new Day9.Parameter();

      if (value.compareTo(BigDecimal.ZERO) < 0) {
        param.code = value.intValue();
      } else {
        String valueStr = String.format("%05d", value.intValue());
        // System.out.println(valueStr);
        param.code = Integer.parseInt(valueStr.substring(3, 5));
        param.param1Mode = Integer.parseInt(String.valueOf(valueStr.charAt(2)));
        param.param2Mode = Integer.parseInt(String.valueOf(valueStr.charAt(1)));
        param.param3Mode = Integer.parseInt(String.valueOf(valueStr.charAt(0)));
      }

      return param;
    }

    public boolean isValid() {
      return (param1Mode == 0 || param1Mode == 1 || param1Mode == 2)
          && (param2Mode == 0 || param2Mode == 1 || param2Mode == 2)
          && (param3Mode == 0 || param3Mode == 1 || param3Mode == 2);
    }
  }
}
