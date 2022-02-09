package org.ic4j.demos.loanflow.loanprovider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoanProviderControler {
    @Autowired
    private LoanProviderService loanProviderService;

	@GetMapping("/name")
    public String getName() {
        return loanProviderService.getName();
    }

	@GetMapping("/requests")
    public List<LoanOfferRequest> getRequests() {
        return loanProviderService.getRequests();
    }	

    @GetMapping("/offers")
    public List<LoanOffer> getOffers() {
        return loanProviderService.getOffers();
    }   

    @PostMapping("/requests")
    void addOffer(@RequestBody LoanOfferResponse response) {
        loanProviderService.addOffer(response);
    }    
}
