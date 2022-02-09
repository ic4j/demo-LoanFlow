import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { LoanOfferRequestListComponent } from './loanofferrequest-list/loanofferrequest-list.component';
import { LoanProviderService } from './loan-provider.service';
import { LoanOfferResponseFormComponent } from './loanofferresponse-form/loanofferresponse-form.component';
import { LoanOfferListComponent } from './loanoffer-list/loanoffer-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoanOfferRequestListComponent,
    LoanOfferResponseFormComponent,
    LoanOfferListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule  
  ],
  providers: [LoanProviderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
