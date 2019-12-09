package year2019;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {

  public static void main(String[] args) throws IOException {
    String input = Utils.readFile("./data/input7.txt");
    for (int i = 0; i <= 4; i++) {
      Day5.INPUT = i;
      List<Integer> results = Day5.execute(parseInstructions(input));
      System.out.println(results);
    }
  }

  public static List<Integer> parseInstructions(String instructions) {
    return Arrays.asList(instructions.split(",")).stream()
        .map(instruction -> Integer.parseInt(instruction))
        .collect(Collectors.toList());
  }

  public static void amplifiers(String input) {
    for (int i = 0; i <= 4; i++) {
      Day5.INPUT = i;
      List<Integer> results = Day5.execute(parseInstructions(input));
      System.out.println(results);
    }
  }
}
