import { Component } from '@angular/core';

import { Song } from './song.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'platzi-store';

  items = ['nicolas', 'julian', 'perez'];

  objeto = {};

  songs: Song[] = [
    {
      id: '1',
      image: 'assets/images/im1.jpg',
      title: 'Mr. Hungary',
    },
    {
      id: '2',
      image: 'assets/images/im2.jpg',
      title: 'Si estuviésemos Juntos',
    },
    {
      id: '3',
      image: 'assets/images/im3.jpg',
      title: 'Purified',
    },
  ];

  addItem() {
    this.items.push('nuevo item');
  }

  deleteItem(index: number) {
    this.items.splice(index, 1);
  }

  clickSong(id: number) {
    console.log('song');
    console.log(id);
  }
}