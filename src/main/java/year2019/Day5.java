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

  public static void main(String[] args) throws IOException {
    String input = Utils.readFile("./data/input5.txt");
    List<Integer> results = execute(parseInstructions(input));
    System.out.println(results);
  }

  public static List<Integer> parseInstructions(String instructions) {
    return Arrays.asList(instructions.split(",")).stream().map(instruction -> Integer.parseInt(instruction)).collect(Collectors.toList());
  }

  public static List<Integer> execute(List<Integer> instructions) {
    List<Integer> instructionCopy = new ArrayList<>(instructions);
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < instructionCopy.size(); i += 4) {
      Parameter operation = Parameter.parse(instructionCopy.get(i));
      if (operation.code == OPERATION_EXIT) {
        break;
      }

      if (operation.code == OPERATION_INPUT) {
        int operandResult = getValueByMode(instructionCopy, Parameter.MODE_IMMEDIATE, i + 1);
        System.out.print("Input: ");
        setValueWithPadding(instructionCopy, sc.nextInt(), operandResult);
      } else if (operation.code == OPERATION_OUTPUT) {
        int operandResult = getValueByMode(instructionCopy, Parameter.MODE_POSITION, i + 1);
        System.out.println("Output: " + operandResult);
      } else if (operation.code == OPERATION_ADD || operation.code == OPERATION_MULT) {

        int operandLeft = getValueByMode(instructionCopy, operation.param1Mode, i + 1);
        int operandRight = getValueByMode(instructionCopy, operation.param2Mode, i + 2);
        int operandResult = getValueByMode(instructionCopy, operation.param3Mode, i + 3);

        int result = 0;
        switch (operation.code) {
          case OPERATION_ADD:
            result = operandLeft + operandRight;
            System.out.println(String.format("%s + %s -> %s (mode: %s)", operandLeft, operandRight, operandResult, operation.param3Mode));
            break;
          case OPERATION_MULT:
            result = operandLeft * operandRight;
            System.out.println(String.format("%s * %s -> %s (mode: %s)", operandLeft, operandRight, operandResult, operation.param3Mode));
            break;
          default:
            continue;
        }

        setValueWithPadding(instructionCopy, result, operandResult);
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
    public int param3Mode = MODE_IMMEDIATE; // by default value store location is not by reference

    public static Parameter parse(int value) {
      Parameter param = new Parameter();
      if (value < 99) {
        param.code = value;
      } else {
        String valueStr = String.format("%05d", value);
        param.code = Integer.parseInt(valueStr.substring(3, 5));
        param.param1Mode = Integer.parseInt(String.valueOf(valueStr.charAt(2)));
        param.param2Mode = Integer.parseInt(String.valueOf(valueStr.charAt(1)));
        param.param3Mode = Integer.parseInt(String.valueOf(valueStr.charAt(0)));
      }
      return param;
    }
  }
}
