package year2019;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day14 {
  public static void main(String[] args) throws IOException {
    List<String> lines = Utils.readLines("./data/input14.txt");
    Map<String, Chemical> chemicals = new TreeMap<>();
    for (String line : lines) {
      Chemical chemical = Chemical.getInstance(line);
      chemicals.put(chemical.name, chemical);
    }
    System.out.println(chemicals);

    Chemical fuel = chemicals.get("FUEL");
    Map<String, Integer> allRequired = new TreeMap<>();
    fuel.getAllRequired(chemicals, " ORE", allRequired);
    System.out.println(allRequired);
    BigDecimal count = BigDecimal.ZERO;
    for (String c : allRequired.keySet()) {
      double qc = allRequired.get(c);
      if (c.equals("ORE")) {
        count = count.add(new BigDecimal(qc));
      } else {
        count = count.add(new BigDecimal(chemicals.get(c).getRequired(chemicals, "ORE") / qc));
      }
    }
    System.out.println(count);
  }

  public static class Chemical {
    public String name;
    public Integer quantity;
    public Map<String, Integer> required = new TreeMap<>();

    public static Chemical getInstance(String str) {
      String[] requiredVsResult = str.split("=>");
      String[] required = requiredVsResult[0].trim().split(",");
      String[] name = requiredVsResult[1].trim().split(" ");
      Chemical chemical = new Chemical();
      chemical.name = name[1].trim();
      chemical.quantity = Integer.parseInt(name[0].trim());
      for (String req : required) {
        chemical.required.put(
            req.trim().split(" ")[1].trim(), Integer.parseInt(req.trim().split(" ")[0].trim()));
      }
      return chemical;
    }

    public void getAllRequired(
        Map<String, Chemical> chemicals,
        String requiredChemical,
        Map<String, Integer> allRequired) {
      for (String reqName : required.keySet()) {
        int reqQuantity = this.required.get(reqName);
        if (!allRequired.containsKey(reqName)) {
          allRequired.put(reqName, 0);
        }
        allRequired.put(reqName, allRequired.get(reqName) + reqQuantity);
        if (!reqName.equals(requiredChemical) && chemicals.containsKey(reqName)) {
          chemicals.get(reqName).getAllRequired(chemicals, requiredChemical, allRequired);
        }
      }
    }

    public double getRequired(Map<String, Chemical> chemicals, String requiredChenical) {
      double count = 0;
      if (this.name.equals(requiredChenical)) {
        count = this.quantity;
      } else {
        for (String reqName : required.keySet()) {
          int reqQuantity = this.required.get(reqName);
          if (reqName.equals(requiredChenical)) {
            count += reqQuantity;
          } else {
            Chemical req = chemicals.get(reqName);
            // double minRequired = (reqQuantity / req.quantity) + 1;
            double required = req.getRequired(chemicals, requiredChenical) / req.quantity;
            System.out.println(required + " " + requiredChenical + " => 1 " + reqName);
            count += required;
          }
        }
      }
      return count;
    }

    @Override
    public String toString() {
      return this.quantity + " " + this.name;
    }
  }
}
