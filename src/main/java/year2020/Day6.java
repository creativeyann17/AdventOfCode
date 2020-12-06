package year2020;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {

  public static void main(String[] args) throws IOException {
    List<String> lines = Utils.readLines("./data/2020/input6.txt");
    List<Group> groups = Group.getGroups(lines);
    System.out.println(
        groups.stream().map(g -> g.answers.size()).reduce(0, (l, r) -> l + r).intValue());
    System.out.println(
        groups.stream()
            .map(
                g -> {
                  int yes = 0;
                  for (String key : g.answers.keySet()) {
                    int count = g.answers.get(key);
                    if (count == g.groupCount) {
                      yes++;
                    }
                  }
                  return yes;
                })
            .reduce(0, (l, r) -> l + r)
            .intValue());
  }

  @ToString
  @EqualsAndHashCode
  public static class Group {
    public Map<String, Integer> answers;
    public int groupCount = 0;

    public Group(List<String> lines) {
      this.answers = new HashMap<>();
      this.groupCount = lines.size();
      for (String line : lines) {
        for (char c : line.toCharArray()) {
          String question = String.valueOf(c);
          int count = answers.getOrDefault(question, 0);
          answers.put(question, count + 1);
        }
      }
    }

    public static List<Group> getGroups(List<String> lines) {
      List<Group> groups = new ArrayList<>();
      List<String> temp = new ArrayList<>();
      for (String line : lines) {
        if (StringUtils.isBlank(line) && temp.size() > 0) {
          groups.add(new Group(temp));
          temp.clear();
        } else {
          temp.add(line);
        }
      }
      if (temp.size() > 0) {
        groups.add(new Group(temp));
        temp.clear();
      }
      return groups;
    }
  }
}
