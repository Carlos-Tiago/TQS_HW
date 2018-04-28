/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hw_tqs;

import com.mycompany.hw_tqs.ServiceRequestApi;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author demo
 */
public class ServiceRequestApiTest {
    
    public ServiceRequestApiTest() {
    }

    /**
     * Test of getExchangeRatio method, of class ServiceRequestApi.
     */
    @Test
    public void testExchangeRatio() {
        ServiceRequestApi servreq = new ServiceRequestApi();
        String answer = servreq.getExchangeRatio("5", "LAK", "MXN");
        assertTrue(answer.equals("0.011213714726577506"));
    }
    
    /**
     * Test of getCurrencies method, of class ServiceRequestApi.
     */
    @Test
    public void testGetCurrencies() {
        ServiceRequestApi servreq = new ServiceRequestApi();
        List<String> currencies = servreq.getCurrencies();
        assertTrue("Verifying if all currencies were fetched",currencies.size()==168);
    }
    
}
