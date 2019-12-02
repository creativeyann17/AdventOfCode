package Year2019;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day2 {
  public static void main(String[]args) throws IOException {
    Stream<String> stream = Files.lines(Paths.get("./data/input1.txt"));
    BigDecimal totalMass = stream.map(componentMass ->
      computeRequiredFuelWithFuelForMass(componentMass)
    ).reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.println(totalMass);
  }

  public static BigDecimal computeRequiredFuelWithFuelForMass(String componentMass) {
    BigDecimal requiredFuelForFuel = Day1.computeRequiredFuelForMass(componentMass);
    if (requiredFuelForFuel.compareTo(BigDecimal.ZERO) <= 0) {
      return BigDecimal.ZERO;
    } else {
      return requiredFuelForFuel.add(computeRequiredFuelWithFuelForMass(String.valueOf(requiredFuelForFuel)));
    }
  }
}
