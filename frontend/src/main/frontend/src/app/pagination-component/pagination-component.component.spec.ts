import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaginationComponentComponent } from './pagination-component.component';

describe('PaginationComponentComponent', () => {
  let component: PaginationComponentComponent;
  let fixture: ComponentFixture<PaginationComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaginationComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaginationComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
