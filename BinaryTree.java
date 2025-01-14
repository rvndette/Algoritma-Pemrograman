import java.util.Scanner;

class Node {
    int key;
    Node left, right;
    public Node(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }
}
class BinaryTreeClass {
    private Node root;

    public BinaryTreeClass()
    {
        root = null;
    }
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insertRecursive(root, key);
        }
    }
    private void insertRecursive(Node current, int key) {
        if (key < current.key) {
            if (current.left == null) {
                current.left = new Node(key);
            } else {
                insertRecursive(current.left, key);
            }
        } else if (key > current.key) {
            if (current.right == null) {
                current.right = new Node(key);
            } else {
                insertRecursive(current.right, key);
            }
        }
    }

    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(Node current, int key) {
        if (current == null) {
            return false;
        }
        if (current.key == key) {
            return true;
        } else if (key < current.key) {
            return searchRecursive(current.left, key);
        } else {
            return searchRecursive(current.right, key);
        }
    }

    public void inorderTraversal() {
        inorderRecursive(root);
    }

    private void inorderRecursive(Node current) {
        if (current != null) {
            inorderRecursive(current.left);
            System.out.print(current.key + " ");
            inorderRecursive(current.right);
        }
    }
}
// Main class
public class BinaryTree {
    public static void main(String[] args) {
        BinaryTreeClass tree = new BinaryTreeClass();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBinary Tree Operations:");
            System.out.println("1. Insert a value");
            System.out.println("2. Search for a value");
            System.out.println("3. Display inorder traversal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the value to insert: ");
                    int insertValue = scanner.nextInt();
                    tree.insert(insertValue);
                    System.out.println("Value " + insertValue + " inserted.");
                    break;

                case 2:
                    System.out.print("Enter the value to search for: ");
                    int searchValue = scanner.nextInt();
                    if (tree.search(searchValue)) {
                        System.out.println("Value " + searchValue + " found in the tree.");
                    } else {
                        System.out.println("Value " + searchValue + " not found in the tree.");
                    }
                    break;

                case 3:
                    System.out.print("Inorder Traversal: ");
                    tree.inorderTraversal();
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
