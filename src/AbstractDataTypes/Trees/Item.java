package AbstractDataTypes.Trees;

public class Item {

    public enum ComparisonResult {
        Lesser,
        Equal,
        Greater
    }

    private int key;

    public Item(int key) {
        this.key = key;
    }

    /**
     * Compare the keys of two items
     * @param item item to compare
     * @return If this item key is lesser than the provided item key - returns ComparisonResult.Lesser
     * If this item key is greater than the provided item key - returns ComparisonResult.Greater
     */
    public ComparisonResult CompareWith(Item item)
    {
        if (key < item.key) {
            return ComparisonResult.Lesser;
        } else if (key == item.key) {
            return ComparisonResult.Equal;
        } else {
            return ComparisonResult.Greater;
        }

    }

    public int GetKey() {
        return key;
    }

    public void SetKey(int key) {
        this.key = key;
    }
}
