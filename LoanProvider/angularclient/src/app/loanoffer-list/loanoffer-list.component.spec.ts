import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanofferListComponent } from './loanoffer-list.component';

describe('LoanofferListComponent', () => {
  let component: LoanofferListComponent;
  let fixture: ComponentFixture<LoanofferListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoanofferListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanofferListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
