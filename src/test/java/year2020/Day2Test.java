package year2020;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Day2Test {

  @Test
  void containsRuleOnce() {
    assertEquals(true, Day2.containsRuleOnce(new Day2.Rule("1-3 a"), "abcde"));
    assertEquals(false, Day2.containsRuleOnce(new Day2.Rule("1-3 b"), "cdefg"));
    assertEquals(false, Day2.containsRuleOnce(new Day2.Rule("2-9 c"), "ccccccccc"));
  }

  @Test
  void isValidPassword() {
    assertEquals(true, Day2.isValidPassword(new Day2.Rule("1-3 a"), "abcde"));
    assertEquals(false, Day2.isValidPassword(new Day2.Rule("1-3 b"), "cdefg"));
    assertEquals(true, Day2.isValidPassword(new Day2.Rule("2-9 c"), "ccccccccc"));
  }

}