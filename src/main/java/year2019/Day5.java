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
      int operation = instructionCopy.get(i);
      if (operation == OPERATION_EXIT) {
        break;
      }

      if (operation == OPERATION_INPUT) {
        int operandResult = instructionCopy.get(i + 1);
        System.out.print("Request input (int): ");
        instructionCopy.set(operandResult, sc.nextInt());
      } else if (operation == OPERATION_OUTPUT) {
        int operandResult = instructionCopy.get(i + 1);
        System.out.println(operandResult);
      } else {

        int operandLeft = instructionCopy.get(instructionCopy.get(i + 1));
        int operandRight = instructionCopy.get(instructionCopy.get(i + 2));
        int operandResult = instructionCopy.get(i + 3);

        int result = 0;
        switch (operation) {
          case OPERATION_ADD:
            result = operandLeft + operandRight;
            break;
          case OPERATION_MULT:
            result = operandLeft * operandRight;
            break;
        }

        instructionCopy.set(operandResult, result);
      }
    }
    return instructionCopy;
  }

}
