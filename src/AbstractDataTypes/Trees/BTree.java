package AbstractDataTypes.Trees;

import static AbstractDataTypes.Trees.Item.ComparisonResult.*;

public class BTree implements Tree {

    private static class Page {
        int numberOfItems;
        Item[] items;
        Page[] childPages;

        public Page(int maxSize) {
            numberOfItems = 0;
            items = new Item[maxSize];
            childPages = new Page[maxSize + 1];
        }

    }

    private Page mainPage;
    private int minSize;
    private int maxSize;
    private int comparisons = 0;

    public BTree(int minSize) {
        mainPage = null;
        this.minSize = minSize;
        this.maxSize = minSize * 2;
    }

    @Override
    public void Insert(Item insertedItem) {
        Item[] returnInsertedItem = new Item[1];
        boolean[] growth = new boolean[1];
        Page returnPage = this.Insert(insertedItem, this.mainPage, returnInsertedItem, growth);

        if (growth[0]) {
            Page tempPage = new Page(maxSize);
            tempPage.items[0] = returnInsertedItem[0];
            tempPage.childPages[0] = this.mainPage;
            tempPage.childPages[1] = returnPage;
            this.mainPage = tempPage;
            this.mainPage.numberOfItems++;
        }
        else {
            this.mainPage = returnPage;
        }

    }

    private Page Insert(Item insertedItem, Page currentPage, Item[] returnInsertedItem, boolean[] growth) {

        Page returnPage = null;

        if (currentPage == null) {
            growth[0] = true;
            returnInsertedItem[0] = insertedItem;
        }
        else {
            int i = 0;

            while ((i < currentPage.numberOfItems - 1) && (insertedItem.CompareWith(currentPage.items[i]) == Greater)) {
                i++;
            }

            if (insertedItem.CompareWith(currentPage.items[i]) == Equal) {
                System.out.println("Error: Register already exists");
                growth[0] = false;
            }
            else {

                if (insertedItem.CompareWith(currentPage.items[i]) == Greater) {
                    i++;
                }

                returnPage = Insert(insertedItem, currentPage.childPages[i], returnInsertedItem, growth);

                if (growth[0])
                    if (currentPage.numberOfItems < this.maxSize) {
                        this.InsertInPage(currentPage, returnInsertedItem[0], returnPage);
                        growth[0] = false;
                        returnPage = currentPage;
                    }
                    else {
                        Page tempPage = new Page(this.maxSize);
                        tempPage.childPages[0] = null;
                        if (i <= this.minSize) {
                            InsertInPage(tempPage, currentPage.items[this.maxSize - 1], currentPage.childPages[this.maxSize]);
                            currentPage.numberOfItems--;
                            InsertInPage(currentPage, returnInsertedItem[0], returnPage);
                        }
                        else {
                            InsertInPage(tempPage, returnInsertedItem[0], returnPage);
                        }

                        for (int j = this.minSize + 1; j < this.maxSize; j++) {
                            InsertInPage(tempPage, currentPage.items[j], currentPage.childPages[j + 1]);
                            currentPage.childPages[j + 1] = null;
                        }

                        currentPage.numberOfItems = this.minSize;
                        tempPage.childPages[0] = currentPage.childPages[this.minSize + 1];
                        returnInsertedItem[0] = currentPage.items[this.minSize];
                        returnPage = tempPage;

                    }
            }
        }

        return (growth[0] ? returnPage : currentPage);

    }

    private void InsertInPage(Page currentPage, Item insertedItem, Page nextPage) {

        int rightmostValidPosition = currentPage.numberOfItems - 1;

        while ((rightmostValidPosition >= 0) &&
                (insertedItem.CompareWith(currentPage.items[rightmostValidPosition]) == Lesser)) {
            currentPage.items[rightmostValidPosition + 1] = currentPage.items[rightmostValidPosition];
            currentPage.childPages[rightmostValidPosition + 2] = currentPage.childPages[rightmostValidPosition + 1];
            rightmostValidPosition--;
        }

        currentPage.items[rightmostValidPosition + 1] = insertedItem;
        currentPage.childPages[rightmostValidPosition + 2] = nextPage;
        currentPage.numberOfItems++;
    }

    @Override
    public Item Search(Item searchItem) {
        return Search(searchItem, mainPage);
    }

    private Item Search(Item searchItem, Page currentPage) {

        comparisons++;

        if (currentPage == null) {
            System.out.println("Error: Value could not be found");
            return null;
        }
        else {
            int i = 0;

            while ((i < (currentPage.numberOfItems - 1)) &&
                    (searchItem.CompareWith(currentPage.items[i]) == Greater)) {
                i++;
            }

            if (searchItem.CompareWith(currentPage.items[i]) == Equal) {
                return currentPage.items[i];
            }
            else if (searchItem.CompareWith(currentPage.items[i]) == Lesser) {
                return Search(searchItem, currentPage.childPages[i]);
            }
            else {
                return Search(searchItem, currentPage.childPages[i + 1]);
            }
        }
    }

    @Override
    public int GetComparisons() {
        return comparisons;
    }
}
