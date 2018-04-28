package com.mycompany.hw_tqs;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("currentcurrencies")
@RequestScoped
public class CurrencyWebPathDefiner {
    
    @EJB
   ServiceRequestApi servReqApi;
   
   @GET
   @Produces({ "application/json", "application/xml" })
   //define path depending on currencies
   @Path("{value}:{currency1}to{currency2}")
   public String makeConversion(@PathParam("value") String quantity, @PathParam("currency1") String currency1, @PathParam("currency2") String currency2)
   {
      return String.format(""
        + "<makeConversion>"
            + "<valueNcurrencies>"
                + "%s %s %s"
            + "</valueNcurrencies>"
            + "<answer>"
                + "%s"
            + "</answer>"
        + "</makeConversion>",
          quantity,currency1,currency2, servReqApi.getExchangeRatio(quantity, currency1, currency2));
   }

   @EJB
   WebInterfaceBean wib;

   @GET
   @Produces({ "application/xml", "application/json" })
   public String listCurrencies() {
       String currentCurrencies = "";
       
       for(String sg: wib.getCurrentCurrencies()){
           currentCurrencies = currentCurrencies.concat("<currency>"+sg+"</currency>");
       }
      return String.format("<currencies>"
                              + "%s" 
                           +"</currencies>"
                            , currentCurrencies);
   }
   
}
