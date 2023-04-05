import { Component, OnInit } from '@angular/core';
import { LoanOfferRequest } from '../loanofferrequest';
import { LoanProviderService } from '../loan-provider.service';

@Component({
  selector: 'app-loanofferrequest-list',
  templateUrl: './loanofferrequest-list.component.html',
  styleUrls: ['./loanofferrequest-list.component.css']
})
export class LoanOfferRequestListComponent implements OnInit {
  requests!: LoanOfferRequest[];

  constructor(private loanProviderService: LoanProviderService) {
  }

  ngOnInit() {
    this.loanProviderService.getRequests().subscribe(data => {
      this.requests = data;
    });
  }

}
