package year2019;

import java.util.stream.IntStream;

public class Day4 {

  public static final int MIN = 256310;
  public static final int MAX = 732736;

  public static void main(String[] args) {

    long start = System.currentTimeMillis();
    long countOfValidPassword = IntStream.rangeClosed(MIN, MAX).filter(number -> isValidPassword(number, MIN, MAX)).count();
    System.out.println(String.format("Result: %s in %s ms", countOfValidPassword, (System.currentTimeMillis() - start)));

    // step 1: 979
    // step 2: 635
  }

  public static boolean isValidPassword(int password, int min, int max) {
    if (password < min || password > max) {
      return false;
    }
    String passwordStr = String.valueOf(password);
    int previousNuUmber = -1;
    int countOfSameNumber = 0;
    int countOfPair = 0;
    for (char numberStr : passwordStr.toCharArray()) {
      int number = Integer.parseInt(String.valueOf(numberStr));
      if (number < previousNuUmber) {
        return false;
      } else if (number == previousNuUmber) {
        countOfSameNumber++;
      } else {
        if (countOfSameNumber == 1) {
          countOfPair++;
        }
        countOfSameNumber = 0;
      }
      previousNuUmber = number;
    }
    if (countOfSameNumber == 1) {
      countOfPair++;
    }
    return countOfPair > 0;
  }

}
