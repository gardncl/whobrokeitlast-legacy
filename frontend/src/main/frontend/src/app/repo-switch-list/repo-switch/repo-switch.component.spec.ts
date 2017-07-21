import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RepoSwitchComponent } from './repo-switch.component';

describe('RepoSwitchComponent', () => {
  let component: RepoSwitchComponent;
  let fixture: ComponentFixture<RepoSwitchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RepoSwitchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RepoSwitchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
