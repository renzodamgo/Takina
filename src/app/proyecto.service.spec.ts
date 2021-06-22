import { TestBed } from '@angular/core/testing';

import { ProyectoService } from './core/services/proyecto.service';

describe('ProyectoService', () => {
  let service: ProyectoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProyectoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
