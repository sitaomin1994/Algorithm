// Implementation of Disjoint Sets class
//
// ******************PUBLIC OPERATIONS*********************
// void union(item1, item2) --> Merge two sets
// int find(x) --> Return set containing x

import java.util.*;

public class DisJointSet<AnyType> {

    public HashMap<AnyType, AnyType> fatherMap;   //store the E->Parent ralationship
    public HashMap<AnyType, Integer> sizeMap;     //store the size or height of each disjoint sets

    /**
     * Constructor of disjoint sets, needs to construct from a list of elements
     * @param elements - list of elements of generic AnyType
     */
    public DisJointSet(List<AnyType> elements) {

        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();

        //initialize to make every element -> itself, and every element size -> 0
        for(AnyType item: elements) {

            fatherMap.put(item, item);
            sizeMap.put(item, 1);
        }
    }

    /**
     *  Find a set contains element x, return the root of this set, using path compression
     * @param x - a element
     * @return - a root of the set contains x
     */
    public AnyType find(AnyType x){

        AnyType father = fatherMap.get(x);

        //base case - node -> itself => the root
        if(father == x){
            return x;
        }

        //find(its father) recursively return the root of the set
        AnyType root = find(father);

        //make the parent of x to the root - path compression
        fatherMap.put(x, root);

        return root;
    }

    /**
     * check if two elements is in the same set or not
     * @param item1 - a element
     * @param item2 - a element
     * @return - true or false denotes the result
     */
    public boolean isInSameSet(AnyType item1, AnyType item2){

        return find(item1) == find(item2);
    }

    /**
     * Union the sets of two elements to one sets
     * @param item1 - a element
     * @param item2 - a element
     */
    public void union(AnyType item1, AnyType item2){

        if(item1 == null || item2 == null){
            return;
        }

        AnyType root1 = find(item1);
        AnyType root2 = find(item2);

        if(root1 != root2){
            int size1 = sizeMap.get(root1);
            int size2 = sizeMap.get(root2);

            if(size1 <=  size2){
                fatherMap.put(root1, root2);
                sizeMap.put(root2, size1 + size2);
            }else{
                fatherMap.put(root2, root1);
                sizeMap.put(root1, size1 + size2);
            }
        }
    }

    /**
     *  print the disjoint set
     */
    public void printDisJointSet(){

        //Hashmap used to store the result
        HashMap<AnyType, ArrayList<AnyType>> result = new HashMap<>();

        //traverse the disjoint set
        Set<AnyType> elements = fatherMap.keySet();
        for(AnyType item : elements) {

            AnyType root = find(item);
            if (!result.containsKey(root)) {
                ArrayList<AnyType> set = new ArrayList<>();
                set.add(item);
                result.put(root, set);
            } else {
                ArrayList<AnyType> set = result.get(root);
                set.add(item);
                result.put(root, set);
            }

        }

        //printout result
        System.out.println("==========Disjoint Set=============");
        for(AnyType key : result.keySet()){
            System.out.println( key + " : " + result.get(key).toString() + " size: " + sizeMap.get(key));
        }
    }

    // Test main; all finds on same output line should be identical
    public static void main( String [ ] args )
    {
        final int NumElements = 15;
        final int RANGE = 50;
        final int UnionTimes = 7;
        final int FindTimes = 5;

        Random generator = new Random(0);
        ArrayList<Integer> elements = new ArrayList<>();

        for(int i=0; i< NumElements; i++){
            elements.add(generator.nextInt(RANGE));
        }

        System.out.println("Elements: " + elements.toString());

        DisJointSet<Integer> ds = new DisJointSet<>(elements);
        int set1, set2;

        // Test union
        System.out.println("======================================================");
        System.out.println("Test Union");
        System.out.println("======================================================");

        for( int k = 1; k < UnionTimes; k *= 2 )
        {
            for(int j = 0; j + k < NumElements; j += 2 * k )
            {
                int item1 = elements.get(j);
                int item2 = elements.get(j + k);

                System.out.println("===================================");
                System.out.println("Union " + item1 + " " + item2 + ":");
                ds.union(item1, item2);
                //print result of disjoint set
                ds.printDisJointSet();
            }
        }

        //Test find
        System.out.println("======================================================");
        System.out.println("Test Find");
        System.out.println("======================================================");
        ds.printDisJointSet();
        for( int i = 0; i < FindTimes; i++ )
        {
            int index = generator.nextInt(elements.size());
            int element = elements.get(index);
            int root = ds.find(element);
            System.out.println("Find element " + element + ": the root is " + root +".");

        }
        System.out.println( );
    }

}
