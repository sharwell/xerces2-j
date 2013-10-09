/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.xerces.impl.xs.util;

import java.lang.reflect.Array;
import java.util.AbstractList;

import org.apache.xerces.xs.datatypes.ObjectList;

/**
 * Contains a list of Objects.
 *
 * @xerces.internal
 *
 * @version $Id$
 */
public final class ObjectListImpl<E> extends AbstractList<E> implements ObjectList<E> {

    /**
     * An immutable empty list.
     */
    public static final ObjectListImpl EMPTY_LIST = new ObjectListImpl(new Object[0], 0);
    
    // The array to hold all data
    private final E[] fArray;
    
    // Number of elements in this list
    private final int fLength;

    public ObjectListImpl(E[] array, int length) {
        fArray = array;
        fLength = length;
    }

    public int getLength() {
        return fLength;
    }
    
    public boolean contains(Object item) {
        if (item == null) {
            for (int i = 0; i < fLength; i++) {
                if (fArray[i] == null)
                    return true;
            }
        }
        else {
            for (int i = 0; i < fLength; i++) {
                if (item.equals(fArray[i]))
                    return true;
            }
        }
        return false;
    }
    
    public E item(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        return fArray[index];
    }
    
    /*
     * List methods
     */
    public E get(int index) {
        if (index >= 0 && index < fLength) {
            return fArray[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }
    
    public Object[] toArray() {
        Object[] a = new Object[fLength];
        toArray0(a);
        return a;
    }
    
    public Object[] toArray(Object[] a) {
        if (a.length < fLength) {
            Class arrayClass = a.getClass();
            Class componentType = arrayClass.getComponentType();
            a = (Object[]) Array.newInstance(componentType, fLength);
        }
        toArray0(a);
        if (a.length > fLength) {
            a[fLength] = null;
        }
        return a;
    }

    private void toArray0(Object[] a) {
        if (fLength > 0) {
            System.arraycopy(fArray, 0, a, 0, fLength);
        }
    }
}
