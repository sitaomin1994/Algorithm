import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements the topology sort of DAG used to detect whether a directed graph has a cycle or not
 */


public class TopologySort {

    public static boolean topologySort(DiGraph g){

        HashMap<String, HashMap<String, Integer>> adj = g.getAdj();
        HashMap<String, HashMap<String, Integer>> reversedAdj = g.getReverse_adj();

        //Using hashmap to track in-degree of a node
        HashMap<String, Integer> inDegreeMap = new HashMap<>();
        Queue<String> noInEdgeNodes = new LinkedList<>();
        int counter = 0; //counter to track all explored nodes

        //push all no incoming edges node to zero in-degree queue
        for(String node: reversedAdj.keySet()){
            int inDegree = reversedAdj.get(node).size();
            inDegreeMap.put(node, inDegree);  //initial inDegreeMap
            if(inDegree == 0){                //push all zero in-degree node to queue
                noInEdgeNodes.add(node);
            }
        }

        //loop until queue is empty
        while(!noInEdgeNodes.isEmpty()){
            //pop out a node
            String currentNode = noInEdgeNodes.remove();
            //do something
            System.out.print(counter + ":" + currentNode + " ");
            counter++;

            //delete all its out edges
            for(String dst:adj.get(currentNode).keySet()){
                //decrease in degree by one
                inDegreeMap.put(dst, inDegreeMap.get(dst)-1);
                //add to queue if in-degree is 0
                if(inDegreeMap.get(dst) == 0) {
                    noInEdgeNodes.add(dst);
                }
            }
        }

        //check whether all nodes are explored or it has cycle
        return counter == adj.keySet().size();
    }

    public static void main(String [ ] args){

        DiGraph graph = new DiGraph();

        System.out.println("========================================================");
        System.out.println("Constructing Graph");
        System.out.println("========================================================");

         //     v1  -> v2
         //    /  \  /   \
         //  v3 <- v4 <- v5
         //    \  /  \  /
         //    v6 <- v7

        graph.addEdge("v1","v2");
        graph.addEdge("v1","v3");
        graph.addEdge("v1","v4");

        graph.addEdge("v2","v4");
        graph.addEdge("v2","v5");

        graph.addEdge("v3","v6");

        graph.addEdge("v4","v3");
        graph.addEdge("v4","v6");
        graph.addEdge("v4","v7");


        graph.addEdge("v5","v4");
        graph.addEdge("v5","v7");

        graph.addEdge("v7","v6");

        System.out.println(graph.toString());
        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);

        System.out.println("========================================================");
        System.out.println("Topological Order");
        System.out.println("========================================================");

        boolean hasNoCycle = topologySort(graph);
        System.out.println();
        System.out.println("Has Cycle: " + !hasNoCycle);


        System.out.println("========================================================");
        System.out.println("Cycle detection");
        System.out.println("========================================================");

        graph.deleteEdge("v5","v7");
        graph.addEdge("v7","v5");

        hasNoCycle = topologySort(graph);
        System.out.println();
        System.out.println("Has Cycle: " + !hasNoCycle);


        Integer[] a = new Integer[10];

        ArrayList<Integer> b = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            b.add(i);
        }

        a = b.toArray(new Integer[10]);


    }
}
