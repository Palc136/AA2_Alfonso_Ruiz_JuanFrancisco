
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule,RouterLink} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
  standalone: true,
  imports: [CommonModule, RouterModule,RouterLink]
})
export class HomeComponent {
  constructor() {}
}
