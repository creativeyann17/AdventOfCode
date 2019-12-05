package year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Day5Test {

  @Test
  void Day2() throws IOException {
    assertEquals(Arrays
        .asList(4138687, 12, 2, 2, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 6, 1, 24, 1, 19, 10, 28, 2, 13, 23, 140, 1, 5, 27, 141, 2, 6, 31, 282, 1, 6, 35, 284,
            2, 39, 9, 852, 1, 5, 43, 853, 1, 13, 47, 858, 1, 10, 51, 862, 2, 55, 10, 3448, 2, 10, 59, 13792, 1, 9, 63, 13795, 2, 67, 13, 68975, 1, 71, 6, 68977,
            2, 6, 75, 137954, 1, 5, 79, 137955, 2, 83, 9, 413865, 1, 6, 87, 413867, 2, 91, 6, 827734, 1, 95, 6, 827736, 2, 99, 13, 4138680, 1, 6, 103, 4138682,
            1, 2, 107, 4138684, 1, 111, 9, 0, 99, 2, 14, 0, 0), Day5.execute(Day5.parseInstructions(Utils.readFile("./data/input2.txt"))));
  }


  @Test
  void validateNewImplementation_workSameAsDay2() {
    assertEquals(Arrays.asList(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), Day5.execute(Arrays.asList(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)));
  }

  @Test
  void withParamMode() {
    assertEquals(Arrays.asList(1002, 4, 3, 4, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99),
        Day5.execute(Arrays.asList(1002, 4, 3, 4, 33)));
  }

}
