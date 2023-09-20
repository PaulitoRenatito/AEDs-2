package AbstractDataTypes.Trees;

import static AbstractDataTypes.Trees.Item.ComparisonResult.*;

public class BinaryTree implements Tree {

    private static class Node {
        Item item;
        Node leftChild, rightChild;

        public Node(Item item) {
            this.item = item;
            leftChild = null;
            rightChild = null;
        }
    }

    private Node mainRoot;
    private int comparisons = 0;

    public BinaryTree() {
        mainRoot = null;
    }

    public Item Search(Item searchItem)
    {
        return Search(searchItem, mainRoot);
    }

    private Item Search(Item searchItem, Node currentNode) {

        comparisons++;

        if (currentNode == null) {
            System.out.println("Error: Value could not be found");
            return null;
        }
        else if (searchItem.CompareWith(currentNode.item) == Lesser) {
            return Search(searchItem, currentNode.leftChild);
        }
        else if (searchItem.CompareWith(currentNode.item) == Greater) {
            return Search(searchItem, currentNode.rightChild);
        }
        else return currentNode.item;
    }

    public void Insert(Item insertedItem)
    {
        mainRoot = Insert(insertedItem, mainRoot);
    }

    private Node Insert(Item insertedItem, Node currentNode)
    {
        if (currentNode == null) {
            currentNode = new Node(insertedItem);
        }
        else if (insertedItem.CompareWith(currentNode.item) == Lesser) {
            currentNode.leftChild = Insert(insertedItem, currentNode.leftChild);
        }
        else if (insertedItem.CompareWith(currentNode.item) == Greater) {
            currentNode.rightChild = Insert(insertedItem, currentNode.rightChild);
        }
        else System.out.println("Error: The entry already exists in the AbstractDataTypes.Trees.Tree");

        return currentNode;
    }

    public void Remove(Item removedItem) {
        mainRoot = Remove(removedItem, mainRoot);
    }

    private Node Remove(Item removedItem, Node currentNode) {
        if (currentNode == null)
            System.out.println("Error: Value can't be found");
        else if (removedItem.CompareWith(currentNode.item) == Lesser) {
            currentNode.leftChild = Remove(removedItem, currentNode.leftChild);
        }
        else if (removedItem.CompareWith(currentNode.item) == Lesser) {
            currentNode.rightChild = Remove(removedItem, currentNode.rightChild);
        }
        else {
            if (currentNode.rightChild == null)
                currentNode = currentNode.leftChild;
            else if (currentNode.leftChild == null)
                currentNode = currentNode.rightChild;
            else
                currentNode.leftChild = RemoveRootNode(currentNode, currentNode.leftChild);
        }

        return currentNode;
    }

    private Node RemoveRootNode(Node rootNode, Node rootNodeLeftChild) {

        if (rootNodeLeftChild.rightChild != null)
            rootNodeLeftChild.rightChild = RemoveRootNode(rootNode, rootNodeLeftChild.rightChild);
        else {
            rootNode.item = rootNodeLeftChild.item;
            rootNodeLeftChild = rootNodeLeftChild.leftChild;
        }

        return rootNodeLeftChild;
    }

    public int GetComparisons() {
        int totalComparisons = comparisons;
        comparisons = 0;
        return totalComparisons;
    }
}
