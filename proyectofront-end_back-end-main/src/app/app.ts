import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router'; // Importante para <router-outlet>
import { MovieService } from './services/movie';

// IMPORTA  COMPONENTES 
import { HeaderComponent } from './components/header/header'; 
import { FooterComponent } from './components/footer/footer';

@Component({
  selector: 'app-root',
  standalone: true,
  // 3.  Agrégar a la lista de imports
  imports: [RouterOutlet, HeaderComponent, FooterComponent], 
  templateUrl: './app.html',
  styleUrl: './app.scss' // OJO AQUÍ: Mira el siguiente paso
})
export class AppComponent implements OnInit {
  title = 'cineplus';

  constructor(private movieService: MovieService) {}

  ngOnInit(): void {
    /* //cargar datos de el front end de service/movies.ts a la base de datos my sql conectados al back-end
    this.movieService.migrateToBackend().subscribe({
      next: (res) => {
        console.log('¡Respuesta del servidor Java!', res);
        alert('¡Datos migrados con éxito a MySQL!');
      },
      error: (err) => {
        console.error('Error al conectar con el Backend:', err);
      }
    });*/
  }
}