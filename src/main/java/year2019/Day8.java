package year2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day8 {

  public static void main(String[] args) throws IOException {
    Image image = new Image(Utils.readFile("./data/input8.txt"), 25, 6);
    // System.out.println(image);
    List<Integer> lessCorruptedLayer = image.getLessCorruptedLayer();
    Map<Integer, Integer> counts =
        lessCorruptedLayer.stream()
            .collect(Collectors.groupingBy(value -> value, Collectors.summingInt(a -> 1)));
    System.out.println(counts.get(1) * counts.get(2)); // 2684
    System.out.println(image.mergeLayers().toString().replace("0", " ").replace("1", "|"));
  }

  public static class Image {
    public Map<Integer, List<Integer>> data = new TreeMap<>();
    public int width, height;

    public Image(String image, int width, int height) throws IOException {
      this.width = width;
      this.height = height;

      List<Integer> layer = new ArrayList<>();
      for (int i = 0; i < image.length(); i++) {
        layer.add(Integer.parseInt(String.valueOf(image.charAt(i))));
        if (layer.size() == width * height) {
          data.put(data.size(), new ArrayList<>(layer));
          layer.clear();
        }
      }
    }

    public List<Integer> getLessCorruptedLayer() {
      int bestLayer = -1;
      int lastErrorCount = -1;

      for (int layer = 0; layer < data.size(); layer++) {
        int errorCount = 0;
        List<Integer> layerData = data.get(layer);
        for (int i : layerData) {
          if (i == 0) {
            errorCount++;
          }
        }
        if (lastErrorCount == -1 || errorCount < lastErrorCount) {
          bestLayer = layer;
          lastErrorCount = errorCount;
        }
      }
      return data.get(bestLayer);
    }

    public Image mergeLayers() throws IOException {
      StringBuilder builder = new StringBuilder();

      List<Integer> currentLayer = data.get(0);
      for (int currentLayerDataIndex = 0;
          currentLayerDataIndex < width * height;
          currentLayerDataIndex++) {
        int currentLayerData = currentLayer.get(currentLayerDataIndex);
        if (currentLayerData == 2) {
          for (int nextLayerIndex = 1; nextLayerIndex < data.size(); nextLayerIndex++) {
            List<Integer> nextLayer = data.get(nextLayerIndex);
            int nextLayerData = nextLayer.get(currentLayerDataIndex);
            if (nextLayerData != 2) {
              builder.append(nextLayerData);
              break;
            }
          }
        } else {
          builder.append(currentLayerData);
        }
      }

      return new Image(builder.toString(), width, height);
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      for (int layer = 0; layer < data.size(); layer++) {
        List<Integer> layerData = data.get(layer);
        for (int i = 0; i < layerData.size(); i++) {
          if (i > 0 && i % width == 0) {
            builder.append("\n");
          }
          builder.append(layerData.get(i));
        }
        builder.append("\n\n");
      }
      return builder.toString();
    }
  }
}
