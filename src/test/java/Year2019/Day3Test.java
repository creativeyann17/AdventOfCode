package Year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day3Test {

  @Test
  void distance() {
    Day3.WirePosition p1 = new Day3.WirePosition(5,2);
    assertEquals(5, p1.compareTo(new Day3.WirePosition(0,0)));
    assertEquals(8, p1.compareTo(new Day3.WirePosition(7,10)));
  }

  @Test
  void collision() {
    Day3.Wire w1 = new Day3.Wire();
    w1.draw("R8,U5,L5,D3");
    Day3.Wire w2 = new Day3.Wire();
    w2.draw("U7,R6,D4,L4");
    assertEquals(new Day3.WirePosition(3,3), w1.getManhattanDistance(w2, false)[0]);
  }
}