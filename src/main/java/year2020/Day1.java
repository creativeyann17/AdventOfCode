package year2020;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

import utils.Utils;

public class Day1 {

  public static void main(String[]args) throws IOException {
    List<String> lines = Utils.readLines("./data/2020/input1.txt");
    List<Integer> numbers = lines.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
    computeStep1(2020, numbers);
    computeStep2(2020, numbers);
  }

  public static Integer computeStep1(int expected, List<Integer> numbers) {
    Integer first = null, second = null, result = null;
    out:
    for (int i=0; i< numbers.size(); i++) {
      for (int j=0; j< numbers.size(); j++) {
        first = numbers.get(i);
        second = numbers.get(j);
        result = computeIfValid(expected, first, second);
        if (result != null) {
          break out;
        } else {
          first = second = null;
        }
      }
    }
    if (ObjectUtils.allNotNull(first, second)) {
      System.out.println(String.format("%s * %s = %s", first, second, first * second));
    }
    return result;
  }

  public static Integer computeStep2(int expected, List<Integer> numbers) {
    Integer first = null, second = null, third = null, result = null;
    out:
    for (int i=0; i< numbers.size(); i++) {
      for (int j=0; j< numbers.size(); j++) {
        for (int k = 0; k < numbers.size(); k++) {
          first = numbers.get(i);
          second = numbers.get(j);
          third = numbers.get(k);
          result = computeIfValid(expected, first, second, third);
          if (result != null) {
            break out;
          } else {
            first = second = third = null;
          }
        }
      }
    }
    if (ObjectUtils.allNotNull(first, second, third)) {
      System.out.println(
          String.format("%s * %s * %s = %s", first, second, third, first * second * third));
    }
    return result;
  }

  private static Integer computeIfValid(int expected, int ... numbers) {
    final int sum = Arrays.stream(numbers).reduce(0, Integer::sum);
    if (sum == expected) {
      return Arrays.stream(numbers).reduce(1, (left, right) -> left * right);
    }
    return null;
  }

}
