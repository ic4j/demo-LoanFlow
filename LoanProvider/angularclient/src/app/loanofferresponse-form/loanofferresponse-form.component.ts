import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanOfferResponse } from '../loanofferresponse';
import { LoanProviderService } from '../loan-provider.service';

@Component({
  selector: 'app-loanofferresponse-form',
  templateUrl: './loanofferresponse-form.component.html',
  styleUrls: ['./loanofferresponse-form.component.css']
})
export class LoanOfferResponseFormComponent {
  response : LoanOfferResponse;
  
  constructor(private route: ActivatedRoute, 
    private router: Router, 
      private loanProviderService: LoanProviderService) { 
        this.response = new LoanOfferResponse();
      }

      onSubmit() {
        this.loanProviderService.save(this.response).subscribe(result => this.gotoLoanOfferRequestsList());
      }
    
      gotoLoanOfferRequestsList() {
        this.router.navigate(['/requests']);
      }

}
