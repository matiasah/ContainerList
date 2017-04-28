/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerlist;

import java.util.ListIterator;

/**
 *
 * @author Matías
 */
public class ContainerListIterator implements ListIterator {

    private ContainerList list;
    private Container current;
    private int index;
    
    private Container rCurrent;
    private int rIndex; // last returned index

    public ContainerListIterator(ContainerList list) {

        this.list = list;
        this.current = this.list.getFirstContainer();
        this.index = 0;

    }
    
    public ContainerListIterator(ContainerList list, int index) throws IndexOutOfBoundsException {
        
        if ( index < 0 || index > this.list.size() ) {
            
            throw new IndexOutOfBoundsException();
            
        }
        
        this.list = list;
        this.current = this.list.getFirstContainer();
        this.index = 0;
        
        while ( this.current != null ) {
            
            int currentSize = this.current.getSize();
            
            if ( index <= currentSize && index <= this.list.getContainerElements() ) {
                
                break;
                
            }
            
            this.index = this.index - currentSize;
            this.current = this.current.getNext();
            
        }
        
        if ( this.current == null ) {
            
            throw new IndexOutOfBoundsException();
            
        }
        
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

            this.rCurrent = this.current;
            this.rIndex = this.index;
            this.index++;

            if (this.index > this.current.getSize() || this.index >= this.list.getContainerElements()) {

                this.index = 0;
                this.current = this.current.getNext();

            }

            return value;

        }

        return null;

    }

    @Override
    public boolean hasPrevious() {

        if (this.current != null) {

            if (index == 0) {

                return this.current.getPrevious() != null;

            }

            return true;

        }

        return false;

    }

    @Override
    public Object previous() {

        if (this.current != null) {

            Object value = this.current.get(this.index);

            this.rCurrent = this.current;
            this.rIndex = this.index;
            this.index--;

            if (this.index < 0) {

                this.current = this.current.getPrevious();

                if (this.current != null) {

                    this.index = this.current.getSize();

                }

            }

            return value;

        }

        return null;

    }

    @Override
    public int nextIndex() {
        
        return this.current.getIndex() + this.index + 1;
        
    }

    @Override
    public int previousIndex() {
        
        return this.current.getIndex() + this.index - 1;
        
    }

    @Override
    public void remove() {
        
        if ( this.rCurrent != null ) {
            
            this.rCurrent.remove(this.rIndex);
            
        }
        
    }

    @Override
    public void set(Object e) {
        
        if ( this.rCurrent != null ) {
            
            this.rCurrent.set(this.rIndex, e);
            
        }
        
    }

    @Override
    public void add(Object e) {
        
        if ( this.rCurrent != null ) {
            
            this.rCurrent.add(this.rIndex, e);
            
        }
        
    }

}
