/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hw_tqs;

import com.mycompany.hw_tqs.WebInterfaceBean;
import com.mycompany.hw_tqs.ServiceRequestApi;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author demo
 */
@RunWith(EasyMockRunner.class)
public class WebInterfaceBeanTest {
//Normal JUnit tests    


    
    /**
    * Test when converting a "non-number" char
    */
    
    @Test
    public void testBadValuesWriteError() {
        WebInterfaceBean wib = new WebInterfaceBean();
        wib.setQuantity("This is not a number");
        wib.onButtonTrigger();
        assertTrue(wib.getDisplayedAnswer().equals("O caracter tem de ser um número"));
    }
    
    /**
    * Test a null submission to converter
    */   
    @Test
    public void testNullSubmision() {
        WebInterfaceBean wib = new WebInterfaceBean();
        wib.setQuantity("");
        wib.onButtonTrigger();
        assertTrue(wib.getDisplayedAnswer().equals("O caracter tem de ser um número"));
    }


    @TestSubject
    WebInterfaceBean wib = new WebInterfaceBean();
    @Mock
    ServiceRequestApi serviceApiRequester;   
    @Test
    public void testMockConversion(){
        EasyMock.expect(serviceApiRequester.getExchangeRatio("10", "DJF","CUP")).andReturn("1.4986144715419953");
        EasyMock.replay(serviceApiRequester);
        
        wib.setOriginalCurrency("DJF");
        wib.setDestinationCurrency("CUP");
        wib.setQuantity("10");
        wib.onButtonTrigger();     
        assertTrue(wib.getDisplayedAnswer().equals("1.4986144715419953"));
    }
    
    
}
