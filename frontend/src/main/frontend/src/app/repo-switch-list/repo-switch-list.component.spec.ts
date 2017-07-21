import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RepoSwitchListComponent } from './repo-switch-list.component';

describe('RepoSwitchListComponent', () => {
  let component: RepoSwitchListComponent;
  let fixture: ComponentFixture<RepoSwitchListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RepoSwitchListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RepoSwitchListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
