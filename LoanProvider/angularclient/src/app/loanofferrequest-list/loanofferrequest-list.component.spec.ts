import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanOfferRequestListComponent } from './loanofferrequest-list.component';

describe('LoanOfferRequestListComponent', () => {
  let component: LoanOfferRequestListComponent;
  let fixture: ComponentFixture<LoanOfferRequestListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoanOfferRequestListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanOfferRequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
