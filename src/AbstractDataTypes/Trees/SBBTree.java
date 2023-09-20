package AbstractDataTypes.Trees;

import static AbstractDataTypes.Trees.Item.ComparisonResult.*;

public class SBBTree implements Tree {

    private enum ChildType {
        Horizontal,
        Vertical
    }

    private static class Node {
        Item item;
        Node leftChild, rightChild;
        ChildType leftChildType, rightChildType;

        public Node(Item item) {
            this.item = item;
            leftChild = null;
            rightChild = null;
            leftChildType = ChildType.Vertical;
            rightChildType = ChildType.Vertical;
        }
    }

    private Node mainRoot;
    private boolean haveSBBProperty;
    private int comparisons = 0;

    public SBBTree() {
        mainRoot = null;
        haveSBBProperty = true;
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
        mainRoot = Insert(insertedItem, mainRoot, null, true);
    }

    private Node Insert(Item insertedItem, Node currentNode, Node currentNodeFather, boolean haveLeftChild)
    {
        if (currentNode == null) {

            currentNode = new Node(insertedItem);

            if (currentNodeFather != null) {
                if (haveLeftChild) {
                    currentNodeFather.leftChildType = ChildType.Horizontal;
                }
                else {
                    currentNodeFather.rightChildType = ChildType.Horizontal;
                }
            }

            haveSBBProperty = false;
        }
        else if (insertedItem.CompareWith(currentNode.item) == Lesser) {
            currentNode.leftChild = Insert(insertedItem, currentNode.leftChild, currentNode, true);
            if (!haveSBBProperty) {
                if (currentNode.leftChildType == ChildType.Horizontal) {
                    if (currentNode.leftChild.leftChildType == ChildType.Horizontal) {

                        currentNode = LeftLeftTransformation(currentNode);

                        if (currentNodeFather != null) {
                            if (haveLeftChild) {
                                currentNodeFather.leftChildType = ChildType.Horizontal;
                            }
                            else {
                                currentNodeFather.rightChildType = ChildType.Horizontal;
                            }
                        }
                    }
                    else if (currentNode.leftChild.rightChildType == ChildType.Horizontal) {

                        currentNode = LeftRightTransformation(currentNode);

                        if (currentNodeFather != null) {
                            if (haveLeftChild) {
                                currentNodeFather.leftChildType = ChildType.Horizontal;
                            }
                            else {
                                currentNodeFather.rightChildType = ChildType.Horizontal;
                            }
                        }
                    }
                }
                else {
                    haveSBBProperty = true;
                }
            }
        }
        else if (insertedItem.CompareWith(currentNode.item) == Greater) {
            currentNode.rightChild = Insert(insertedItem, currentNode.rightChild, currentNode, false);

            if (!haveSBBProperty) {
                if (currentNode.rightChildType == ChildType.Horizontal) {
                    if (currentNode.rightChild.rightChildType == ChildType.Horizontal) {

                        currentNode = RightRightTransformation(currentNode);

                        if (currentNodeFather != null) {
                            if (haveLeftChild) {
                                currentNodeFather.leftChildType = ChildType.Horizontal;
                            }
                            else {
                                currentNodeFather.rightChildType = ChildType.Horizontal;
                            }
                        }
                    }
                    else if (currentNode.rightChild.leftChildType == ChildType.Horizontal) {

                        currentNode = RightLeftTransformation(currentNode);

                        if (currentNodeFather != null) {
                            if (haveLeftChild) {
                                currentNodeFather.leftChildType = ChildType.Horizontal;
                            }
                            else {
                                currentNodeFather.rightChildType = ChildType.Horizontal;
                            }
                        }
                    }
                }
                else {
                    haveSBBProperty = true;
                }
            }
        }
        else {
            System.out.println("Error: The entry already exists in the AbstractDataTypes.Trees.Tree");
            haveSBBProperty = true;
        }

        return currentNode;
    }

    private Node LeftLeftTransformation(Node currentNode) {
        Node newRoot = currentNode.leftChild;
        currentNode.leftChild = newRoot.rightChild;
        newRoot.rightChild = currentNode;

        newRoot.leftChildType = ChildType.Vertical;
        currentNode.leftChildType = ChildType.Vertical;
        currentNode = newRoot;

        return currentNode;
    }

    private Node RightRightTransformation(Node currentNode) {
        Node newRoot = currentNode.rightChild;
        currentNode.rightChild = newRoot.leftChild;
        newRoot.leftChild = currentNode;

        newRoot.rightChildType = ChildType.Vertical;
        currentNode.rightChildType = ChildType.Vertical;
        currentNode = newRoot;

        return currentNode;
    }

    private Node LeftRightTransformation(Node currentNode) {

        Node newRootLeftChild = currentNode.leftChild;
        Node newRoot = newRootLeftChild.rightChild;
        newRootLeftChild.rightChildType = ChildType.Vertical;
        currentNode.leftChildType = ChildType.Vertical;
        newRootLeftChild.rightChild = newRoot.leftChild;
        newRoot.leftChild = newRootLeftChild;
        currentNode.leftChild = newRoot.rightChild;
        newRoot.rightChild = currentNode;
        currentNode = newRoot;

        return currentNode;
    }

    private Node RightLeftTransformation(Node currentNode) {

        Node newRootRightChild = currentNode.rightChild;
        Node newRoot = newRootRightChild.leftChild;
        newRootRightChild.leftChildType = ChildType.Vertical;
        currentNode.rightChildType = ChildType.Vertical;
        newRootRightChild.leftChild = newRoot.rightChild;
        newRoot.rightChild = newRootRightChild;
        currentNode.rightChild = newRoot.leftChild;
        newRoot.leftChild = currentNode;
        currentNode = newRoot;

        return currentNode;
    }

    public int GetComparisons() {
        int totalComparisons = comparisons;
        comparisons = 0;
        return totalComparisons;
    }
}
