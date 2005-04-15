/*
 * $Header$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2005 Elliotte Rusty Harold.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions, and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions, and the disclaimer that follows 
 *    these conditions in the documentation and/or other materials 
 *    provided with the distribution.
 *
 * 3. The name "Jaxen" must not be used to endorse or promote products
 *    derived from this software without prior written permission.  For
 *    written permission, please contact license@jaxen.org.
 * 
 * 4. Products derived from this software may not be called "Jaxen", nor
 *    may "Jaxen" appear in their name, without prior written permission
 *    from the Jaxen Project Management (pm@jaxen.org).
 * 
 * In addition, we request (but do not require) that you include in the 
 * end-user documentation provided with the redistribution and/or in the 
 * software itself an acknowledgement equivalent to the following:
 *     "This product includes software developed by the
 *      Jaxen Project (http://www.jaxen.org/)."
 * Alternatively, the acknowledgment may be graphical using the logos 
 * available at http://www.jaxen.org/
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE Jaxen AUTHORS OR THE PROJECT
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * ====================================================================
 * This software consists of voluntary contributions made by many 
 * individuals on behalf of the Jaxen Project and was originally 
 * created by bob mcwhirter <bob@werken.com> and 
 * James Strachan <jstrachan@apache.org>.  For more information on the 
 * Jaxen Project, please see <http://www.jaxen.org/>.
 * 
 * $Id$
 */

package org.jaxen.function;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.jaxen.BaseXPath;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom.DOMXPath;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author Elliotte Rusty Harold
 *
 */
public class SubstringAfterTest extends TestCase {

    private Document doc;
    
    public void setUp() throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.newDocument();
        doc.appendChild(doc.createElement("root"));
    }


    public SubstringAfterTest(String name) {
        super(name);
    }

    public void testSubstringAfterNumber() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after(33, '3')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("3", result);
    }    
  
    public void testSubstringAfterString() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after('test', 'es')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("t", result);
    }    
  
    public void testSubstringAfterString4() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after('superlative', 'superlative')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("", result);
    }    
  
    public void testSubstringAfterNumber2() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after(43, '0')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("", result);
    }    
  
    public void testSubstringAfterString2() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after('1234567890', '1234567a')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("", result);
    }    
  
  
    public void testSubstringAfterString3() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after('1234567890', '456')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("7890", result);
    }    
  
  
    public void testEmptyStringSubstringAfterNonEmptyString() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after('', 'a')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("", result);
    }
  
    public void testEmptyStringBeforeEmptyString() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after('', '')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("", result);
    }
  
    public void testSubstringAfterEmptyString() throws JaxenException
    {
        XPath xpath = new DOMXPath( "substring-after('a', '')" );
        String result = (String) xpath.evaluate( doc );
        assertEquals("a", result);
    }    
  
    public void testSubstringAfterFunctionRequiresAtLeastTwoArguments() 
      throws JaxenException {
        
        BaseXPath xpath = new DOMXPath("substring-after('a')");
        
        try {
            xpath.selectNodes(doc);
            fail("Allowed substring-after function with one argument");
        }
        catch (FunctionCallException ex) {
            assertNotNull(ex.getMessage());
        }
        
    }    

    public void testContainsFunctionRequiresAtMostTwoArguments() 
      throws JaxenException {
        
        BaseXPath xpath = new DOMXPath("substring-after('a', 'a', 'a')");
        
        try {
            xpath.selectNodes(doc);
            fail("Allowed substring-after function with three arguments");
        }
        catch (FunctionCallException ex) {
            assertNotNull(ex.getMessage());
        }
        
    }    

}