/**
 *  This class implements preorder, inorder, postorder tranverse the tree using recursion style
 */
public class tranverseTree {

    //preorder tranverse
    private static void preOrderTranverse(TreeNode root){

        if(root == null){
            return;
        }

        System.out.print(root.val+ " ");
        preOrderTranverse(root.left);
        preOrderTranverse(root.right);
    }

    //in-order tranverse
    private static void inOrderTranverse(TreeNode root){

        if(root == null){
            return;
        }

        inOrderTranverse(root.left);
        System.out.print(root.val+ " ");
        inOrderTranverse(root.right);
    }

    //post order tranverse
    private static void postOrderTranverse(TreeNode root){

        if(root == null){
            return;
        }

        postOrderTranverse(root.left);
        postOrderTranverse(root.right);
        System.out.print(root.val+ " ");
    }

    public static void main(String[] args) {

        /**contruct a tree
         *                5
         *              /   \
         *             3     8
         *            / \   / \
         *           2  4  7  10
         *          /     /   / \
         *         1     6   9  11
         * */
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        // print out tree structure
        TreeUtils.printTree(head);

        // recursive
        System.out.println("================================recursive=======================================");
        System.out.print("pre-order tranverse: ");
        preOrderTranverse(head);
        System.out.println();
        System.out.print("in-order tranverse: ");
        inOrderTranverse(head);
        System.out.println();
        System.out.print("pos-order tranverse: ");
        postOrderTranverse(head);
        System.out.println();

    }

}
