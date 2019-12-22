package year2019;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 {
  public static void main(String[] args) throws IOException {
    Belt belt = Belt.getInstance("./data/input10.txt");
    System.out.println(belt);
    System.out.println(belt.find()); // best x=17 y=23
  }

  public static class Delta {
    public int x;
    public int y;

    public Delta(int x, int y) {
      int gcd = BigInteger.valueOf(x).gcd(BigInteger.valueOf(y)).intValue();
      gcd = gcd == 0 ? 1 : gcd;
      this.x = x / gcd;
      this.y = y / gcd;
    }

    @Override
    public String toString() {
      return String.format("[%s,%s]", x, y);
    }

    @Override
    public boolean equals(Object obj) {
      return this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
      return this.toString().hashCode();
    }
  }

  public static class Belt {
    public List<String> asteroids = new ArrayList<>();
    public int width, height;

    public Belt(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public String get(int x, int y) {
      int index = x + (y * width);
      if (index >= asteroids.size() || index < 0) return null;
      return asteroids.get(x + (y * width));
    }

    public int find() {
      int bestLocationCount = -1;
      for (int y1 = 0; y1 < height; y1++) {
        for (int x1 = 0; x1 < width; x1++) {
          if ("#".equals(this.get(x1, y1))) {
            Set<Delta> visible = new HashSet<>();
            for (int y2 = 0; y2 < height; y2++) {
              for (int x2 = 0; x2 < width; x2++) {
                if (x2 == x1 && y2 == y1) continue;
                if ("#".equals(this.get(x2, y2))) {
                  int deltaX = x2 - x1;
                  int deltaY = y2 - y1;
                  visible.add(new Delta(deltaX, deltaY));
                }
              }
            }
            if (visible.size() > bestLocationCount) {
              bestLocationCount = visible.size();
              System.out.println(x1 + " " + y1 + " " + visible);
            }
          }
        }
      }
      return bestLocationCount;
    }

    public static Belt getInstance(String file) throws IOException {
      List<String> lines = Utils.readLines(file);
      Belt belt = new Belt(lines.get(0).length(), lines.size());
      for (int i = 0; i < lines.size(); i++) {
        char[] lineArray = lines.get(i).toCharArray();
        for (int j = 0; j < lineArray.length; j++) {
          belt.asteroids.add(String.valueOf(lineArray[j]));
        }
      }

      return belt;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          builder.append(get(x, y));
        }
        builder.append("\n");
      }
      return builder.toString();
    }
  }
}
