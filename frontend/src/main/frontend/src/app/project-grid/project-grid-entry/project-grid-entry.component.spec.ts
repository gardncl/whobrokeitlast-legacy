import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectGridEntryComponent } from './project-grid-entry.component';

describe('ProjectGridEntryComponent', () => {
  let component: ProjectGridEntryComponent;
  let fixture: ComponentFixture<ProjectGridEntryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectGridEntryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectGridEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
