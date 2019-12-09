package year2019;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {

  public static void main(String[] args) throws IOException {
    // step 1 = 23104 = 67023
    String input = Utils.readFile("./data/input7.txt");
    String bestPhases = null;
    int bestOutput = -1;

    final int min = 0;
    final int max = 4;

    for (int i = min; i <= max; i++) {
      for (int j = min; j <= max; j++) {
        for (int k = min; k <= max; k++) {
          for (int e = min; e <= max; e++) {
            for (int f = min; f <= max; f++) {
              String phases = String.format("%s%s%s%s%s", i, j, k, e, f);
              boolean isValid = true;
              for (int z = min; z <= max; z++) {
                if (phases.contains(String.valueOf(z))
                    && phases.replaceAll(String.valueOf(z), "").length() != 4) {
                  isValid = false;
                  break;
                }
              }

              if (isValid) {

                List<Integer> ampA = Day5.execute(parseInstructions(input), i, 0);

                List<Integer> ampB = Day5.execute(parseInstructions(input), j, Day5.OUTPUT);

                List<Integer> ampC = Day5.execute(parseInstructions(input), k, Day5.OUTPUT);

                List<Integer> ampD = Day5.execute(parseInstructions(input), e, Day5.OUTPUT);

                List<Integer> ampE = Day5.execute(parseInstructions(input), f, Day5.OUTPUT);

                if (bestOutput == -1 || Day5.OUTPUT > bestOutput) {
                  bestPhases = String.format("%s%s%s%s%s = %s", i, j, k, e, f, Day5.OUTPUT);
                  bestOutput = Day5.OUTPUT;
                }
              }
            }
          }
        }
      }
      System.out.println(bestPhases);
    }
  }

  public static List<Integer> parseInstructions(String instructions) {
    return Arrays.asList(instructions.split(",")).stream()
        .map(instruction -> Integer.parseInt(instruction))
        .collect(Collectors.toList());
  }
}
