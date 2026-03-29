import { Component, OnInit } from '@angular/core';
import { CommonModule,} from '@angular/common';
import { RouterLink } from '@angular/router'
import { MovieCardComponent } from '../../components/movie-card/movie-card';
import { MovieService } from '../../services/movie';
import { StorageService } from '../../services/storage';
import { Movie } from '../../models/movie';
import { map } from 'rxjs/operators'; // 1. Importa map
import { Observable } from 'rxjs';

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
  // 1. Obtenemos los IDs de favoritos del localStorage
  this.favorites = this.storageService.getFavorites();

  // 2. Nos suscribimos al servicio para obtener las películas reales de Java
  this.movieService.getMovies().subscribe({
    next: (allMovies) => {
      // 3. Ahora que los datos LLEGARON, podemos usar .filter de Array
      this.favoriteMovies = allMovies.filter(movie => 
        this.favorites.includes(movie.id)
      );
      console.log('Favoritos cargados:', this.favoriteMovies);
    },
    error: (err) => {
      console.error('Error al cargar favoritos desde el servidor:', err);
    }
  });
}

  hasFavorites(): boolean {
    return this.favoriteMovies.length > 0;
  }
}
