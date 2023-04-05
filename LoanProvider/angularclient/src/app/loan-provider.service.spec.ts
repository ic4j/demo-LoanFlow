import { TestBed } from '@angular/core/testing';

import { LoanProviderService } from './loan-provider.service';

describe('LoanServiceService', () => {
  let service: LoanProviderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanProviderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
