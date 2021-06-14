import { Component,Input, Output, EventEmitter, OnChanges, SimpleChange,SimpleChanges, OnInit } from '@angular/core';

import { Lista } from '../lista.model'

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() lista: Lista;
  @Output() listaClicked: EventEmitter<any> = new EventEmitter();

  addCart() {
    console.log('añadir a la lista');
    this.listaClicked.emit(this.lista.id);
  }

}
