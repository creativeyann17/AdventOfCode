package Year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Day2Test {

  @Test
  void execute() {
    Day2.Computer computer = new Day2.Computer();
    computer.code.addAll(Arrays.asList(1,9,10,3,2,3,11,0,99,30,40,50));
    assertEquals(Arrays.asList(3500,9,10,70,2,3,11,0,99,30,40,50), computer.execute());
  }

}