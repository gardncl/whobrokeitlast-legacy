import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectListHeaderComponent } from './project-list-header.component';

describe('ProjectListHeaderComponent', () => {
  let component: ProjectListHeaderComponent;
  let fixture: ComponentFixture<ProjectListHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectListHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectListHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
