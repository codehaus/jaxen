
package org.jaxen.function;

import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.Navigator;
import org.jaxen.FunctionCallException;

import java.util.List;

/**
 *   <p><b>4.1</b> <code><i>string</i> local-name(<i>node-set?</i>)</code> 
 *  
 *  @author bob mcwhirter (bob @ werken.com)
 */
public class LocalNameFunction implements Function
{

    public Object call(Context context,
                       List args) throws FunctionCallException
    {
        if ( args.size() == 0 )
        {
            return evaluate( context.getNodeSet(),
                             context.getNavigator() );
        }

        if ( args.size() == 1 )
        {
            return evaluate( args,
                             context.getNavigator() );
        }

        throw new FunctionCallException( "local-name() requires zero or one argument." );
    }

    public static String evaluate(List list,
                                  Navigator nav)
    {
        if ( ! list.isEmpty() )
        {            
            Object first = list.get(0);

            if (first instanceof List)
            {
                return evaluate( (List) first,
                                 nav );
            }
            else if ( nav.isElement( first ) )
            {
                return nav.getElementName( first );
            }
            else if ( nav.isAttribute( first ) )
            {
                return nav.getAttributeName( first );
            }
        }

        return "";
    }
}
