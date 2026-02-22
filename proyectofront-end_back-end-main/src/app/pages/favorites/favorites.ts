import { Component, OnInit } from '@angular/core';
import { CommonModule,} from '@angular/common';
import { RouterLink } from '@angular/router'
import { MovieCardComponent } from '../../components/movie-card/movie-card';
import { MovieService } from '../../services/movie';
import { StorageService } from '../../services/storage';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.html',
  styleUrls: ['./favorites.scss'],
  standalone: true,
  imports: [CommonModule, MovieCardComponent,RouterLink]
})
export class FavoritesComponent implements OnInit {
  favoriteMovies: Movie[] = [];
  favorites: number[] = [];

  constructor(
    private movieService: MovieService,
    private storageService: StorageService
  ) {}

  ngOnInit() {
    this.loadFavorites();
  }

  loadFavorites() {
    this.favorites = this.storageService.getFavorites();
    const allMovies = this.movieService.getMovies();
    this.favoriteMovies = allMovies.filter(movie => 
      this.favorites.includes(movie.id)
    );
  }

  hasFavorites(): boolean {
    return this.favoriteMovies.length > 0;
  }
}
