import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MovieCardComponent } from '../../components/movie-card/movie-card';
import { MovieService } from '../../services/movie';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movies',
  imports: [CommonModule, FormsModule, MovieCardComponent],
  templateUrl: './movies.html',
  styleUrls: ['./movies.scss'],
  standalone: true,
})
export class MoviesComponent implements OnInit {
  movies: Movie[] = [];
  filteredMovies: Movie[] = [];
  
  genreFilter: string = '';
  yearFilter: string = '';
  sortFilter: string = 'newest';
  searchTerm: string = '';

  genres: string[] = ['Todos los géneros', 'Acción', 'Aventura', 'Comedia', 'Drama', 'Ciencia Ficción', 'Terror', 'Romance', 'Animación'];
  years: number[] = [2023, 2022, 2021, 2020, 2019];

  constructor(private movieService: MovieService) {}

  ngOnInit() {
    this.movies = this.movieService.getMovies();
    this.filteredMovies = [...this.movies];
    this.applyFilters();
  }

  applyFilters() {
    let filtered = this.movieService.filterMovies(
      this.genreFilter === 'Todos los géneros' || !this.genreFilter ? undefined : this.genreFilter,
      this.yearFilter ? parseInt(this.yearFilter) : undefined,
      this.searchTerm
    );

    switch (this.sortFilter) {
      case 'newest':
        filtered.sort((a, b) => b.year - a.year);
        break;
      case 'oldest':
        filtered.sort((a, b) => a.year - b.year);
        break;
      case 'rating':
        filtered.sort((a, b) => b.rating - a.rating);
        break;
      case 'title':
        filtered.sort((a, b) => a.title.localeCompare(b.title));
        break;
    }

    this.filteredMovies = filtered;
  }

  resetFilters() {
    this.genreFilter = '';
    this.yearFilter = '';
    this.sortFilter = 'newest';
    this.searchTerm = '';
    this.applyFilters();
  }

onSearchChange(event: any) {
  this.searchTerm = event.target.value;
  // Usamos setTimeout para dar tiempo a que Angular actualice el modelo
  setTimeout(() => {
    this.applyFilters();
  }, 300);
}
}