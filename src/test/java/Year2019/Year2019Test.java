package Year2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class Year2019Test {

  @Test
  void computeRequiredFuelForFuel() {
    assertEquals(BigDecimal.valueOf(2), Day2.computeRequiredFuelWithFuelForMass("12"));
    assertEquals(BigDecimal.valueOf(966), Day2.computeRequiredFuelWithFuelForMass("1969"));
    assertEquals(BigDecimal.valueOf(50346), Day2.computeRequiredFuelWithFuelForMass("100756"));
  }
}