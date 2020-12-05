package year2020;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import utils.Utils;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

  public static void main(String[] args) throws IOException {
    List<String> lines = Utils.readLines("./data/2020/input5.txt");
    List<SithId> sithIds = lines.stream().map(Day5::decode).collect(Collectors.toList());
    List<SithId> sorted =
        sithIds.stream().sorted(new SithIdComparator()).collect(Collectors.toList());
    System.out.println(sorted.get(sorted.size() - 1));
    System.out.println(findMissing(sorted));
  }

  public static SithId findMissing(List<SithId> sithIds) {
    SithId sithId = null;
    int firstRow = sithIds.get(0).row + 1;
    int lastRow = sithIds.get(sithIds.size() - 1).row;
    for (int row = firstRow; row < lastRow; row++) { // ignore first row, ignore last row
      for (int col = 0; col < 8; col++) {
        SithId expected = new SithId(row, col);
        if (!sithIds.contains(expected)) {
          sithId = expected;
          break;
        }
      }
    }
    return sithId;
  }

  public static class SithIdComparator implements Comparator<SithId> {
    @Override
    public int compare(SithId l, SithId r) {
      if (l.row == r.row) {
        return l.col - r.col;
      } else {
        return l.row - r.row;
      }
    }
  }

  @ToString
  @EqualsAndHashCode
  public static class SithId {
    public int row, col, seatId;

    public SithId(int row, int col) {
      this.row = row;
      this.col = col;
      this.seatId = (row * 8) + col;
    }
  }

  public static SithId decode(String line) {
    int row = -1, col = -1;
    int rowMin = 0, rowMax = 127;
    int colMin = 0, colMax = 7;
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      switch (c) {
        case 'F':
          if (i == 6) {
            row = rowMin;
          } else {
            rowMax -= compute(rowMin, rowMax);
          }
          break;
        case 'B':
          if (i == 6) {
            row = rowMax;
          } else {
            rowMin += compute(rowMin, rowMax);
          }
          break;
        case 'L':
          if (i == 9) {
            col = colMin;
          } else {
            colMax -= compute(colMin, colMax);
          }
          break;
        case 'R':
          if (i == 9) {
            col = colMax;
          } else {
            colMin += compute(colMin, colMax);
          }
          break;
      }
    }
    return new SithId(row, col);
  }

  private static int compute(int min, int max) {
    return (int) Math.floor(((((double) max - (double) min)) / 2) + 0.5);
  }

  public static void computeStep1(List<String> lines) {}

  public static void computeStep2(List<String> lines) {}
}
