
package org.jaxen;

import java.util.Map;
import java.util.HashMap;

public class SimpleFunctionContext implements FunctionContext
{
    private HashMap functions;

    public SimpleFunctionContext()
    {
        this.functions = new HashMap();
    }

    public void registerFunction( String namespaceURI,
                                  String localName,
                                  Function function )
    {
        this.functions.put( new QualifiedName(namespaceURI, localName),
                            function );
    }

    public Function getFunction( String namespaceURI,
                                 String prefix,
                                 String localName )
        throws UnresolvableException
    {
        Object key = new QualifiedName( namespaceURI, localName );

        if ( this.functions.containsKey(key) )
            return (Function) this.functions.get( key );
        else
            throw new UnresolvableException( "Function " +
                                             prefix + ":" + localName );
    }
}