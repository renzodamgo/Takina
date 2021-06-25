import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ShowArtistasComponent } from './show-artistas.component';

describe('ShowArtistasComponent', () => {
  let component: ShowArtistasComponent;
  let fixture: ComponentFixture<ShowArtistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowArtistasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowArtistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
