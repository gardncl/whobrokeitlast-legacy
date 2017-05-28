import { TestBed, inject } from '@angular/core/testing';

import { ProjectDataService } from './project-data.service';

describe('ProjectDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProjectDataService]
    });
  });

  it('should be created', inject([ProjectDataService], (service: ProjectDataService) => {
    expect(service).toBeTruthy();
  }));
});
