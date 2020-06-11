import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * implement the adjacency matrix representation of graph
 */
public class AdjMatrixGraph {

    private int numberOfEdges;
    private int numberOfNodes;
    private int[][] adjMatrix;
    private HashMap<String, Integer> nameMap;
    private String[] indexMap;
    final int MAX_SIZE = 50;

    public AdjMatrixGraph(){
        this.numberOfNodes = 0;
        this.numberOfEdges = 0;
        this.adjMatrix = new int[MAX_SIZE][MAX_SIZE];
        this.nameMap = new HashMap<>();
        this.indexMap = new String[MAX_SIZE];
    }

    public AdjMatrixGraph(int V){
        this.numberOfNodes = V;
        this.numberOfEdges = 0;
        this.adjMatrix = new int[numberOfNodes][numberOfNodes];
        this.nameMap = new HashMap<>();
        this.indexMap = new String[numberOfNodes];
    }

    /**
     * add a vertex to graph
     * @param node vertex name
     */
    public void addNode(String node){

        if(!this.nameMap.containsKey(node)) {
            if(this.numberOfNodes >= MAX_SIZE){
                System.out.println("Cannot add nodes reaches maximum size of graph.");
            }else {
                this.nameMap.put(node, this.numberOfNodes);
                this.indexMap[numberOfNodes] = node;
                this.numberOfNodes++;
            }
        }

    }

    /**
     * add an edge
     * @param src src node
     * @param dst dstination node
     * @param weight weight of edge
     */
    public void addEdge(String src, String dst, int weight){

        if(!this.nameMap.containsKey(src)){
            if(numberOfNodes >= MAX_SIZE) {
                System.out.println("Cannot add edges reaches maximum size of graph.");
            }else {
                this.addNode(src);
            }
        }

        if(!this.nameMap.containsKey(dst)){
            if(numberOfNodes >= MAX_SIZE) {
                System.out.println("Cannot add edges reaches maximum size of graph.");
            }else {
                this.addNode(dst);
            }
        }

        int srcIndex = nameMap.get(src);
        int dstIndex = nameMap.get(dst);
        adjMatrix[srcIndex][dstIndex] = weight;
        numberOfEdges++;

    }

    /**
     * delete a node
     * @param node node
     */
    public void deleteNode(String node){
        if(nameMap.containsKey(node)) {
            int nodeIndex = nameMap.get(node);

            //exchange row and col of this node with last node
            for(int i=0;i<numberOfNodes;i++){
                adjMatrix[nodeIndex][i] = adjMatrix[numberOfNodes-1][i];
                adjMatrix[i][nodeIndex] = adjMatrix[i][numberOfNodes-1];
            }
            //remove nodes in name map and index map -> exchange position with last node
            String lastNodeName = indexMap[numberOfNodes-1];
            nameMap.put(lastNodeName, nodeIndex);
            indexMap[nodeIndex] = lastNodeName;
            nameMap.remove(node);

            numberOfNodes--;
        }
    }

    /**
     * delete a edge
     * @param src src node
     * @param dst dstination node
     */
    public void deleteEdge(String src, String dst){
        if(nameMap.containsKey(src) && nameMap.containsKey(dst)){
            int srcIndex = nameMap.get(src);
            int dstIndex = nameMap.get(dst);
            adjMatrix[srcIndex][dstIndex] = 0;
            numberOfEdges--;
        }
    }

    public int getNumberOfNodes(){
        return numberOfNodes;
    }

    public int getNumberOfEdges(){
        return numberOfEdges;
    }

    public int[][] getAdjMatrix(){
        return this.adjMatrix;
    }

    public HashMap<String, Integer> getNameMap(){
        return nameMap;
    }

    public String[] getIndexMap(){
        return indexMap;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix:\n");
        sb.append("  ");
        for(int i=0; i< numberOfNodes; i++){
            sb.append(indexMap[i]).append(" ");
        }
        sb.append("\n");
        for(int i=0; i < numberOfNodes; i++) {
            sb.append(indexMap[i]).append(" ");
            for (int j = 0; j < numberOfNodes; j++) {
                sb.append(adjMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String [ ] args){
        AdjMatrixGraph graph = new AdjMatrixGraph();

        System.out.println("======================Constructing graph======================= ");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("A","D",3);
        graph.addEdge("B","D",4);
        graph.addEdge("B","E",6);
        graph.addEdge("B","G",3);
        graph.addEdge("C","B",2);
        graph.addEdge("C", "D",4);
        graph.addEdge("C","G",5);
        graph.addEdge("C","E",3);
        graph.addEdge("D", "E",8);
        graph.addEdge("D", "G",9);
        graph.addEdge("E", "G",7);
        graph.addEdge("G","A",3);

        graph.addNode("H");

        System.out.println(graph);
        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);

        System.out.println("=======================Test delete======================== ");
        graph.deleteEdge("A","B");
        graph.deleteEdge("A", "D");
        graph.deleteNode("H");

        System.out.println(graph);

        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);
    }

}
