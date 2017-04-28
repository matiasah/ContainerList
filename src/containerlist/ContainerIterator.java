/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerlist;

import java.util.Iterator;

/**
 *
 * @author Matías
 */
public class ContainerIterator implements Iterator {

    private ContainerList list;
    private Container current;
    private int index;

    public ContainerIterator(ContainerList list) {

        this.list = list;
        this.current = this.list.getFirstContainer();
        this.index = 0;

    }

    @Override
    public boolean hasNext() {

        if (this.current != null) {

            if (index == this.current.getSize()) {

                return this.current.getNext() != null;

            }

            return true;

        }

        return false;

    }

    @Override
    public Object next() {

        if (this.current != null) {

            Object value = this.current.get(this.index);

            this.index++;

            if (this.index > this.current.getSize() || this.index >= this.list.getContainerElements()) {

                this.index = 0;
                this.current = this.current.getNext();

            }

            return value;

        }
        
        return null;

    }

}
