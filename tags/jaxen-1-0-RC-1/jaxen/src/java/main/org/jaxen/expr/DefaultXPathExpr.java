// Copyright 2001 bob mcwhirter and James Strachan. All rights reserved.

package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.JaxenException;

import java.util.List;

public class DefaultXPathExpr implements XPathExpr
{
    private Expr rootExpr;

    public DefaultXPathExpr(Expr rootExpr)
    {
        this.rootExpr = rootExpr;
    }

    public Expr getRootExpr()
    {
        return this.rootExpr;
    }

    public void setRootExpr(Expr rootExpr)
    {
        this.rootExpr = rootExpr;
    }

    public String toString()
    {
        return "[(DefaultXPath): " + getRootExpr() + "]";
    }

    public String getText()
    {
        return getRootExpr().getText();
    }

    public void simplify()
    {
        setRootExpr( getRootExpr().simplify() );
    }

    public List asList(Context context) throws JaxenException
    {
        return DefaultExpr.convertToList( getRootExpr().evaluate( context ) );
    }
}
