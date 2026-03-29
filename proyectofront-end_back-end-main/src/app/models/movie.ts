export interface CastMember {
  name: string;
  character: string;
  image: string;
}


// Tu interfaz Movie actualizada
export interface Movie {
  id: number;
  titulo: string;
  anio: number;
  genero: any;
  rating: number;
  imagenPortada: string; 
  sinopsis: string;
  reparto_pelicula: any[];   
}

export interface Comment {
  author: string;
  rating: number;
  text: string;
  date: string;
}