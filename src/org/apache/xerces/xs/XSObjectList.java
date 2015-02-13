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

package org.apache.xerces.xs;

import java.util.List;

/**
 *  The {@link XSObjectList} interface provides the abstraction of an 
 * immutable ordered collection of {@link XSObject}s, without defining 
 * or constraining how this collection is implemented. 
 */
public interface XSObjectList extends List {
    /**
     *  The number of {@link XSObjects} in the list. The range of valid 
     * child object indices is 0 to {@code length-1} inclusive. 
     */
    public int getLength();

    /**
     *  Returns the {@code index}th item in the collection or 
     * <code>null</code> if <code>index</code> is greater than or equal to 
     * the number of objects in the list. The index starts at 0. 
     * @param index  index into the collection. 
     * @return  The {@link XSObject} at the {@code index}th 
     *   position in the {@link XSObjectList}, or <code>null</code> if 
     *   the index specified is not valid. 
     */
    public XSObject item(int index);

}
