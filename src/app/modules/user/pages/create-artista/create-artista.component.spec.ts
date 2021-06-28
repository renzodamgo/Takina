import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateArtistaComponent } from './create-artista.component';

describe('CreateArtistaComponent', () => {
  let component: CreateArtistaComponent;
  let fixture: ComponentFixture<CreateArtistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateArtistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateArtistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
