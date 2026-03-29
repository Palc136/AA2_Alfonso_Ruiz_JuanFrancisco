

import { Injectable } from '@angular/core';
import { Movie } from '../models/movie';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  // La URL de tu Backend (asegúrate de que sea esta)
  private apiUrl = 'http://localhost:8080/juanAlfonsomi-backend/api/movies';

  constructor(private http: HttpClient) { }

  // Obtiene todas las películas desde Java
  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.apiUrl);
  }

  // OPCIONAL: Si tu backend soporta buscar por ID (ej: /api/movies/1)
  // Si no lo soporta, el componente de detalle debe usar getMovies() y filtrar, como hicimos antes.
  getMovieByIdFromBackend(id: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.apiUrl}/${id}`);
  }

  // Esta función envia tus datos locales a la base de datos (solo si la necesitas)
  migrateToBackend(movies: Movie[]) {
    return this.http.post(this.apiUrl, movies);
  }
}