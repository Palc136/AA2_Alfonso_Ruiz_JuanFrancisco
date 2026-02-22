import { Injectable } from '@angular/core';
import { Comment } from '../models/movie';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  getFavorites(): number[] {
    const favorites = localStorage.getItem('movieFavorites');
    return favorites ? JSON.parse(favorites) : [];
  }

  saveFavorites(favorites: number[]): void {
    localStorage.setItem('movieFavorites', JSON.stringify(favorites));
  }

  getComments(): { [movieId: number]: Comment[] } {
    const comments = localStorage.getItem('movieComments');
    return comments ? JSON.parse(comments) : {};
  }

  saveComments(comments: { [movieId: number]: Comment[] }): void {
    localStorage.setItem('movieComments', JSON.stringify(comments));
  }

  addComment(movieId: number, comment: Comment): void {
    const comments = this.getComments();
    if (!comments[movieId]) {
      comments[movieId] = [];
    }
    comments[movieId].push(comment);
    this.saveComments(comments);
  }
}
