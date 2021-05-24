import { Component, Input, Output, EventEmitter, OnChanges, SimpleChange,SimpleChanges, OnInit } from '@angular/core';

import { Song } from '../song.model';

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent {
  @Input() song: Song;
  @Output() songClicked: EventEmitter<any> = new EventEmitter();

  addCart() {
    console.log('añadir a la lista');
    this.songClicked.emit(this.song.id);
  }

}

