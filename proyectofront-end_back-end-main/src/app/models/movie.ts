export interface CastMember {
  name: string;
  character: string;
  image: string;
}

export interface Movie {
  id: number;
  title: string;
  year: number;
  genre: string;
  rating: number;
  image: string;
  synopsis: string;
  cast: CastMember[];
}

export interface Comment {
  author: string;
  rating: number;
  text: string;
  date: string;
}