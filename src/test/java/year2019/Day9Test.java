package year2019;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {
  @Test
  void copyOfItself() {
    Day9.execute(
        Arrays.asList(
            new BigDecimal(109),
            new BigDecimal(1),
            new BigDecimal(204),
            new BigDecimal(-1),
            new BigDecimal(1001),
            new BigDecimal(100),
            new BigDecimal(1),
            new BigDecimal(100),
            new BigDecimal(1008),
            new BigDecimal(100),
            new BigDecimal(16),
            new BigDecimal(101),
            new BigDecimal(1006),
            new BigDecimal(101),
            new BigDecimal(0),
            new BigDecimal(99)),
        BigDecimal.ZERO);
    assertEquals(new BigDecimal("99"), Day9.OUTPUT);
  }

  @Test
  void relativeValue() {
    assertEquals(
        Arrays.asList(new BigDecimal(109), new BigDecimal(19)),
        Day9.execute(Arrays.asList(new BigDecimal(109), new BigDecimal(19)), new BigDecimal(2000)));
    // assertEquals(2019, Day9.relativeAddr);
  }

  @Test
  void bigDigit() {
    assertEquals(
        Arrays.asList(
            new BigDecimal(1102),
            new BigDecimal(34915192),
            new BigDecimal(34915192),
            new BigDecimal(7),
            new BigDecimal(4),
            new BigDecimal(7),
            new BigDecimal(99),
            new BigDecimal("1219070632396864")),
        Day9.execute(
            Arrays.asList(
                new BigDecimal(1102),
                new BigDecimal(34915192),
                new BigDecimal(34915192),
                new BigDecimal(7),
                new BigDecimal(4),
                new BigDecimal(7),
                new BigDecimal(99),
                new BigDecimal(0)),
            BigDecimal.ZERO));
    assertEquals(new BigDecimal("1219070632396864"), Day9.OUTPUT);
  }
}
