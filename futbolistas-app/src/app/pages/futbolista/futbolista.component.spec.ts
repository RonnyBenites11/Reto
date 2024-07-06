import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FutbolistaComponent } from './futbolista.component';

describe('FutbolistaComponent', () => {
  let component: FutbolistaComponent;
  let fixture: ComponentFixture<FutbolistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FutbolistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FutbolistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
