package AbstractDataTypes.Trees;

public interface Tree {

    void Insert(Item insertedItem);
    Item Search(Item searchItem);
    int GetComparisons();

}
