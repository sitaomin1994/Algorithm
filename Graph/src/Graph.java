import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *  The undirected graph implemented by adjacent list
 */

public class Graph {

    /**
     *  adj is adjacent list of graph: node -> (dest node, weight)
     */
    private HashMap<String, HashMap<String, Integer>> adj;
    int numberOfNodes;
    int numberOfEdges;

    public Graph(){
        this.adj = new HashMap<>();
        this.numberOfEdges = 0;
        this.numberOfNodes = 0;
    }

    /**
     * add a vertex to graph
     * @param vertex new vertex
     */
    public void addVertex(String vertex){
        if(!adj.containsKey(vertex)){
            adj.put(vertex, new HashMap<>());
            this.numberOfNodes++;
        }
    }

    /**
     * add an edge to graph
     * @param src source node
     * @param dst destination node
     * @param weight weight of edge
     */
    public void addEdge(String src, String dst, int weight){
        if(!adj.containsKey(src)) addVertex(src);
        if(!adj.containsKey(dst)) addVertex(dst);

        HashMap<String, Integer> srcNeighbors = adj.get(src);
        srcNeighbors.put(dst, weight);

        HashMap<String, Integer> dstNeighbors = adj.get(dst);
        dstNeighbors.put(src, weight);

        this.numberOfEdges++;
    }

    /**
     * add an edge to graph
     * @param src source node
     * @param dst destination node
     */
    public void addEdge(String src, String dst){
        if(!adj.containsKey(src)) addVertex(src);
        if(!adj.containsKey(dst)) addVertex(dst);

        HashMap<String, Integer> srcNeighbors = adj.get(src);
        srcNeighbors.put(dst, 1);

        HashMap<String, Integer> dstNeighbors = adj.get(dst);
        dstNeighbors.put(src, 1);

        this.numberOfEdges++;
    }

    /**
     * get the number of vertices in the graph
     * @return number of vertices
     */
    public int getNumberOfNodes(){
        return this.numberOfNodes;
    }

    /**
     * get the number of edges in the graph
     * @return number of edges
     */
    public int getNumberOfEdges(){
        return this.numberOfEdges;
    }

    /**
     * get edges connected to a node
     * @param node node
     * @return hashmap of nodes and weight connected with a node
     */
    public HashMap<String, Integer> getEdges(String node){
        if(!adj.containsKey(node))
            throw new RuntimeException("NO SUCH NODES IN THE GRAPH!");
        return adj.get(node);
    }

    public HashMap<String, HashMap<String, Integer>> getAdj(){
        return adj;
    }

    /**
     * check if graph contains an edges
     * @param src src node
     * @param dst dst node
     * @return true or false
     */
    public boolean hasEdge(String src, String dst){
        if(adj.containsKey(src)){
            return adj.get(src).containsKey(dst);
        }

        return false;
    }

    /**
     * check if graph contains a vertex
     * @param node vertex name
     * @return true or false
     */
    public boolean hasVertex(String node){
        return adj.containsKey(node);
    }

    /**
     * delete an edge
     * @param src src vertex
     * @param dst dst vertex
     */
    public void deleteEdge(String src, String dst){

       if(hasEdge(src, dst)) {
           adj.get(src).remove(dst);
           adj.get(dst).remove(src);
           this.numberOfEdges--;
       }else{
           System.out.println("cannot find edge:" + src +" - " + dst);
       }
    }

    /**
     * delete an vertex
     * @param node vertex name
     */
    public void deleteVertex(String node){
        if(hasVertex(node)){
            for(String dst: adj.get(node).keySet()){
                adj.get(dst).remove(node);
                this.numberOfEdges--;
            }
            adj.remove(node);
            this.numberOfNodes--;
        }else{
            System.out.println("cannot find node: " + node);
        }
    }



    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        HashSet<String> edges = new HashSet<>();

        for(String node: adj.keySet()){
            sb.append(node).append(": ");
            HashMap<String, Integer> nodeNeighbor = adj.get(node);
            for(String dst: nodeNeighbor.keySet()){
                String edge = "( " + node + " - " + dst + " w: " + nodeNeighbor.get(dst) + ")";
                String revertedEdge = "( " + dst + " - " + node + " w: " + nodeNeighbor.get(dst) + ")";
                if(!(edges.contains(edge) || edges.contains(revertedEdge))) {
                    edges.add(edge);
                }
                sb.append(edge);
            }
            sb.append("\n");
        }

        sb.append("Edges:");
        for(String edge: edges){
            sb.append(edge).append(" ");
        }

        return (sb.toString());
    }

    public static void main(String [ ] args){
        Graph graph = new Graph();

        System.out.println("======================Constructing graph======================= ");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "D",1);
        graph.addEdge("D", "E",1);
        graph.addEdge("D", "G",1);
        graph.addEdge("E", "G",1);
        graph.addVertex("H");

        System.out.println(graph);
        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);

        System.out.println("=======================Test delete======================== ");
        graph.deleteEdge("A","B");
        graph.deleteEdge("A", "D");
        graph.deleteVertex("H");

        System.out.println(graph);

        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);
    }
}
