package Year2019;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;

public class Day1 {
  public static void main(String[]args) throws IOException {
    Stream<String> stream = Files.lines(Paths.get("./data/input1.txt"));
    BigDecimal totalMass = stream.map(mass ->
        computeRequiredFuelForMass(mass)
    ).reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.println(totalMass);
  }

  public static BigDecimal computeRequiredFuelForMass(String mass) {
    if (NumberUtils.isParsable(mass)) {
      BigDecimal requiredFuelForMass = new BigDecimal(mass).divide(new BigDecimal(3), 2, RoundingMode.HALF_EVEN).setScale(0, RoundingMode.DOWN)
          .subtract(new BigDecimal(2));
      return requiredFuelForMass.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : requiredFuelForMass;
    } else {
      return BigDecimal.ZERO;
    }
  }
}
