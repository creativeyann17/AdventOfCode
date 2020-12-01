package year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day2 {

  public static void main(String[] args) throws IOException {
    Computer computer = Computer.getInstanceFromPath("./data/2019/input2.txt");
    System.out.println(computer.code);
    System.out.println(computer.execute());
    System.out.println(computer.execute(19690720));
  }

  public static class Computer {
    public List<Integer> code = new ArrayList<>();

    public List<Integer> execute(int expected) {
      for (int i = 0; i < 99; i++) {
        for (int j = 0; j < 99; j++) {
          code.set(1, i);
          code.set(2, j);
          List<Integer> results = execute();
          if (results.get(0) == expected) {
            return results;
          }
        }
      }
      return null;
    }

    public List<Integer> execute() {
      List<Integer> results = new ArrayList<>(code);
      for (int i = 0; i < results.size(); i += 4) {
        Operation operation = Operation.fromValue(results.get(i)); //value
        if (Operation.EXIT.equals(operation))
          break;
        int operandLeft = results.get(results.get(i + 1)); //ref
        int operandRight = results.get(results.get(i + 2)); //ref
        int operandResult = results.get(i + 3); //value
        int result = 0;
        switch (operation) {
          case ADD:
            result = operandLeft + operandRight;
            break;
          case MULT:
            result = operandLeft * operandRight;
            break;
        }
        results.set(operandResult, result);
      }
      return results;
    }

    public static Computer getInstanceFromPath(String path) throws IOException {
      Computer computer = new Computer();
      final StringBuilder builder = new StringBuilder();
      Stream<String> stream = Files.lines(Paths.get(path));
      stream.forEach(line -> builder.append(line.trim()));
      Arrays.asList(builder.toString().split(",")).stream().forEach(instruction -> computer.code.add(Integer.parseInt(instruction)));
      return computer;
    }
  }


  private enum Operation {
    ADD(1), MULT(2), EXIT(99);

    public int value;

    private Operation(int value) {
      this.value = value;
    }

    public static Operation fromValue(int value) {
      return Arrays.asList(Operation.values()).stream().filter(op -> op.value == value).findFirst().orElse(null);
    }

  }

}
