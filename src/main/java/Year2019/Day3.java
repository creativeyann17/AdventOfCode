package Year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Day3 {

  public static WirePosition ORIGIN = new WirePosition(0, 0);

  public static void main(String[] args) throws IOException {
    Stream<String> stream = Files.lines(Paths.get("./data/input3.txt"));
    List<String> lines = new ArrayList<>();
    stream.forEach(line -> lines.add(line));

    Wire wire1 = new Wire();
    wire1.draw(lines.get(0));
    System.out.println(wire1.positions.size());
    Wire wire2 = new Wire();
    wire2.draw(lines.get(1));
    System.out.println(wire2.positions.size());

    WirePosition[] collision = wire1.getManhattanDistance(wire2, false);
    System.out.println(collision[0] + " sum " + (collision[0].x + collision[0].y)); // answer 1 was 557

    WirePosition[] fewerSteps = wire1.getManhattanDistance(wire2, true);
    System.out.println(Arrays.asList(fewerSteps) + " steps " + (fewerSteps[0].steps + fewerSteps[1].steps)); // answer 2 was 56410
  }

  public static class Wire {
    public List<WirePosition> positions = new ArrayList<>();

    public void draw(String instructions) {
      List<WireInstruction> wireInstructions = parseInstructions(instructions);
      int positionX = ORIGIN.x;
      int positionY = ORIGIN.y;
      // considering a x,y coord plan, left/right move on x et up/down move on y
      positions = new ArrayList<>();
      for (WireInstruction instruction : wireInstructions) {
        for (int i = 0; i < instruction.distance; i++) {
          switch (instruction.direction) {
            case U:
              positionY++;
              break;
            case D:
              positionY--;
              break;
            case L:
              positionX--;
              break;
            case R:
              positionX++;
              break;
          }
          positions.add(new WirePosition(positionX, positionY, positions.size() + 1));
        }
      }
    }

    public WirePosition[] getManhattanDistance(Wire other, boolean preferLowerStepsOverDistance) {
      WirePosition lastGoodP1 = null;
      WirePosition lastGoodP2 = null;

      List<WirePosition> copyOfPositions1 = new ArrayList<>(this.positions);
      List<WirePosition> copyOfPositions2 = new ArrayList<>(other.positions);

      Iterator<WirePosition> it1 = copyOfPositions1.iterator();
      WirePosition pos1, pos2;
      while (it1.hasNext()) {
        pos1 = it1.next();
        Iterator<WirePosition> it2 = copyOfPositions2.iterator();
        while (it2.hasNext()) {
          pos2 = it2.next();
          if (pos1.equals(pos2)) {
            if (preferLowerStepsOverDistance) {
              if (lastGoodP1 == null || lastGoodP2 == null || (pos1.steps + pos2.steps) < (lastGoodP1.steps + lastGoodP2.steps)) {
                lastGoodP1 = pos1;
                lastGoodP2 = pos2;
              }
            } else {
              if (lastGoodP1 == null || pos1.compareTo(ORIGIN) < lastGoodP1.compareTo(ORIGIN)) {
                lastGoodP1 = pos1;
                lastGoodP2 = pos2;
              }
            }

          }
          // optimisation of hell ....
          if (preferLowerStepsOverDistance) {
            removeIfOutOfSteps(lastGoodP1, lastGoodP2, pos1, pos2, it2);
          } else {
            removeIfOutOfRange(lastGoodP1, pos2, it2);
          }
        }
      }

      return new WirePosition[] { lastGoodP1, lastGoodP2 };
    }

    private static void removeIfOutOfSteps(WirePosition betterSolution1, WirePosition betterSolution2, WirePosition currentItPosition1,
        WirePosition currentItPosition2, Iterator<WirePosition> it) {
      if (betterSolution1 != null && betterSolution2 != null && (betterSolution1.steps + betterSolution2.steps) < (currentItPosition1.steps
          + currentItPosition2.steps)) {
        it.remove();
      }
    }

    private static void removeIfOutOfRange(WirePosition betterSolution, WirePosition currentItPosition, Iterator<WirePosition> it) {
      if (betterSolution != null && betterSolution.compareTo(ORIGIN) < currentItPosition.compareTo(ORIGIN)) {
        it.remove();
      }
    }

    private List<WireInstruction> parseInstructions(String instructions) {
      List<WireInstruction> wireInstructions = new ArrayList<>();
      for (String instruction : instructions.split(",")) {
        wireInstructions.add(new WireInstruction(instruction));
      }
      return wireInstructions;
    }
  }


  public static class WirePosition implements Comparable<WirePosition> {
    public int x;
    public int y;
    public int steps;

    public WirePosition(int x, int y) {
      this(x, y, 0);
    }

    public WirePosition(int x, int y, int steps) {
      this.x = x;
      this.y = y;
      this.steps = steps;
    }

    @Override
    public boolean equals(Object obj) {
      return this.x == ((WirePosition) obj).x && this.y == ((WirePosition) obj).y;
    }

    @Override
    public String toString() {
      return String.format("{%s,%s,%s}", x, y, steps);
    }

    @Override
    public int compareTo(WirePosition o) {
      return (int) Math.sqrt(Math.pow(o.y - y, 2) + Math.pow(o.x - x, 2));
    }
  }


  public static class WireInstruction {
    public WireInstructionType direction;
    public int distance;

    public WireInstruction(String instruction) {
      this.direction = WireInstructionType.valueOf(String.valueOf(instruction.charAt(0)));
      this.distance = Integer.parseInt(instruction.substring(1));
    }
  }


  public enum WireInstructionType {
    L, R, U, D
  }

}
