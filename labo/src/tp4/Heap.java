package tp4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {
    final private ArrayList<ValueType> data;
    final private boolean isMin;

    // O(1)
    public Heap() {
        this(true);
    }

    // O(1): construction sans donnees initiales
    public Heap(boolean isMin) {
        // TODO
        this.data = new ArrayList<>();
        this.isMin = isMin;
    }

    // O(n)
    public Heap(Collection<ValueType> data) {
        this(true, data);
    }

    // O(n): construction avec donnees initiales, allez voir le lien dans la description pour vous aider
    public Heap(boolean isMin, Collection<ValueType> data) {
        this.isMin = isMin;
        this.data = new ArrayList<>(data);
        build();
    }

    // O(1): on retourne le nombre d'elements dans la liste
    public int size() {
        return this.data.size();
    }

    // O(1): on compare deux elements en fonction du type de monceau
    private boolean compare(ValueType first, ValueType second) {
        if(isMin) {
            return first.compareTo(second) < 0;
        } else {
            return first.compareTo(second) > 0;
        }
    }

    // O(1): on donne l'indice du parent
    private int parentIdx(int idx) {
        return idx / 2;
    }

    // O(1): on donne l'indice de l'enfant de gauche
    private int leftChildIdx(int idx) {
        return (2*idx);
    }

    // O(1): on donne l'indice de l'enfant de droite
    private int rightChildIdx(int idx) {
        return (2*idx) + 1;
    }

    // O(1): on echange deux elements dans le tableau
    private void swap(int firstIdx, int secondIdx) {
        // TODO
        ValueType value = data.get(firstIdx);
        data.set(firstIdx, data.get(secondIdx));
        data.set(secondIdx, value);
    }

    // O(log(n)): l'index courant represente le parent, on s'assure que le parent soit le min/max avec ses enfants
    // De facon visuelle, ceci ammene un noeud le plus haut possible dans l'arbre
    // Par exemple: si le min/max est une feuille, on appelera resursivement log(n) fois la methode pour monter le noeud
    private void heapify(int idx) {
        // TODO
        int left = leftChildIdx(idx);
        int right = rightChildIdx(idx);

        int newIdx = idx;

        if(left < size() && compare(data.get(left), data.get(idx))) {
            newIdx = left;
        }
        if(right < size() && compare(data.get(right), data.get(newIdx))) {
            newIdx = right;
        }
        if(newIdx != idx) {
            swap(idx, newIdx);
            heapify(newIdx);
        }
    }

    // O(log(n)): on ajoute un element et on preserve les proprietes du monceau
    public void insert(ValueType element) {
        // TODO
        data.add(element);
        int i = size() - 1;
        int parent = parentIdx(i);
        while(parent != i && compare(data.get(i), data.get(parent))) {
            swap(i, parent);
            i = parent;
            parent = parentIdx(i);
        }
    }

    // O(n): on s'assure que tous les elements sont bien places dans le tableau,
    // allez voir le lien dans la description pour vous aider
    public void build() {
        // TODO
        for(int i = size() / 2 - 1; i >= 0; --i) {
            heapify(i);
        }
    }

    // O(log(n)): on retire le min ou le max et on preserve les proprietes du monceau
    public ValueType pop() {
        // TODO
        if(size() == 0) {
            return null;
        } else if (size() == 1) {
            ValueType root = data.get(0);
            data.remove(0);
            return root;
        }

        ValueType root = data.get(0);
        data.set(0, data.get(size() - 1));
        data.remove(size() - 1);

        heapify(0);

        return root;
    }

    // O(1): on retourne sans retirer le plus petit ou plus grand element.
    public ValueType peek() {
        // TODO
        if(size() == 0) {
            return null;
        } else {
            return data.get(0);
        }
    }

    // O(nlog(n)): On applique l'algorithme Heap Sort, on s'attend a ce que le monceau soit vide a la fin.
    public List<ValueType> sort() {
        // TODO
        ArrayList<ValueType> list = new ArrayList<>();
        for(int i = size() - 1; i >= 0; --i) {
            list.add(pop());
        }
        return list;
    }

    // Creation d'un iterateur seulement utilise dans les tests, permet de faire des boucles "for-each"
    @Override
    public Iterator<ValueType> iterator() {
        // TODO
        return new Iterator<>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size();
            }

            @Override
            public ValueType next() {
                if(current < size()) {
                    return data.get(current++);
                }
                return null;
            }
        };
    }
}
