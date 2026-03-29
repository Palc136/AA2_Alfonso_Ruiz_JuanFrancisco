import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Movie } from '../../models/movie';
import { Router } from '@angular/router';

@Component({
  
  selector: 'app-movie-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './movie-card.html',
  styleUrls: ['./movie-card.scss']
})
export class MovieCardComponent {
  @Input() movie!: Movie;

  constructor(private router: Router) {}

  navigateToDetail() {
    this.router.navigate(['/movie', this.movie.id]);
  }

  getRatingStars(rating: number): string {
    const val = Math.floor(rating || 0);
    return '★'.repeat(val) + '☆'.repeat(5 - val);
  }

  
}

