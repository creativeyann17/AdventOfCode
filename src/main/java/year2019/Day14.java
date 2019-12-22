package year2019;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day14 {
  public static void main(String[] args) throws IOException {
    List<String> lines = Utils.readLines("./data/input14_0.txt");
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
    int oreCount = 0;
    for (String reqName : allRequired.keySet()) {
      if (reqName.equals("ORE")) {
        oreCount += allRequired.get(reqName);
      } else {
        long minCount =
            Math.round((allRequired.get(reqName) / chemicals.get(reqName).quantity) + 0.5);
        oreCount += minCount * chemicals.get(reqName).getRequired(chemicals, "ORE");
      }
    }
    System.out.println(oreCount);
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

    public int getRequired(Map<String, Chemical> chemicals, String requiredChemical) {
      int count = 0;
      for (String reqName : required.keySet()) {
        if (reqName.equals(requiredChemical)) {
          count += quantity;
        } else {
          Chemical req = chemicals.get(reqName);
          count += req.getRequired(chemicals, requiredChemical);
        }
      }
      return count;
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

    @Override
    public String toString() {
      return this.quantity + " " + this.name;
    }
  }
}
