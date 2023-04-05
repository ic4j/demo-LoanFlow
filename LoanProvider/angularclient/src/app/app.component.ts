import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { LoanProviderService } from './loan-provider.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title : string = "Loan Provider";

  constructor(private loanProviderService: LoanProviderService) { 
  }
  
  ngOnInit() {
    this.loanProviderService.getName().subscribe(response =>{this.title = response});
  }
}
