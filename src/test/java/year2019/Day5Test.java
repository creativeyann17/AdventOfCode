package year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import utils.Utils;

class Day5Test {

  @Before
  void before() {
    Day5.INPUT = 0;
  }

  @Test
  void Day2() throws IOException {
    assertEquals(Arrays
        .asList(4138687, 12, 2, 2, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 6, 1, 24, 1, 19, 10, 28, 2, 13, 23, 140, 1, 5, 27, 141, 2, 6, 31, 282, 1, 6, 35, 284,
            2, 39, 9, 852, 1, 5, 43, 853, 1, 13, 47, 858, 1, 10, 51, 862, 2, 55, 10, 3448, 2, 10, 59, 13792, 1, 9, 63, 13795, 2, 67, 13, 68975, 1, 71, 6, 68977,
            2, 6, 75, 137954, 1, 5, 79, 137955, 2, 83, 9, 413865, 1, 6, 87, 413867, 2, 91, 6, 827734, 1, 95, 6, 827736, 2, 99, 13, 4138680, 1, 6, 103, 4138682,
            1, 2, 107, 4138684, 1, 111, 9, 0, 99, 2, 14, 0, 0), Day5.execute(Day5.parseInstructions(Utils.readFile("./data/2019/input2.txt"))));
  }

  @Test
  void input_output() throws IOException {
    Day5.INPUT = 1;
    assertEquals(Arrays.asList(1, 0, 4, 0, 99), Day5.execute(Arrays.asList(3, 0, 4, 0, 99)));
  }

  @Test
  void negative() throws IOException {
    assertEquals(Arrays.asList(1101, 100, -1, 4, 99), Day5.execute(Arrays.asList(1101, 100, -1, 4, 0)));
  }


  @Test
  void validateNewImplementation_workSameAsDay2() {
    assertEquals(Arrays.asList(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), Day5.execute(Arrays.asList(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)));
  }

  @Test
  void withParamMode() {
    assertEquals(Arrays.asList(1002, 4, 3, 4, 99), Day5.execute(Arrays.asList(1002, 4, 3, 4, 33)));
  }

  @Test
  void equals8_positionMode() {
    Day5.INPUT = 8;
    assertEquals(Arrays.asList(3, 9, 8, 9, 10, 9, 4, 9, 99, 1, 8), Day5.execute(Arrays.asList(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)));
    assertEquals(1, Day5.OUTPUT);

    Day5.INPUT = 7;
    assertEquals(Arrays.asList(3, 9, 8, 9, 10, 9, 4, 9, 99, 0, 8), Day5.execute(Arrays.asList(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)));
    assertEquals(0, Day5.OUTPUT);
  }

  @Test
  void lessThan8_positionMode() {
    Day5.INPUT = 7;
    assertEquals(Arrays.asList(3, 9, 7, 9, 10, 9, 4, 9, 99, 1, 8), Day5.execute(Arrays.asList(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)));
    assertEquals(1, Day5.OUTPUT);

    Day5.INPUT = 9;
    assertEquals(Arrays.asList(3, 9, 7, 9, 10, 9, 4, 9, 99, 0, 8), Day5.execute(Arrays.asList(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)));
    assertEquals(0, Day5.OUTPUT);
  }

  @Test
  void equals8_immediateMode() {
    Day5.INPUT = 8;
    assertEquals(Arrays.asList(3, 3, 1108, 1, 8, 3, 4, 3, 99), Day5.execute(Arrays.asList(3, 3, 1108, -1, 8, 3, 4, 3, 99)));
    assertEquals(1, Day5.OUTPUT);

    Day5.INPUT = 9;
    assertEquals(Arrays.asList(3, 3, 1108, 0, 8, 3, 4, 3, 99), Day5.execute(Arrays.asList(3, 3, 1108, -1, 8, 3, 4, 3, 99)));
    assertEquals(0, Day5.OUTPUT);
  }

  @Test
  void lessThan8_immediateMode() {
    Day5.INPUT = 7;
    assertEquals(Arrays.asList(3, 3, 1107, 1, 8, 3, 4, 3, 99), Day5.execute(Arrays.asList(3, 3, 1107, -1, 8, 3, 4, 3, 99)));
    assertEquals(1, Day5.OUTPUT);

    Day5.INPUT = 9;
    assertEquals(Arrays.asList(3, 3, 1107, 0, 8, 3, 4, 3, 99), Day5.execute(Arrays.asList(3, 3, 1107, -1, 8, 3, 4, 3, 99)));
    assertEquals(0, Day5.OUTPUT);
  }

  @Test
  void jump_positionMode() {
    Day5.INPUT = 2;
    assertEquals(Arrays.asList(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, 2, 1, 1, 9),
        Day5.execute(Arrays.asList(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)));
    assertEquals(1, Day5.OUTPUT);

    Day5.INPUT = 0;
    assertEquals(Arrays.asList(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, 0, 0, 1, 9),
        Day5.execute(Arrays.asList(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)));
    assertEquals(0, Day5.OUTPUT);
  }

  @Test
  void jump_immediateMode() {
    Day5.INPUT = 2;
    assertEquals(Arrays.asList(3, 3, 1105, 2, 9, 1101, 0, 0, 12, 4, 12, 99, 1), Day5.execute(Arrays.asList(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)));
    assertEquals(1, Day5.OUTPUT);

    Day5.INPUT = 0;
    assertEquals(Arrays.asList(3, 3, 1105, 0, 9, 1101, 0, 0, 12, 4, 12, 99, 0), Day5.execute(Arrays.asList(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)));
    assertEquals(0, Day5.OUTPUT);
  }

  @Test
  void big_example_equals8() {
    Day5.INPUT = 8;
    assertEquals(Arrays
        .asList(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 1000, 8, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999,
            1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99), Day5.execute(Arrays
        .asList(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999,
            1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99)));
    assertEquals(1000, Day5.OUTPUT);
  }

  @Test
  void big_example_below8() {
    Day5.INPUT = 7;
    assertEquals(Arrays
        .asList(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 7, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999,
            1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99), Day5.execute(Arrays
        .asList(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999,
            1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99)));
    assertEquals(999, Day5.OUTPUT);
  }


  @Test
  void big_example_bigger8() {
    Day5.INPUT = 9;
    assertEquals(Arrays
        .asList(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 1001, 9, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999,
            1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99), Day5.execute(Arrays
        .asList(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999,
            1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99)));
    assertEquals(1001, Day5.OUTPUT);
  }



}
