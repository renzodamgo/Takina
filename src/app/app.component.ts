import { Component } from '@angular/core';

import { Lista } from './lista.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Takina';

  listas: Lista[] = [
    {
      id: '1',
      image: 'assets/images/listsong1.jpg',
      title: 'Mr. Hungary',
    },
    {
      id: '2',
      image: 'assets/images/listsong7.jpg',
      title: 'Si estuviésemos Juntos',
    },
    {
      id: '3',
      image: 'assets/images/listsong3.jpg',
      title: 'Purified',
    },
    {
      id: '4',
      image: 'assets/images/listsong5.jfif',
      title: 'Purified',
    },
    {
      id: '5',
      image: 'assets/images/listsong4.jpg',
      title: 'Purified',
    },
    {
      id: '6',
      image: 'assets/images/listsong6.jfif',
      title: 'Purified',
    },
  ];

  clickLista(id: number) {
    console.log('Lista');
    console.log(id);
  }
}