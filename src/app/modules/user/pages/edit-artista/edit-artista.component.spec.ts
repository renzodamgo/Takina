import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditArtistaComponent } from './edit-artista.component';

describe('EditArtistaComponent', () => {
  let component: EditArtistaComponent;
  let fixture: ComponentFixture<EditArtistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditArtistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditArtistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
