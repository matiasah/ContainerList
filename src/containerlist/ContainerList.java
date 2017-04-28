/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Matías
 */
public class ContainerList<E> implements List {

    private int size = 0;
    private int containerElements = 0;
    private Container first;
    private Container last;

    public ContainerList(int containerElements) {

        this.containerElements = containerElements;

    }

    @Override
    public int size() {

        return this.size;

    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {

        if (this.last != null) {

            Object ar[] = new Object[this.last.getIndex() + this.last.getSize()];

            Container current = this.first;
            int j = 0;

            while (current != null) {

                for (int i = 0; i < current.getSize(); i++) {

                    ar[j] = current.get(i);
                    j++;

                }

                current = current.getNext();

            }

            return ar;

        }

        return new Object[0];

    }

    @Override
    public Object[] toArray(Object[] a) {

        Class arrayClass = a.getClass();
        Container current = this.first;
        int j = 0;

        while (current != null) {

            for (int i = 0; i < current.getSize(); i++) {

                Object element = current.get(i);

                if (arrayClass.equals(element.getClass())) {

                    a[j] = element;
                    j++;

                }

            }

            current = current.getNext();

        }

        return a;

    }

    @Override
    public boolean remove(Object o) {

        Container current = this.first;

        while (current != null) {

            if (current.remove(o)) {

                return true;

            }

            current = current.getNext();

        }

        return false;

    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {

        this.first = null;
        this.last = null;
        this.size = 0;

    }

    @Override
    public Object get(int index) {

        Container current = this.first;

        while (current != null) {

            index -= current.getLocalIndex();

            if (index <= current.getSize() && index < this.containerElements) {

                return current.get(index);

            }

            current = current.getNext();

        }

        return -1;
    }

    @Override
    public Object set(int index, Object element) {

        if (this.first == null) {

            if (index == 0) {

                this.first = new Container(this, this.containerElements);
                this.last = this.first;

            } else {

                throw new IndexOutOfBoundsException();

            }

        }

        Container current = this.first;

        while (current != null) {

            index -= current.getLocalIndex();

            if (index <= current.getSize() && index < this.containerElements) {

                return current.set(index, element);

            }

            current = current.getNext();

        }

        if (current == null && index == this.containerElements) {
            // No container but index is the size of the container list
            Container newLast = new Container(this, this.containerElements);

            newLast.setPrevious(this.last);
            this.last.setNext(newLast);
            this.last = newLast;

            this.last.add(0, element);

        }

        return null;

    }

    @Override
    public void add(int index, Object element) {

        if (this.first == null) {

            this.first = new Container(this, this.containerElements);
            this.last = this.first;

        }

        Container current = this.first;

        while (current != null) {

            index -= current.getLocalIndex();

            if (index <= current.getSize() && index < this.containerElements) {

                current.add(index, element);

                break;

            }

            current = current.getNext();

        }

        if (current == null && index == this.containerElements) {
            // No container but index is the size of the container list
            Container newLast = new Container(this, this.containerElements);

            newLast.setPrevious(this.last);
            this.last.setNext(newLast);
            this.last = newLast;

            this.last.add(0, element);

        }

    }

    @Override
    public Object remove(int index) {

        Container current = this.first;

        while (current != null) {

            index -= current.getLocalIndex();

            if (index < current.getSize()) {

                return current.remove(index);

            }

            current = current.getNext();

        }

        while (this.first != null && this.first.isEmpty()) {

            this.first = this.first.getNext();

        }

        while (this.last != null && this.last.isEmpty()) {

            this.last = this.last.getPrevious();

        }

        return null;

    }

    @Override
    public int indexOf(Object o) {

        Container current = this.first;

        while (current != null) {

            int index = current.indexOf(o);

            if (index >= 0) {

                return index + current.getIndex();

            }

            current = current.getNext();

        }

        return -1;

    }

    @Override
    public int lastIndexOf(Object o) {

        Container current = this.last;

        while (current != null) {

            int index = current.lastIndexOf(o);

            if (index >= 0) {

                return index + current.getIndex();

            }

            current = current.getPrevious();

        }

        return -1;

    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        
        if ( fromIndex < 0 || toIndex > this.size || fromIndex > toIndex) {
            
            throw new IndexOutOfBoundsException();
            
        }

        ContainerList list = new ContainerList(this.containerElements);
        Container current = this.first;
        int j = 0;

        while (current != null) {
            
            int currentSize = current.getSize();

            if (fromIndex <= currentSize && currentSize <= toIndex) {
                
                int maxSize = Math.min( current.getSize(), toIndex + 1);
                int minSize = Math.max(fromIndex, 0);
                
                for (int i = minSize; i < maxSize; i++) {
                    
                    list.set(j, current.get(i));
                    
                    j++;
                    
                }

            }

            fromIndex = fromIndex - currentSize;
            toIndex = toIndex - currentSize;
            current = current.getNext();

        }

        return list;

    }

    @Override
    public boolean isEmpty() {

        Container current = this.first;

        while (current != null) {

            if (current.isEmpty()) {

                return true;

            }

            current = current.getNext();

        }

        return true;

    }

    @Override
    public boolean contains(Object o) {

        Container current = this.first;

        while (current != null) {

            if (current.contains(o)) {

                return true;

            }

            current = current.getNext();

        }

        return false;

    }

    @Override
    public boolean add(Object e) {

        if (this.first == null) {

            this.first = new Container(this, this.containerElements);
            this.last = this.first;

        }

        Container current = this.first;

        while (current != null) {

            if (current.getAvailableSize() > 0) {

                return current.add(e);

            }

            current = current.getNext();

        }

        Container newLast = new Container(this, this.containerElements);
        newLast.setPrevious(this.last);

        this.last.setNext(newLast);
        this.last = newLast;

        return this.last.add(e);

    }

    public int countContainers() {

        Container current = this.first;
        int count = 0;

        while (current != null) {

            count++;
            current = current.getNext();

        }

        return count;

    }

    public void increaseSize() {

        this.size++;

    }

    public void decreaseSize() {

        this.size--;

    }

}
