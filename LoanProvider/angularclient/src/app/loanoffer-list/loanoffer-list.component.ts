import { Component, OnInit } from '@angular/core';
import { LoanProviderService } from '../loan-provider.service';
import { LoanOffer } from '../loanoffer';

@Component({
  selector: 'app-loanoffer-list',
  templateUrl: './loanoffer-list.component.html',
  styleUrls: ['./loanoffer-list.component.css']
})
export class LoanOfferListComponent implements OnInit {
  offers!: LoanOffer[];

  constructor(private loanProviderService: LoanProviderService) {
  }

  ngOnInit() {
    this.loanProviderService.getOffers().subscribe(data => {
      this.offers = data;
    });
  }

}
