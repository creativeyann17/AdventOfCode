package year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class Day1Test {

  @Test
  void computeRequiredFuelForFuel() {
    assertEquals(BigDecimal.valueOf(2), Day1.computeRequiredFuelWithFuelForMass("12"));
    assertEquals(BigDecimal.valueOf(966), Day1.computeRequiredFuelWithFuelForMass("1969"));
    assertEquals(BigDecimal.valueOf(50346), Day1.computeRequiredFuelWithFuelForMass("100756"));
  }
}