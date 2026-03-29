import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MovieCardComponent } from '../../components/movie-card/movie-card';
import { MovieService } from '../../services/movie';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [CommonModule, FormsModule, MovieCardComponent],
  templateUrl: './movies.html', // Asegúrate de que el nombre coincida con tu HTML
  styleUrls: ['./movies.scss']   // Asegúrate de que el nombre coincida con tu SCSS
})
export class MoviesComponent implements OnInit {
  movies: Movie[] = [];
  filteredMovies: Movie[] = [];
  
  genreFilter: string = '';
  yearFilter: string = '';
  sortFilter: string = 'newest';
  searchTerm: string = ''; 

  generos: string[] = ['Acción', 'Drama', 'Comedia', 'Terror', 'Ciencia Ficción']; 
  
  // También para los años si los usas:
  años: number[] = [2026, 2025, 2024, 2023, 2022];

  constructor(private movieService: MovieService) {}

  // ... tus métodos ngOnInit, cargarPeliculas, applyFilters, etc.

  
  ngOnInit() {
    // 1. Llamamos a la función que pide los datos al Backend
    this.cargarPeliculas();
  }

  cargarPeliculas() {
    // 2. Usamos .subscribe() porque los datos ya no son instantáneos
    this.movieService.getMovies().subscribe({
      next: (data) => {
        this.movies = data;
        this.filteredMovies = [...this.movies];
        this.applyFilters(); // Aplicamos filtros una vez que lleguen los datos
        console.log('Películas cargadas desde la base de datos:', data);
      },
      error: (err) => {
        console.error('Error al conectar con el servidor Java:', err);
      }
    });
  }

  applyFilters() {
    // IMPORTANTE: Como el filtrado ahora depende de 'this.movies' que viene de la DB,
    // asegúrate de que movieService.filterMovies use la lista que le pases
    // o haz el filtrado aquí mismo sobre this.movies.
    
    let filtered = [...this.movies];

    // Lógica de filtrado manual (más segura mientras conectas todo)
    if (this.searchTerm) {
      filtered = filtered.filter(m => 
        m.titulo.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }

    if (this.genreFilter && this.genreFilter !== 'Todos los géneros') {
      filtered = filtered.filter(m => m.genero === this.genreFilter);
    }

    if (this.yearFilter) {
      filtered = filtered.filter(m => m.anio === parseInt(this.yearFilter));
    }

    // Tu lógica de ordenamiento (switch sortFilter) sigue igual aquí abajo...
    switch (this.sortFilter) {
      case 'newest': filtered.sort((a, b) => b.anio - a.anio); break;
      case 'oldest': filtered.sort((a, b) => a.anio - b.anio); break;
      case 'rating': filtered.sort((a, b) => b.rating - a.rating); break;
      case 'title': filtered.sort((a, b) => a.titulo.localeCompare(b.titulo)); break;
    }

    this.filteredMovies = filtered;
  }
  
resetFilters() {
    // 1. Reiniciamos los valores de los filtros a su estado inicial
    this.searchTerm = '';
    this.genreFilter = '';
    this.yearFilter = '';
    this.sortFilter = 'newest';

    // 2. Restauramos la lista mostrada con todas las películas originales
    this.filteredMovies = [...this.movies];
    
    // 3. Opcional: Volvemos a aplicar los filtros (que ahora están vacíos) 
    // para asegurar que la vista se refresque correctamente.
    this.applyFilters();
    
    console.log('Filtros reiniciados');
  
     }
}