import { Component } from '@angular/core';

import { Song } from './song.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Takina';

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

  clickSong(id: number) {
    console.log('song');
    console.log(id);
  }
}