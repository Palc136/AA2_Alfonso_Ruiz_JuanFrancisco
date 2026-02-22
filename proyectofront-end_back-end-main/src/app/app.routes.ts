import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./pages/home/home').then(m => m.HomeComponent)
  },
  {
    path: 'movies',
    loadComponent: () => import('./pages/movies/movies').then(m => m.MoviesComponent)
  },
  {
    path: 'favorites',
    loadComponent: () => import('./pages/favorites/favorites').then(m => m.FavoritesComponent)
  },
  {
    path: 'contacts',
    loadComponent: () => import('./pages/contacts/contacts').then(m => m.ContactsComponent)
  },
  {
    path: 'movie/:id',
    loadComponent: () => import('./components/movie-detail/movie-detail').then(m => m.MovieDetailComponent)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];