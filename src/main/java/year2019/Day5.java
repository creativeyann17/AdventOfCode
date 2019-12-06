package year2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day5 {

  public static final int OPERATION_EXIT = 99;
  public static final int OPERATION_ADD = 1;
  public static final int OPERATION_MULT = 2;
  public static final int OPERATION_INPUT = 3;
  public static final int OPERATION_OUTPUT = 4;
  public static final int OPERATION_JUMP_IF_TRUE = 5;
  public static final int OPERATION_JUMP_IF_FALSE = 6;
  public static final int OPERATION_LESS_THAN = 7;
  public static final int OPERATION_EQUALS = 8;

  public static int INPUT = -1;
  public static int OUTPUT = -1;

  public static void main(String[] args) throws IOException {
    String input = Utils.readFile("./data/input5.txt");
    execute(parseInstructions(input));
  }

  public static List<Integer> parseInstructions(String instructions) {
    return Arrays.asList(instructions.split(",")).stream().map(instruction -> Integer.parseInt(instruction)).collect(Collectors.toList());
  }

  public static List<Integer> execute(List<Integer> instructions) {
    List<Integer> instructionCopy = new ArrayList<>(instructions);
    Scanner sc = new Scanner(System.in);
    int inc = 0;
    for (int i = 0; i < instructionCopy.size(); i += inc) {
      Parameter operation = Parameter.parse(instructionCopy.get(i));

      inc = 1;

      if (!operation.isValid()) {
        continue;
      }

      if (operation.code == OPERATION_EXIT) {
        break;
      }

      if (operation.code == OPERATION_INPUT) {
        int operandResult = getValueByMode(instructionCopy, Parameter.MODE_IMMEDIATE, i + 1);
        System.out.print("Input: ");
        setValueWithPadding(instructionCopy, INPUT != -1 ? INPUT : sc.nextInt(), operandResult);
        inc = 2;
      } else if (operation.code == OPERATION_OUTPUT) {
        int operandResult = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        System.out.println("Output: " + operandResult);
        OUTPUT = operandResult;
        inc = 2;
      } else if (operation.code == OPERATION_ADD) {
        int operandLeft = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        int operandRight = getValueByMode(instructionCopy, operation.param2Mode, i + 2);
        int operandResult = getValueByMode(instructionCopy, operation.param3Mode, i + 3);
        setValueWithPadding(instructionCopy, operandLeft + operandRight, operandResult);
        inc = 4;
      } else if (operation.code == OPERATION_MULT) {
        int operandLeft = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        int operandRight = getValueByMode(instructionCopy, operation.param2Mode, i + 2);
        int operandResult = getValueByMode(instructionCopy, operation.param3Mode, i + 3);
        setValueWithPadding(instructionCopy, operandLeft * operandRight, operandResult);
        inc = 4;
      } else if (operation.code == OPERATION_JUMP_IF_TRUE) {
        int operandLeft = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        int operandRight = getValueByMode(instructionCopy, operation.param2Mode, i + 2);
        if (operandLeft != 0) {
          i = operandRight;
          inc = 0;
        }
      } else if (operation.code == OPERATION_JUMP_IF_FALSE) {
        int operandLeft = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        int operandRight = getValueByMode(instructionCopy, operation.param2Mode, i + 2);
        if (operandLeft == 0) {
          i = operandRight;
          inc = 0;
        }
      } else if (operation.code == OPERATION_LESS_THAN) {
        int operandLeft = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        int operandRight = getValueByMode(instructionCopy, operation.param2Mode, i + 2);
        int operandResult = getValueByMode(instructionCopy, operation.param3Mode, i + 3);
        setValueWithPadding(instructionCopy, operandLeft < operandRight ? 1 : 0, operandResult);
        inc = 4;
      } else if (operation.code == OPERATION_EQUALS) {
        int operandLeft = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        int operandRight = getValueByMode(instructionCopy, operation.param2Mode, i + 2);
        int operandResult = getValueByMode(instructionCopy, operation.param3Mode, i + 3);
        setValueWithPadding(instructionCopy, operandLeft == operandRight ? 1 : 0, operandResult);
        inc = 4;
      }
    }
    return instructionCopy;
  }

  private static void setValueWithPadding(List<Integer> instructions, int value, int index) {
    for (int i = instructions.size(); i <= index; i++) {
      instructions.add(0);
    }
    instructions.set(index, value);
  }

  private static int getValueByMode(List<Integer> instructions, int paramMode, int index) {
    int value = instructions.get(index);
    if (paramMode == Parameter.MODE_POSITION) {
      value = instructions.get(value);
    }
    return value;
  }

  private static class Parameter {
    public static final int MODE_POSITION = 0;
    public static final int MODE_IMMEDIATE = 1;

    public int code;
    public int param1Mode = MODE_POSITION;
    public int param2Mode = MODE_POSITION;
    public int param3Mode = MODE_IMMEDIATE;

    public static Parameter parse(int value) {
      Parameter param = new Parameter();

      if (value <= 99) {
        param.code = value;
      } else {
        String valueStr = String.format("%05d", value);
        // System.out.println(valueStr);
        param.code = Integer.parseInt(valueStr.substring(3, 5));
        param.param1Mode = Integer.parseInt(String.valueOf(valueStr.charAt(2)));
        param.param2Mode = Integer.parseInt(String.valueOf(valueStr.charAt(1)));
        // param.param3Mode = Integer.parseInt(String.valueOf(valueStr.charAt(0)));
      }
      return param;
    }

    public boolean isValid() {
      return (param1Mode == 0 || param1Mode == 1) && (param2Mode == 0 || param2Mode == 1) && (param2Mode == 0 || param2Mode == 1);
    }
  }

}
