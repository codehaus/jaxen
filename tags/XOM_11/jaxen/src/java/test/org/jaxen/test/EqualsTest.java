/*
 * $Header$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2000-2002 bob mcwhirter & James Strachan.
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



package org.jaxen.test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jaxen.JaxenException;
import org.jaxen.dom.DOMXPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

/**
 * <p>
 *  Test for function context.
 * </p>
 * 
 * @author Elliotte Rusty Harold
 * @version 1.1b9
 *
 */
public class EqualsTest extends TestCase
{

    public void testEqualityAgainstNonExistentNodes() 
      throws JaxenException, ParserConfigurationException {
        
        DOMXPath xpath = new DOMXPath("/a/b[c = ../d]");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();
        Element root = doc.createElement("a");
        doc.appendChild(root);
        Element b = doc.createElement("b");
        root.appendChild(b);
        Element c = doc.createElement("c");
        b.appendChild(c);
        
        List result = (List) xpath.evaluate(doc);
        assertEquals(0, result.size());
        
    }
 
    public void testOlander() 
      throws JaxenException, SAXException, IOException, ParserConfigurationException {
        
        DOMXPath xpath = new DOMXPath("//BlockStatement//IfStatement[./Statement =  ./ancestor::BlockStatement/following-sibling::BlockStatement//IfStatement/Statement]");
        
        String data = "<?xml version='1.0'?><Block><BlockStatement><LocalVariableDeclaration><Type><PrimitiveType>boolean</PrimitiveType></Type><VariableDeclarator><VariableDeclaratorId/></VariableDeclarator><VariableDeclarator><VariableDeclaratorId/></VariableDeclarator></LocalVariableDeclaration></BlockStatement><BlockStatement><Statement><IfStatement><Expression><PrimaryExpression><PrimaryPrefix><Name>a</Name></PrimaryPrefix></PrimaryExpression></Expression><Statement><StatementExpression><PrimaryExpression><PrimaryPrefix><Name>methodB</Name></PrimaryPrefix><PrimarySuffix><Arguments/></PrimarySuffix></PrimaryExpression></StatementExpression></Statement></IfStatement></Statement></BlockStatement><BlockStatement><Statement><IfStatement><Expression><PrimaryExpression><PrimaryPrefix><Name>b</Name></PrimaryPrefix></PrimaryExpression></Expression><Statement><StatementExpression><PrimaryExpression><PrimaryPrefix><Name>methodB</Name></PrimaryPrefix><PrimarySuffix><Arguments/></PrimarySuffix></PrimaryExpression></StatementExpression></Statement></IfStatement></Statement></BlockStatement></Block>";
        StringReader reader = new StringReader(data);
        InputSource in = new InputSource(reader);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().parse(in);
        
        List result = (List) xpath.evaluate(doc);
        assertEquals(1, result.size());
        
    }
 
}