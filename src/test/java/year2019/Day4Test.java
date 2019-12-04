package year2019;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Day4Test {

  @Test
  void test() {
    // assertTrue(Day4.isValidPassword(111111, 0, 999999)); // works in step1
    assertFalse(Day4.isValidPassword(111111, 0, 999999)); // doesnt work in step 2
    assertFalse(Day4.isValidPassword(223450, 0, 999999));
    assertFalse(Day4.isValidPassword(123789, 0, 999999));
    assertTrue(Day4.isValidPassword(112233, 0, 999999));
    assertFalse(Day4.isValidPassword(444567, 0, 999999));
    assertFalse(Day4.isValidPassword(123456, 200000, 999999));
    assertFalse(Day4.isValidPassword(1234567, 200000, 9999999));
    assertFalse(Day4.isValidPassword(123444, 0, 999999));
    assertTrue(Day4.isValidPassword(111122, 0, 999999));
    assertTrue(Day4.isValidPassword(111223, 0, 999999));
  }
}
