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

/**
 * This interface represents a complex or simple type definition.
 */
public interface XSTypeDefinition extends XSObject {
    /**
     * The object describes a complex type.
     */
    public static final short COMPLEX_TYPE              = 15;
    /**
     * The object describes a simple type.
     */
    public static final short SIMPLE_TYPE               = 16;
    /**
     * Return whether this type definition is a simple type or complex type.
	 *
	 * @return {@link #COMPLEX_TYPE} if the type is a complex type; otherwise,
	 * {@link #SIMPLE_TYPE} if the type is a simple type.
     */
    public short getTypeCategory();

    /**
     * {base type definition}: either a simple type definition or a complex 
     * type definition. 
     */
    public XSTypeDefinition getBaseType();

    /**
     * {final}. For a complex type definition it is a subset of {extension, 
     * restriction}. For a simple type definition it is a subset of 
     * {extension, list, restriction, union}. 
     * @param restriction  Extension, restriction, list, union constants 
     *   (defined in {@link XSConstants}). 
     * @return True if <code>restriction</code> is in the final set, 
     *   otherwise false.
     */
    public boolean isFinal(short restriction);

    /**
     * For complex types the returned value is a bit combination of the subset 
     * of {{@link XSConstants#DERIVATION_EXTENSION}, {@link XSConstants#DERIVATION_RESTRICTION}} 
     * corresponding to <code>final</code> set of this type or 
     * {@link XSConstants#DERIVATION_NONE}. For simple types the returned value is 
     * a bit combination of the subset of { 
     * {@link XSConstants#DERIVATION_RESTRICTION}, {@link XSConstants#DERIVATION_EXTENSION}, {@link XSConstants#DERIVATION_UNION}, {@link XSConstants#DERIVATION_LIST}
     * } corresponding to <code>final</code> set of this type or 
     * {@link XSConstants#DERIVATION_NONE}. 
     */
    public short getFinal();

    /**
     *  Convenience attribute. A boolean that specifies if the type definition 
     * is anonymous. 
     */
    public boolean getAnonymous();

    /**
     * Convenience method which checks if this type is derived from the given 
     * {@code ancestorType}. 
     * @param ancestorType  An ancestor type definition. 
     * @param derivationMethod  A bit combination representing a subset of {
     *   {@link XSConstants#DERIVATION_RESTRICTION}, {@link XSConstants#DERIVATION_EXTENSION}, {@link XSConstants#DERIVATION_UNION}, {@link XSConstants#DERIVATION_LIST}
     *   }. 
     * @return  True if this type is derived from {@code ancestorType}
     *   using only derivation methods from the {@code derivationMethod}
     *   . 
     */
    public boolean derivedFromType(XSTypeDefinition ancestorType, 
                                   short derivationMethod);

    /**
     * Convenience method which checks if this type is derived from the given 
     * ancestor type. 
     * @param namespace  An ancestor type namespace. 
     * @param name  An ancestor type name. 
     * @param derivationMethod  A bit combination representing a subset of {
     *   {@link XSConstants#DERIVATION_RESTRICTION}, {@link XSConstants#DERIVATION_EXTENSION}, {@link XSConstants#DERIVATION_UNION}, {@link XSConstants#DERIVATION_LIST}
     *   }. 
     * @return  True if this type is derived from <code>ancestorType</code> 
     *   using only derivation methods from the <code>derivationMethod</code>
     *   . 
     */
    public boolean derivedFrom(String namespace, 
                               String name, 
                               short derivationMethod);
    
    /**
     * A property that simplifies testing for the identity of anonymous type
     * definitions.
     *
     * <p>For complex type definition:</p>
	 * <ol>
	 * <li>If the name [attribute] is present (with the exception of the type being
     *    redefined, number 2 below), then absent, otherwise the Element
     *    Declaration corresponding to that parent information item</li>
     * <li>In the case of redefine, the context of the redefined complex type is the
     *    redefining complex type definition</li>
	 * </ol>
     * 
     * <p>For simple type definition,</p>
	 * <ol>
     * <li>If the name [attribute] is present (with the exception of the type being
     *    redefined, number 3 below), then absent</li>
     * <li>otherwise the appropriate case among the following:
	 *    <ol>
     *    <li>If the parent element information item is attribute, then the
     *        corresponding Attribute Declaration</li>
     *    <li>If the parent element information item is element, then the
     *        corresponding Element Declaration</li>
     *    <li>If the parent element information item is list or union, then
     *        the Simple Type Definition corresponding to the grandparent simpleType
     *        element information item</li>
     *    <li>otherwise (the parent element information item is restriction), the
     *        appropriate case among the following:
	 *        <ol>
     *        <li>If the grandparent element information item is simpleType, then
     *              the Simple Type Definition corresponding to the grandparent</li>
     *        <li>otherwise (the grandparent element information item is simpleContent),
     *              the Simple Type Definition which is the {content type} of the Complex
     *              Type Definition corresponding to the great-grandparent complexType
     *              element information item.</li>
	 *        </ol></li>
	 *    </ol></li>
     * <li>In the case of redefine, the context of the redefined simple type is the
     *    redefining simple type definition</li>
	 * </ol>
     */
    public XSObject getContext();
}
