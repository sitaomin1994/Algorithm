import java.awt.desktop.SystemHotkey;
import java.util.List;
import java.util.Stack;

/**
 *  This class implements the un recursive methods for pre-order, in-order, post-order tranverse the tree
 */
public class tranverseTreeUnRecur {

    // pre-order tranverse no recursive version
    // Basic idea - using stack
    // - when pop(touch) a node to stack, push its right child and left child in
    // - the order in the stack is [mid(this), left, right]
    private static void preOrderUnRecur(TreeNode root){
        if(root == null){
            return;
        }
        // using stack to store and simulate the recursion
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        //loop until all elements tranversed
        while(!stack.isEmpty()){
            TreeNode p = stack.pop();
            //do something here
            System.out.print(p.val + " ");

            if(p.right != null) {
                stack.push(p.right);
            }

            if(p.left != null){
                stack.push(p.left);
            }
        }

    }

    // in-order tranverse no recursive version
    // Basic idea - using stack
    // - push the left part of the tree to the stack
    // - when there is no left (null), we pop stack and do something to the node
    // - then when the node has right, we repeat above steps to its right subtree - p = p.right
    // - the order of pop is [left -> mid ->.right subtree ->(left mid right ..)....]
    // - do it until stack is empty and the node has no right child
    private static void inOrderUnRecur(TreeNode root){

        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;

        while(!stack.isEmpty() || p != null){
            if(p != null){
                stack.push(p);
                p = p.left;
            }else{
                p = stack.pop();
                // do something
                System.out.print(p.val +" ");
                p = p.right;
            }
        }

    }

    // post-order tranverse no recursive version
    // Using 2 stack
    // - stack1 for pre-order tranverse ([mid, right, left])
    // - stack2 to reverse the order to post order ([left right mid])
    private static void postOrderUnRecur(TreeNode root){
        if(root != null){
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            //pre-order to tranverse the tree - using stack2 to for reverse
            stack1.push(root);
            while(!stack1.isEmpty()){
                TreeNode p = stack1.pop();
                //preorder do sth - push to stack2
                stack2.push(p);
                //pre-order tranverse
                if(p.left != null){
                    stack1.push(p.left);
                }
                if(p.right != null){
                    stack1.push(p.right);
                }
            }
            //post order
            while(!stack2.isEmpty()){
                TreeNode q = stack2.pop();
                //do something
                System.out.print(q.val +" ");
            }
        }
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
        preOrderUnRecur(head);
        System.out.println();
        System.out.print("in-order tranverse: ");
        inOrderUnRecur(head);
        System.out.println();
        System.out.print("pos-order tranverse: ");
        postOrderUnRecur(head);
        System.out.println();

    }

}
