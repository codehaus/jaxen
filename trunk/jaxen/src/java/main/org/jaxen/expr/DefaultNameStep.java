// Copyright 2001 bob mcwhirter and James Strachan. All rights reserved.

package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.Navigator;

import org.jaxen.expr.iter.IterableAxis;


public class DefaultNameStep extends DefaultStep
{
    private String prefix;
    private String localName;
    private boolean matchesAnyName;
    private boolean matchesAnyNamespace;

    public DefaultNameStep(IterableAxis axis,
                           String prefix,
                           String localName)
    {
        super( axis );

        this.prefix    = prefix;
        this.localName = localName;
        this.matchesAnyName = "*".equals( localName );
        this.matchesAnyNamespace = matchesAnyName || prefix.equals( "*" );        
    }

    public String getPrefix()
    {
        return this.prefix;
    }

    public String getLocalName()
    {
        return this.localName;
    }
    
    public boolean isMatchesAnyName() 
    {
        return matchesAnyName;
    }

    public boolean isMatchesAnyNamespace() 
    {
        return matchesAnyNamespace;
    }

    public String getText()
    {
        if ( ( getPrefix() != null )
             &&
             ( ! getPrefix().equals("") ) )
        {
            return getAxisName() + "::" + getPrefix() + ":" + getLocalName() + super.getText();
        }

        return getAxisName() 
            + "::" + getLocalName()
            + super.getText();
    }

    public String toString()
    {
        return "[(DefaultNameStep): " + getPrefix() + ":" + getLocalName() + "[" + super.toString() + "]]";
    }

    public boolean matches(Object node,
                           ContextSupport contextSupport)
    {
        Navigator nav  = contextSupport.getNavigator();

        String nodeUri  = null;
        String nodeName = null;

        if ( nav.isElement( node ) )
        {
            nodeUri  = nav.getElementNamespaceUri( node );
            nodeName = nav.getElementName( node );

        }
        else if ( nav.isAttribute( node ) )
        {
            nodeUri  = nav.getAttributeNamespaceUri( node );
            nodeName = nav.getAttributeName( node );
        }
        else if ( nav.isDocument( node ) )
        {
            return matchesAnyName;
        }
        else if ( nav.isNamespace( node ) )
        {
            return matchesAnyName;
        }
        else
        {
            return false;
        }

        //System.out.println( "Matching nodeURI: " + nodeUri + " name: " + nodeName );
        
        if ( matchesAnyNamespace )
        {
            return matchesAnyName || getLocalName().equals( nodeName );
        }
        else
        {
            String myPrefix = getPrefix();         
            String myUri = null;
            
            if ( myPrefix != null && myPrefix.length() > 0 ) 
            {
                myUri = nav.translateNamespacePrefixToUri( myPrefix, node );
            }
            
            if ( myUri == null ) 
            {
                myUri = contextSupport.translateNamespacePrefixToUri( myPrefix );
            }
                
            if ( matchesNamespaceURIs( myUri, nodeUri ) )
            {
                return matchesAnyName || getLocalName().equals( nodeName );
            }
        }
        return false;
    }
    
    /** @return true if the two namespace URIs are equal
     * Note that we may wish to consider null being equal to ""
     */
    protected boolean matchesNamespaceURIs( String u1, String u2 ) {
        //System.out.println( "Comparing URI: " + u1 + " against URI: " + u2 );
        
        if ( u1 == u2 ) {
            return true;
        }
        if ( u1 == null ) 
        {
            u1 = "";
        }
        if ( u2 == null ) 
        {
            u2 = "";
        }
        return u1.equals( u2 );
    }
    
}
