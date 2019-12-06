package year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Day6Test {

  @Test
  void test() {
    assertEquals(42, Day6.Graph.getInstance(Arrays.asList("COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L".split(","))).getEdgesCount());
  }
}