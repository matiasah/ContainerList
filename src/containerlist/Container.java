/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerlist;

/**
 *
 * @author Matías
 */
public class Container {

    private ContainerList list;
    private Container next;
    private Container previous;
    private int nElements;
    private Object[] content;

    public int min;
    public int max;

    public Container(ContainerList list, int elements) {

        this.content = new Object[elements];

    }

    public Object getElement(int index) {

        return this.content[index];

    }

    public int getSize() {

        return this.nElements;

    }

    public int getAvailableSize() {

        return this.content.length - this.nElements;

    }

    public boolean contains(Object element) {

        for (int i = 0; i < this.nElements; i++) {

            if (this.content[i] == element) {

                return true;

            }

        }

        return false;

    }

    public int indexOf(Object element) {

        for (int i = 0; i < this.nElements; i++) {

            if (this.content[i] == element) {

                return i;

            }

        }

        return -1;

    }

    public int lastIndexOf(Object element) {

        for (int i = this.nElements; i >= 0; i--) {

            if (this.content[i] == element) {

                return i;

            }

        }

        return -1;

    }

    public boolean add(Object element) {

        if (this.nElements < this.content.length) {

            this.content[this.nElements] = element;
            this.nElements++;

            return true;

        }

        return false;

    }

    public Object set(int index, Object element) {

        if (index < this.nElements || (index == this.nElements && this.nElements < this.content.length)) {

            Object o = this.content[index];

            this.content[index] = element;

            if (index == this.nElements) {

                this.nElements++;

            }

            return o;

        }

        return null;

    }

    public boolean add(int index, Object element) {

        if (this.nElements < this.content.length) {

            if (index <= this.nElements) {

                for (int i = this.nElements; i > index; i--) {

                    this.content[i] = this.content[i - 1];

                }

                this.content[index] = element;
                this.nElements++;

                return true;

            }

        } else if (this.next != null) {

            if (this.next.getAvailableSize() > 0) {

                this.next.add(0, this.content[this.content.length - 1]);

                for (int i = this.content.length - 1; i > index; i--) {

                    this.content[i] = this.content[i - 1];

                }

                this.content[index] = element;

            } else {

                Container next = new Container(this.list, this.content.length);

                next.setPrevious(this);
                next.setNext(this.next);

                this.next.setPrevious(next);
                this.setNext(next);

                this.next.add(0, this.content[this.content.length - 1]);

                for (int i = this.content.length - 1; i > index; i--) {

                    this.content[i] = this.content[i - 1];

                }

                this.content[index] = element;

            }

        } else {

            this.setNext(new Container(this.list, this.content.length));
            this.next.setPrevious(previous);
            this.next.add(0, this.content[this.content.length - 1]);

            for (int i = this.content.length - 1; i > index; i--) {

                this.content[i] = this.content[i - 1];

            }

            this.content[index] = element;

        }

        return false;

    }

    public Object get(int index) {

        if (index < this.nElements) {

            return this.content[index];

        }

        return null;

    }

    public boolean isEmpty() {

        return this.nElements == 0;

    }

    public int getIndex() {

        if (this.previous != null) {

            return this.previous.getIndex() + this.previous.getSize();

        }

        return 0;

    }

    public int getLocalIndex() {

        if (this.previous != null) {

            return this.previous.getSize();

        }

        return 0;

    }

    public Container getNext() {

        return this.next;

    }

    public Container getPrevious() {

        return this.previous;

    }

    public void setNext(Container next) {

        this.next = next;

    }

    public void setPrevious(Container previous) {

        this.previous = previous;

    }

    public Object remove(int index) {

        if (index >= 0 && index < this.nElements) {

            Object o = this.content[index];

            for (int i = index; i < this.nElements; i++) {

                this.content[i] = this.content[i + 1];

            }

            this.nElements--;
            this.removeIfEmpty();

            return o;

        }

        return null;

    }

    public boolean remove(Object o) {

        for (int i = 0; i < this.nElements; i++) {

            if (this.content[i] == o) {

                this.remove(i);
                this.removeIfEmpty();

                return true;

            }

        }

        return false;

    }

    private void removeIfEmpty() {

        if (this.nElements == 0) {

            this.previous.next = this.next;
            this.next.previous = this.previous;

        }

    }

}