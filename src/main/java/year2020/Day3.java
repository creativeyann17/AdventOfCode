package year2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.ToString;
import utils.Utils;

public class Day3 {

  public static void main(String[]args) throws IOException {
    List<String> lines = Utils.readLines("./data/2020/input3.txt");
    long v1 = computeStep1(lines, 1, 1);
    long v2 = computeStep1(lines, 1, 3);
    long v3 = computeStep1(lines, 1, 5);
    long v4 = computeStep1(lines, 1, 7);
    long v5 = computeStep1(lines, 2, 1);
    System.out.println(v1*v2*v3*v4*v5);

  }

  public static long computeStep1(List<String> lines, int down, int right) {
    long countOfThree = 0;

    int i = 0;
    int j = 0;
    while(i<lines.size()) {
      char c = lines.get(i).toCharArray()[j];
      if (c == '#'){
        countOfThree++;
      }
      i+= down;
      j += right;
      if (j >= lines.get(0).length()) {
        j = j - lines.get(0).length();
      }
    }
    System.out.println(countOfThree);
    return countOfThree;
  }

  public static void computeStep2(List<String> lines) {

  }

}
