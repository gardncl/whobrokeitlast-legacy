import { TestBed, inject } from '@angular/core/testing';

import { BrokenBuildDataService } from './broken-build.service';

describe('BrokenBuildDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BrokenBuildDataService]
    });
  });

  it('should be created', inject([BrokenBuildDataService], (service: BrokenBuildDataService) => {
    expect(service).toBeTruthy();
  }));
});
