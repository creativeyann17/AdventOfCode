package year2019;

public class Day4 {

  public static final int MIN = 256310;
  public static final int MAX = 732736;

  public static void main(String[] args) {
    int countOfValidPwd = 0;
    for (int i = MIN; i <= MAX; i++) {
      if (isValidPassword(i, MIN, MAX)) {
        System.out.println(i);
        countOfValidPwd++;
      }
    }
    System.out.println(countOfValidPwd);
  }

  public static boolean isValidPassword(int password, int min, int max) {
    final String pwdStr = String.valueOf(password);
    if (pwdStr.length() != 6) {
      return false;
    }
    if (password < min || password > max) {
      return false;
    }
    int lastDigitInt = -1;
    int wasSameDigit = 0;
    int wasAnyDouble = 0;
    for (char c : pwdStr.toCharArray()) {
      int currentDigit = Integer.parseInt(String.valueOf(c));
      if (currentDigit < lastDigitInt) {
        return false;
      }
      if (lastDigitInt == currentDigit) {
        wasSameDigit++;
      } else {
        if (wasSameDigit > 0 && (wasSameDigit + 1) % 2 != 0) {
          return false; // not a group of pair number
        }
        if (wasSameDigit == 1) {
          wasAnyDouble++; // one double !
        }
        wasSameDigit = 0;
      }
      lastDigitInt = currentDigit;
    }

    if (wasSameDigit > 0 && (wasSameDigit + 1) % 2 != 0) {
      return false; // not a group of pair number
    }
    if (wasSameDigit == 1) {
      wasAnyDouble++; // one double !
    }

    if (wasAnyDouble == 0) {
      return false;
    }

    return true;
  }
  /*
  public static boolean isValidPassword(int password, int min, int max) {
    final String pwdStr = String.valueOf(password);
    if (pwdStr.length() != 6) {
      return false;
    }
    if (password < min || password > max) {
      return false;
    }
    int lastDigitInt = -1;
    int wasSameDigit = 0;
    int wasAnyDouble = 0;
    for (char c : pwdStr.toCharArray()) {
      int currentDigit = Integer.parseInt(String.valueOf(c));
      if (currentDigit < lastDigitInt) {
        return false;
      }
      if (lastDigitInt == currentDigit) {
        wasSameDigit++;
      } else {
        if (wasSameDigit > 0 && (wasSameDigit + 1) % 2 != 0) {
          // return false; // not a group of pair number
        }
        if (wasSameDigit >= 1) {
          wasAnyDouble++; // one double !
        }
        wasSameDigit = 0;
      }
      lastDigitInt = currentDigit;
    }

    if (wasSameDigit > 0 && (wasSameDigit + 1) % 2 != 0) {
      // return false; // not a group of pair number
    }
    if (wasSameDigit >= 1) {
      wasAnyDouble++; // one double !
    }

    return (wasAnyDouble >= 1);
  }*/
}
