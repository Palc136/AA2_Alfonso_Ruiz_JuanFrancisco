import { Component, OnInit, ChangeDetectorRef, NgZone } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
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
  movie?: Movie;
  comments: Comment[] = [];
  favorites: number[] = [];
  newComment = { author: '', rating: 0, text: '' };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private movieService: MovieService,
    private storageService: StorageService,
    private location: Location,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone
  ) {}

  ngOnInit() {
    const idStr = this.route.snapshot.paramMap.get('id');
    const id = Number(idStr);

    this.favorites = this.storageService.getFavorites() || [];

    this.movieService.getMovies().subscribe({
      next: (moviesFromJava) => {
        // ngZone.run garantiza que Angular detecte el cambio y actualice la vista
        this.ngZone.run(() => {
          this.movie = moviesFromJava.find(m => Number(m.id) === id);
          if (this.movie) {
            console.log("¡Datos de Java cargados!", this.movie);
            this.loadComments();
            this.cdr.detectChanges();
          } else {
            console.warn(`Película id=${id} no encontrada. IDs disponibles:`, moviesFromJava.map(m => m.id));
          }
        });
      },
      error: (err) => console.error("Error conectando con Java:", err)
    });
  }

  loadComments() {
    if (this.movie) {
      const allComments = this.storageService.getComments();
      this.comments = allComments[this.movie.id] || [];
    }
  }

  addComment() {
    if (this.movie && this.newComment.author && this.newComment.rating && this.newComment.text) {
      const comment = {
        author: this.newComment.author,
        rating: Number(this.newComment.rating),
        text: this.newComment.text,
        date: new Date().toLocaleDateString('es-ES')
      };
      this.storageService.addComment(this.movie.id, comment);
      this.loadComments();
      this.newComment = { author: '', rating: 0, text: '' };
    }
  }

  toggleFavorite() {
    if (this.movie) {
      const index = this.favorites.indexOf(this.movie.id);
      if (index === -1) {
        this.favorites.push(this.movie.id);
      } else {
        this.favorites.splice(index, 1);
      }
      this.storageService.saveFavorites(this.favorites);
    }
  }

  isFavorite(): boolean {
    return this.movie ? this.favorites.includes(this.movie.id) : false;
  }

  getRatingStars(rating: any = 0): string {
    const val = Math.floor(Number(rating));
    return '★'.repeat(val) + '☆'.repeat(5 - val);
  }

  goBack() {
    this.location.back();
  }
}
