package year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day3Test {

  @Test
  void collision() {
    Day3.Wire w1 = new Day3.Wire();
    w1.draw("R8,U5,L5,D3");
    Day3.Wire w2 = new Day3.Wire();
    w2.draw("U7,R6,D4,L4");
    assertEquals(new Day3.WirePosition(3,3), w1.getManhattanDistance(w2, false)[0]);
  }
}