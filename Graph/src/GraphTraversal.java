import java.util.*;

/**
 *  This class implement the BFS and DFS for graph traversal
 */
public class GraphTraversal {

    public static List<List<String>> BFS(String node, Graph g){

        List<List<String>> result = new ArrayList<>();
        //adjacent list of graph
        HashMap<String, HashMap<String, Integer>> adj = g.getAdj();

        if(adj.containsKey(node)) {

            Queue<String> queue = new LinkedList<>();        //maintain bfs order
            HashSet<String> discoveredSet = new HashSet<>(); //discovered set tracking node explored

            //add first node to set and queue
            discoveredSet.add(node);
            queue.add(node);

            while (!queue.isEmpty()) {

                List<String> newLevel = new ArrayList<>();
                int levelSize = queue.size();

                for (int i = 0; i < levelSize; i++) {

                    String currentNode = queue.remove();
                    newLevel.add(currentNode);

                    HashMap<String, Integer> neighbor = adj.get(currentNode);

                    for (String neighborNode : adj.get(currentNode).keySet()) {
                        if (!discoveredSet.contains(neighborNode)) {
                            discoveredSet.add(neighborNode);
                            queue.add(neighborNode);
                        }
                    }

                }

                result.add(newLevel);
            }
        }

        return result;
    }

    public static void DFS(String node, Graph g){

        //adjacent list of graph
        HashMap<String, HashMap<String, Integer>> adj = g.getAdj();

        if(adj.containsKey(node)){

            Stack<String> stack = new Stack<>();
            HashSet<String> explored = new HashSet<>();

            stack.push(node);

            while(!stack.isEmpty()){

                String currentNode = stack.pop();

                //duplicated item in the dfs stack, need to check explored first
                if(!explored.contains(currentNode)){

                    explored.add(currentNode);
                    //do something
                    System.out.print(currentNode+" ");

                    //push all its neighbor to the stack
                    for(String neighborNode:adj.get(currentNode).keySet()){
                            stack.push(neighborNode);
                    }
                }
            }
        }
    }

    public static void DFSRecur(String node, Graph g){

        HashMap<String, HashMap<String, Integer>> adj = g.getAdj();

        HashSet<String> explored = new HashSet<>();

        if(adj.containsKey(node)){
            DFSRecurHelper(node, adj, explored);
        }
    }

    private static void DFSRecurHelper(String node, HashMap<String, HashMap<String, Integer>> adj,
                                       HashSet<String> explored){

        explored.add(node);

        System.out.print(node + " ");

        for(String v:adj.get(node).keySet()){
            if(!explored.contains(v)){
                DFSRecurHelper(v, adj, explored);
            }
        }

    }

    public static void main(String[] args) {

        System.out.println("================================================================= ");
        System.out.println("Undirected Graph");
        System.out.println("================================================================= ");


         // Graph: A - B
         //         \   \
         //          C - D
         //           \ / \
         //            G - E

        Graph graph = new Graph();

        graph.addEdge("A", "C", 1);
        graph.addEdge("A", "B", 1);
        graph.addEdge("B","D", 1);
        graph.addEdge("C", "D",1);
        graph.addEdge("C","G", 1);
        graph.addEdge("D", "E",1);
        graph.addEdge("D", "G",1);
        graph.addEdge("E", "G",1);

        System.out.println(graph);
        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);

        System.out.println("===============================");
        System.out.println("BFS");
        System.out.println("===============================");

        List<List<String>> bfsResult = BFS("A", graph);

        for(int i=0; i< bfsResult.size(); i++){
            System.out.print("Level-"+ i + ":");
            List<String> level = bfsResult.get(i);
            for(String node: level){
                System.out.print(node + " ");
            }
            System.out.println();
        }

        System.out.println("===============================");
        System.out.println("DFS");
        System.out.println("===============================");
        DFS("A", graph);
        System.out.println();

        System.out.println("===============================");
        System.out.println("DFS Recursion");
        System.out.println("===============================");
        DFSRecur("A", graph);
        System.out.println();


        System.out.println("================================================================= ");
        System.out.println("Directed Graph");
        System.out.println("================================================================= ");

        DiGraph digraph = new DiGraph();
//
//        digraph.addEdge("A", "B", 1);
//        digraph.addEdge("A", "C", 1);
//        digraph.addEdge("B","D", 1);
//        digraph.addEdge("C", "D",1);
//        digraph.addEdge("C","G", 1);
//        digraph.addEdge("D", "E",1);
//        digraph.addEdge("D", "G",1);
//        digraph.addEdge("E", "G",1);

        System.out.println(graph);
        System.out.println("Vertices: " + graph.numberOfNodes);
        System.out.println("Edges: " + graph.numberOfEdges);






    }
}
