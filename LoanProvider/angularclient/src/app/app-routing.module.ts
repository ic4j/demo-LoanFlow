import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoanOfferListComponent } from './loanoffer-list/loanoffer-list.component';
import { LoanOfferRequestListComponent } from './loanofferrequest-list/loanofferrequest-list.component';
import { LoanOfferResponseFormComponent } from './loanofferresponse-form/loanofferresponse-form.component';


const routes: Routes = [
  { path: 'requests', component: LoanOfferRequestListComponent },
  { path: 'offers', component: LoanOfferListComponent },
  { path: 'addoffer', component: LoanOfferResponseFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
