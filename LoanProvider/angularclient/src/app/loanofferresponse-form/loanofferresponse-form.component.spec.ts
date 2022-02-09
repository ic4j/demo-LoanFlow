import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanOfferResponseFormComponent } from './loanofferresponse-form.component';

describe('LoanOfferResponseFormComponent', () => {
  let component: LoanOfferResponseFormComponent;
  let fixture: ComponentFixture<LoanOfferResponseFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoanOfferResponseFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanOfferResponseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
