import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router'
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movie-card',
  templateUrl: './movie-card.html',
  styleUrls: ['./movie-card.scss'],
  standalone: true,
  imports: [CommonModule,]
})
export class MovieCardComponent {
  @Input() movie!: Movie;

  constructor(private router: Router) {}

  navigateToDetail() {
    this.router.navigate(['/movie', this.movie.id]);
  }

  getRatingStars(rating: number): string {
    const fullStars = Math.floor(rating);
    const halfStar = rating % 1 >= 0.5;
    return '★'.repeat(fullStars) + (halfStar ? '½' : '');
  }
}
