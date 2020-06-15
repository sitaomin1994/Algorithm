import java.util.HashMap;
import java.util.HashSet;

/**
 *  Adjacent List representation of Directed Graph
 */
public class DiGraph {

    /**
     *  adj is adjacent list of graph: node -> (dest node, weight)
     */
    private HashMap<String, HashMap<String, Integer>> adj;
    private HashMap<String, HashMap<String, Integer>> reverse_adj;
    int numberOfNodes;
    int numberOfEdges;

    public DiGraph(){
        this.adj = new HashMap<>();
        this.reverse_adj = new HashMap<>();
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

        if(!reverse_adj.containsKey(vertex)){
            reverse_adj.put(vertex, new HashMap<>());
        }
    }

    /**
     * add an edge to graph
     * @param src source node
     * @param dst destination node
     * @param weight weight of edge
     */
    public void addEdge(String src, String dst, int weight){
        //update adjacent list
        if(!adj.containsKey(src)) addVertex(src);
        if(!adj.containsKey(dst)) addVertex(dst);

        HashMap<String, Integer> srcNeighbors = adj.get(src);
        srcNeighbors.put(dst, weight);

        this.numberOfEdges++;

        //update reverse adjacent list
        if(!reverse_adj.containsKey(src)) addVertex(src);
        if(!reverse_adj.containsKey(dst)) addVertex(dst);

        HashMap<String, Integer> dstNeighbors = reverse_adj.get(dst);
        dstNeighbors.put(src, weight);
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

        this.numberOfEdges++;

        //update reverse adjacent list
        if(!reverse_adj.containsKey(src)) addVertex(src);
        if(!reverse_adj.containsKey(dst)) addVertex(dst);

        HashMap<String, Integer> dstNeighbors = reverse_adj.get(dst);
        dstNeighbors.put(src, 1);
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

    public HashMap<String, HashMap<String, Integer>> getAdj(){
        return this.adj;
    }

    public HashMap<String, HashMap<String, Integer>> getReverse_adj(){
        return this.reverse_adj;
    }

    /**
     * get edges connected to a node
     * @param node node
     * @return hashmap of nodes and weight connected with a node
     */
    public HashMap<String, Integer> getSrcEdges(String node){
        if(!adj.containsKey(node))
            throw new RuntimeException("NO SUCH NODES IN THE GRAPH!");
        return adj.get(node);
    }

    /**
     * get edges connected to a node
     * @param node node
     * @return hashmap of nodes and weight connected with a node
     */
    public HashMap<String, Integer> getDstEdges(String node){
        if(!reverse_adj.containsKey(node))
            throw new RuntimeException("NO SUCH NODES IN THE GRAPH!");
        return reverse_adj.get(node);
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
            reverse_adj.get(dst).remove(src);
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
            //delete all edges => node -> dst
            for(String dst: adj.get(node).keySet()){
                reverse_adj.get(dst).remove(node);
                this.numberOfEdges--;
            }
            adj.remove(node);

            //delete all edges => src -> node
            for(String src: reverse_adj.get(node).keySet()){
                adj.get(src).remove(node);
                this.numberOfEdges--;
            }

            reverse_adj.remove(node);
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
                String edge = "( " + node + " -> " + dst + " w: " + nodeNeighbor.get(dst) + ")";
                edges.add(edge);
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
        DiGraph graph = new DiGraph();

        System.out.println("======================Constructing graph======================= ");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("B", "E", 1);
        graph.addEdge("C", "D", 1);
        graph.addEdge("C", "G", 1);
        graph.addEdge("D", "E", 1);
        graph.addEdge("D", "G", 1);
        graph.addEdge("E", "G", 1);

        System.out.println(graph);
        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);

        System.out.println("=======================Test delete======================== ");
        graph.deleteEdge("A","B");
        graph.addEdge("A", "B", 1);
        graph.deleteEdge("A", "D");
        graph.deleteVertex("D");

        System.out.println(graph);

        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);
    }
}