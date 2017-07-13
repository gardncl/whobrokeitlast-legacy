import { TestBed, inject } from '@angular/core/testing';

import { RepositoryDataService } from './repository-data.service';

describe('RepositoryDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RepositoryDataService]
    });
  });

  it('should be created', inject([RepositoryDataService], (service: RepositoryDataService) => {
    expect(service).toBeTruthy();
  }));
});
