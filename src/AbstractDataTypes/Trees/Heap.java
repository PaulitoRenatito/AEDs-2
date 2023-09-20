package AbstractDataTypes.Trees;

import static AbstractDataTypes.Trees.Item.ComparisonResult.*;

public class Heap {

    private Item[] v;

    private int n;

    private int comparisons;

    public Heap (Item[] v) {
        this.v = v;
        this.n = this.v.length - 1;
    }

    public void refaz ( int esq, int dir ) {

        int j = esq * 2;

        Item x = this.v[esq];

        while ( j <= dir ) {

            comparisons++;

            if (( j < dir ) && (this.v[j].CompareWith(this.v[j + 1]) == Lesser))
                j++;
            if (x.CompareWith(this.v[j]) == Equal || x.CompareWith(this.v[j]) == Greater) break;
            this.v[esq] = this.v[j];
            esq = j;
            j = esq * 2;
        }

        this.v[esq] = x;
    }

    public void constroi () {
        int esq = n / (2 + 1);
        while (esq > 1) {
            esq--;
            this.refaz(esq, this.n);
        }
    }

    public Item max() {
        return this .v[1];
    }

    public Item retiraMax() throws Exception {
        Item maximo;
        if (this .n < 1) throw new Exception ( "Erro : heap vazio" );
        else {
            maximo = this .v[1];
            this.v[1] = this.v[this.n--];
            refaz (1 , this.n);
        }
        return maximo;
    }

    public void aumentaChave(int i, Item newItem) throws Exception {
        Item x = this.v[i];

        if (newItem == null)
            throw new Exception ( "Error : newItem has null value" );

        x.SetKey(newItem.GetKey());

        while (( i > 1) && (x.CompareWith(this.v[i / 2]) == Equal || x.CompareWith(this.v[i / 2]) == Greater)) {
            this.v[i] = this.v[ i / 2 ];
            i /= 2;
        }

        this.v[i] = x;
    }
    public void Insert(Item newItem) throws Exception {
        this.n++;
        if (this.n == this.v.length) throw new Exception( "Erro : heap cheio" );
        this.v[this.n] = newItem;
        this.v[this.n].SetKey(Integer.MIN_VALUE);
        this.aumentaChave (this.n, newItem);
    }

    public int GetComparisons() {
        return comparisons;
    }

}
