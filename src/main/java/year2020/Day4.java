package year2020;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {

  public static void main(String[] args) throws IOException {
    List<String> lines = Utils.readLines("./data/2020/input4.txt");
    computeStep1(lines);
    computeStep2(lines);
  }

  public static void computeStep1(List<String> lines) {
    List<Passport> passports = Passport.getPassports(lines);
    System.out.println(passports.stream().filter(p -> p.isValid(false)).count());
  }

  public static void computeStep2(List<String> lines) {
    List<Passport> passports = Passport.getPassports(lines);
    System.out.println(passports.stream().filter(p -> p.isValid(true)).count());
  }

  public static class Passport {

    public enum Field {
      byr("Birth Year"),
      iyr("Issue Year"),
      eyr("Expiration Year"),
      hgt("Height"),
      hcl("Hair Color"),
      ecl("Eye Color"),
      pid("Passport ID"),
      cid("Country ID", true);
      public String description;
      public boolean optional;

      Field(String description) {
        this(description, false);
      }

      Field(String description, boolean optional) {
        this.description = description;
        this.optional = optional;
      }
    }

    public Map<String, String> data;

    public Passport(List<String> lines) {
      data = new HashMap<>();
      for (String line : lines) {
        String[] datas = line.split(" ");
        for (String data : datas) {
          String[] keyValue = data.split(":");
          this.data.put(keyValue[0], keyValue[1]);
        }
      }
    }

    public boolean isValid(boolean step2) {
      boolean isValid = true;
      for (Field key : Field.values()) {
        String value = data.get(key.name());
        if (!isFieldValid(key, value, step2)) {
          isValid = false;
          break;
        }
      }
      return isValid;
    }

    private boolean isFieldValid(Field field, String value, boolean step2) {
      boolean isValid = true;
      int i;
      if (StringUtils.isBlank(value)) {
        isValid = false;
      } else if (step2) {
        switch (field) {
          case byr:
            i = NumberUtils.toInt(value, -1);
            isValid = i >= 1920 && i <= 2002;
            break;
          case iyr:
            i = NumberUtils.toInt(value, -1);
            isValid = i >= 2010 && i <= 2020;
            break;
          case eyr:
            i = NumberUtils.toInt(value, -1);
            isValid = i >= 2020 && i <= 2030;
            break;
          case hgt:
            String cmOrIn = value.substring(value.length() - 2);
            i = Integer.parseInt(value.substring(0, value.length() - 2));
            if (cmOrIn.equals("cm")) {
              isValid = i >= 150 && i <= 193;
            } else if (cmOrIn.equals("in")) {
              isValid = i >= 59 && i <= 76;
            } else {
              isValid = false;
            }
            break;
          case hcl:
            isValid =
                value.charAt(0) == '#'
                    && value.length() == 7
                    && Integer.parseInt(value.substring(1), 16) > -1;
            break;
          case ecl:
            isValid = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value);
            break;
          case pid:
            i = NumberUtils.toInt(value, -1);
            isValid = i > 0 && value.length() == 9;
            break;
          case cid:
            isValid = true;
            break;
        }
      }
      return isValid || field.optional;
    }

    public static List<Passport> getPassports(List<String> lines) {
      List<Passport> passports = new ArrayList<>();
      List<String> passport = new ArrayList<>();
      for (String line : lines) {
        if (StringUtils.isBlank(line) && passport.size() > 0) {
          passports.add(new Passport(passport));
          passport.clear();
        } else {
          passport.add(line);
        }
      }
      if (passport.size() > 0) {
        passports.add(new Passport(passport));
      }
      return passports;
    }
  }
}
