import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../../services/movie';
import { StorageService } from '../../services/storage';
import { Movie, Comment } from '../../models/movie';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.html',
  styleUrls: ['./movie-detail.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class MovieDetailComponent implements OnInit {
  movie!: Movie;
  comments: Comment[] = [];
  favorites: number[] = [];

  newComment = {
    author: '',
    rating: 0,
    text: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private movieService: MovieService,
    private storageService: StorageService
  ) {}

  ngOnInit() {
    const movieId = Number(this.route.snapshot.paramMap.get('id'));
    this.movie = this.movieService.getMovieById(movieId)!;
    this.favorites = this.storageService.getFavorites();
    this.loadComments();
  }

  loadComments() {
    const allComments = this.storageService.getComments();
    this.comments = allComments[this.movie.id] || [];
  }

  addComment() {
    if (this.newComment.author && this.newComment.rating && this.newComment.text) {
      const comment: Comment = {
        author: this.newComment.author,
        rating: this.newComment.rating,
        text: this.newComment.text,
        date: new Date().toLocaleDateString('es-ES')
      };

      this.storageService.addComment(this.movie.id, comment);
      this.loadComments();

      // Reset form
      this.newComment = { author: '', rating: 0, text: '' };
    }
  }

  toggleFavorite() {
    const index = this.favorites.indexOf(this.movie.id);
    if (index === -1) {
      this.favorites.push(this.movie.id);
    } else {
      this.favorites.splice(index, 1);
    }
    this.storageService.saveFavorites(this.favorites);
  }

  isFavorite(): boolean {
    return this.favorites.includes(this.movie.id);
  }

  getRatingStars(rating: number): string {
    const fullStars = Math.floor(rating);
    const halfStar = rating % 1 >= 0.5;
    return '★'.repeat(fullStars) + (halfStar ? '½' : '');
  }

  goBack() {
    this.router.navigate(['/movies']);
  }
}
