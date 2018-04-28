package com.mycompany.hw_tqs;

/**
 *
 * @author carlos
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.ejb.Singleton;


@ManagedBean(name = "webInt", eager = true)
@SessionScoped
@Singleton
public class WebInterfaceBean implements Serializable{
    
   private String quantity;
   private String originalCurrency;
   private String destinationCurrency;  
   private String displayedAnswer; 
   private List<String> currentCurrencies = new ArrayList<>();
   private final ServiceRequestApi serviceRequester;
   
   
   public WebInterfaceBean(){
       displayedAnswer = " ";
       currentCurrencies = new ArrayList<>();
       serviceRequester = new ServiceRequestApi();
   }

   
   public List<String> getCurrentCurrencies() {
      if(currentCurrencies.isEmpty()) 
          setAllCurrencies();
      return currentCurrencies;
   }
   
   public void setAllCurrencies(){
        currentCurrencies = serviceRequester.getCurrencies();
   }
   
    public String getDisplayedAnswer() {
        return displayedAnswer;
    }
   
    public String getQuantity() {
        return quantity;
    }
   
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
   
    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }
   
   public String onButtonTrigger() {
       if(!quantity.matches("[0-9]+(\\.[0-9]+)?")){
           displayedAnswer = "O caracter tem de ser um número";
       }
       else{
           displayedAnswer = serviceRequester.getExchangeRatio(quantity,originalCurrency,destinationCurrency);
       }
        return "index.xhtml";
    }


   
}