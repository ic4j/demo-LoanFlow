import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoanOfferRequest } from './loanofferrequest';
import { LoanOfferResponse } from './loanofferresponse';
import { Observable } from 'rxjs';
import { LoanOffer } from './loanoffer';


@Injectable()
export class LoanProviderService{
  private requestsUrl: string;

  constructor(private http: HttpClient) { 
    this.requestsUrl = 'http://localhost:8080';
  }

  public getName() : Observable<string> {
    return this.http.get(this.requestsUrl + '/name',{responseType: 'text'});
  }

  public getRequests(): Observable<LoanOfferRequest[]> {
    return this.http.get<LoanOfferRequest[]>(this.requestsUrl + '/requests');
  }

  public getOffers(): Observable<LoanOffer[]> {
    return this.http.get<LoanOffer[]>(this.requestsUrl + '/offers');
  }  

  public save(response: LoanOfferResponse) {
    return this.http.post<LoanOfferResponse>(this.requestsUrl + '/requests', response);
  }  
}
