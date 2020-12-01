package year2020;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Day1Test {

  @Test
  void computeStep1() {
    assertEquals(null, Day1.computeStep1(2020,Arrays.asList(1,1)));
    assertEquals(996996, Day1.computeStep1(2020, Arrays.asList(858,1162)));
  }

  @Test
  void computeStep2() {
    assertEquals(null, Day1.computeStep2(2020,Arrays.asList(1,2,3)));
    assertEquals(9210402, Day1.computeStep2(2020,Arrays.asList(19,282,1719)));
  }

}