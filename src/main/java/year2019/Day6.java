package year2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day6 {

  public static void main(String[] args) throws IOException {
    List<String> orbits = Utils.readLines("./data/input6.txt");
    Graph graph = Graph.getInstance(orbits);
    System.out.println(graph.getEdgesCount());
    System.out.println(graph.findPath("YOU", "SAN"));
  }

  public static class Graph{
    public Map<String, Node> nodes = new TreeMap<>();
    public Node getNode(String name){
      if (!nodes.containsKey(name)){
        nodes.put(name, new Node(name));
      }
      return nodes.get(name);
    }
    public int getEdgesCount(){
      int count=0;
      for(String name: nodes.keySet()){
        count += nodes.get(name).getEdgesCount();
      }
      return count;
    }

    public int findPath(String source, String target){
      Node sourceNode = nodes.get(source);
      Node targetNode = nodes.get(target);
      List<Node> exploredNodes = new ArrayList<>();
      List<List<Node>> paths = new ArrayList<>();
      sourceNode.findPath(targetNode, exploredNodes, paths);
      var bestPath = paths.stream().sorted((left, right)-> left.size() < right.size() ? -1: 0).findFirst();
      return bestPath.map(path -> path.size() - 3).orElse(-1);
    }

    public static Graph getInstance(List<String> orbits){
      Graph graph = new Graph();

      for(String orbit: orbits) {
        String spaceObject1 = orbit.split("\\)")[0];
        String spaceObject2 = orbit.split("\\)")[1];
        Node spaceObject1Node = graph.getNode(spaceObject1);
        Node spaceObject2Node = graph.getNode(spaceObject2);
        spaceObject1Node.children.add(spaceObject2Node);
        spaceObject2Node.parents.add(spaceObject1Node);
      }

      return graph;
    }
  }

  public static class Node{
    public String name;
    public List<Node> children = new ArrayList<>();
    public List<Node> parents = new ArrayList<>();
    public Node(String name){
      this.name=name;
    }
    public int getEdgesCount(){
      int count = 0;
      for(Node children: children){
        count += children.getEdgesCount();
      }
      return children.size() + count;
    }

    public void findPath(Node target, List<Node> exploredNodes, List<List<Node>> paths ){
      if (!exploredNodes.contains(this)) {
        exploredNodes.add(this);
        if (this.name.equals(target.name)) {
          paths.add(new ArrayList<>(exploredNodes));
        } else {
          for (Node child : children) {
            child.findPath(target, exploredNodes, paths);
          }
          for (Node parent : parents) {
            parent.findPath(target, exploredNodes,  paths);
          }
        }
        exploredNodes.remove(this);
      }
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

}
