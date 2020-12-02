package year2020;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.ToString;
import utils.Utils;

public class Day2 {

  public static void main(String[]args) throws IOException {
    List<String> lines = Utils.readLines("./data/2020/input2.txt");
    computeStep1(lines);
    computeStep2(lines);
  }

  public static int computeStep1(List<String> lines) {
    int totalValidPassword = 0;
    for (String line: lines) {
      String[] split = line.split(":");
      if (isValidPassword(new Rule(split[0]), split[1])) {
        totalValidPassword++;
      }
    }
    System.out.println(totalValidPassword);
    return totalValidPassword;
  }

  public static int computeStep2(List<String> lines) {
    int totalValidPassword = 0;
    for (String line: lines) {
      String[] split = line.split(":");
      if (containsRuleOnce(new Rule(split[0]), split[1])) {
        totalValidPassword++;
      }
    }
    System.out.println(totalValidPassword);
    return totalValidPassword;
  }

  public static boolean containsRuleOnce(Rule rule, String password) {
    final boolean min = password.trim().charAt(rule.min-1) == rule.character;
    final boolean max = password.trim().charAt(rule.max-1) == rule.character;
    return min ^ max;
  }

  public static boolean isValidPassword(Rule rule, String password) {
    final int characterCount = StringUtils.countMatches(password.trim(), rule.character);
    return characterCount >= rule.min && characterCount <= rule.max;
  }

  @ToString
  public static class Rule {
    public int min, max;
    public char character;
    public Rule(String rule) {
      String[] decoded = rule.split(" ");
      String[] indexes = decoded[0].split("-");
      min = Integer.parseInt(indexes[0]);
      max = Integer.parseInt(indexes[1]);
      character = decoded[1].trim().charAt(0);
    }
  }
}
