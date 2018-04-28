/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hw_tqs;

import com.mycompany.hw_tqs.CurrencyApplication;
import com.mycompany.hw_tqs.CurrencyWPD;
import com.mycompany.hw_tqs.WebInterfaceBean;
import com.mycompany.hw_tqs.ServiceRequestApi;
import java.io.File;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(Arquillian.class)
public class CurrencyWPD {
    private WebTarget target1;
    private WebTarget target2;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve()
                .withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(CurrencyApplication.class, CurrencyWPD.class,
                        ServiceRequestApi.class, WebInterfaceBean.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml")
                .addAsLibraries(files);
    }
    
    @ArquillianResource
    private URL base;


    @Before
    public void setUp() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target1 = client.target(URI.create(new URL(base, "REST/currentcurrencies").toExternalForm()));
        target1.register(ServiceRequestApi.class);
        target2 = client.target(URI.create(new URL(base, "REST/currentcurrencies/1:USDtoUSD").toExternalForm()));
        target2.register(ServiceRequestApi.class);
    }
      

    /**
     * Test of calculate method, of class CurrencyWebPathDefiner.
     */
    @Test @InSequence(1)
    public void testConvert() {
        Response resp = target2.request().get();
        resp.bufferEntity();
        String s = resp.readEntity(String.class);
        String[] sAnswers = s.split("<answer>");
        //check for eur
        assertTrue(sAnswers[107].equals("1.2131446650750208"));
        
        resp.close();
    }
    
    /**
     * Test of listCurrencies method, of class CurrencyWebPathDefiner.
     */
    @Test @InSequence(2)
    public void testListCurrencies() {
        final int NCURRS = 168;
        Response resp = target1.request().get();
        resp.bufferEntity();
        String s = resp.readEntity(String.class);
 
        String[] sA = s.split("</currency>");
        //check if all
        assertTrue(sA.length == NCURRS);
        resp.close();
        
    }
    
}
