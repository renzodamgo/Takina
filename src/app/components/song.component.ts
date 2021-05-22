import { Component, Input, Output, EventEmitter, OnChanges, SimpleChange,SimpleChanges, OnInit } from '@angular/core';

import { Song } from '../song.model';

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.scss']
})
export class SongComponent implements OnChanges, OnInit {

  @Input() song: Song;
  @Output() songClicked: EventEmitter<any> = new EventEmitter();

  constructor(){
    console.log('1.constructor');
  }
//cada vez que se haga cambios en algun dato del objeto
  ngOnChanges(changes: SimpleChanges){
    console.log('2.ngOnChanges');
    console.log(changes);
  }
//Cuando el componente ya fue puesto en pantalla,
//se ejecuta este metodo
  ngOnInit(){
    console.log('3.ngOnInit');
  }
//Este es lo mismo que ngOnChanges, pero en este caso
// tu lo haces manual
  // ngDoCheck(){
  //   console.log('4.ngDoCheck');
  // }

  ngOnDestroy(){
    console.log('5.ngOnDestroy');
  }

  addCart() {
    console.log('añadir al carrito');
    this.songClicked.emit(this.song.id);
  }

}
