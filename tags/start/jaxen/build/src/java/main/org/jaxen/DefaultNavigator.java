// Copyright 2001 werken digital. All rights reserved.

package org.jaxen;

import org.jaxen.util.SelfAxisIterator;
import org.jaxen.util.DescendantOrSelfAxisIterator;
import org.jaxen.util.AncestorOrSelfAxisIterator;
import org.jaxen.util.AncestorAxisIterator;
import org.jaxen.util.DescendantAxisIterator;

import java.util.Iterator;

/** Default implementation of {@link Navigator}.
 *
 *  <p>
 *  This implementation is an abstract class, since
 *  some required operations cannot be implemented without
 *  additional knowledge of the object model.
 *  </p>
 *
 *  <p>
 *  When possible, default method implementations build
 *  upon each other, to reduce the number of methods required
 *  to be implemented for each object model.  All methods,
 *  of course, may be overridden, to provide more-efficient
 *  implementations.
 *  </p>
 *
 *  @author bob mcwhirter (bob@werken.com)
 */
public abstract class DefaultNavigator implements Navigator
{

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getChildAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("child");
    }

    public Iterator getDescendantAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        return new DescendantAxisIterator( contextNode,
                                           this );
    }

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getParentAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("parent");
    }

    public Iterator getAncestorAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        return new AncestorAxisIterator( contextNode,
                                         this );
    }

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getFollowingSiblingAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("following-sibling");
    }

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getPrecedingSiblingAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("preceding-sibling");
    }

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getFollowingAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("following");
    }

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getPrecedingAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("preceding");
    }

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getAttributeAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("attribute");
    }

    /** Throws <code>UnsupportedAxisException</code>
     */
    public Iterator getNamespaceAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        throw new UnsupportedAxisException("namespace");
    }

    public Iterator getSelfAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        return new SelfAxisIterator( contextNode );
    }

    public Iterator getDescendantOrSelfAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        return new DescendantOrSelfAxisIterator( contextNode,
                                                 this );
    }

    public Iterator getAncestorOrSelfAxisIterator(Object contextNode) throws UnsupportedAxisException
    {
        return new AncestorOrSelfAxisIterator( contextNode,
                                               this );
    }

    public Object getDocumentNode(Object contextNode)
    {
        return null;
    }
}