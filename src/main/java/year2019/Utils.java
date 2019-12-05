package year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

  public static String readFile(String path) throws IOException {
    final StringBuilder builder = new StringBuilder();
    Stream<String> stream = Files.lines(Paths.get(path));
    stream.forEach(line -> builder.append(line));
    return builder.toString();
  }

  public static List<String> readLines(String path)throws IOException {
    Stream<String> stream = Files.lines(Paths.get(path));
    return stream.collect(Collectors.toList());
  }
}
