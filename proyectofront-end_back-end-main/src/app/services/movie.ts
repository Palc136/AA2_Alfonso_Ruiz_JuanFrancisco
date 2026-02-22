import { Injectable } from '@angular/core';
import { Movie, CastMember } from '../models/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private moviesData: Movie[] = [
    {
        id: 1,
        title: "Avengers: Endgame",
        year: 2019,
        genre: "Acción",
        rating: 4.8,
        image: "img/portadas/avengers.webp",
        synopsis: "Los Vengadores restantes deben encontrar una manera de recuperar a sus aliados para un enfrentamiento épico con Thanos, el malvado que diezmó el planeta y el universo.",
        cast: [
            { name: "Robert Downey Jr.", character: "Iron Man", image: "img/personajes_actores/Robert(ironman).jpeg" },
            { name: "Chris Evans", character: "Captain America", image: "img/personajes_actores/chris_evans(capitanA).jpeg" },
            { name: "Scarlett Johansson", character: "Black Widow", image: "img/personajes_actores/scarlett(matasha ).jpeg" }
        ]
    },
    {
        id: 2,
        title: "The Batman",
        year: 2022,
        genre: "Acción",
        rating: 4.5,
        image: "img/portadas/batman.jpg",
        synopsis: "Batman explora la corrupción existente en la ciudad de Gotham y el vínculo de esta con su propia familia. Además, entra en conflicto con un asesino en serie conocido como el Acertijo.",
        cast: [
            { name: "Robert Pattinson", character: "Batman", image: "img/personajes_actores/robertP(batman).jpeg" },
            { name: "Zoë Kravitz", character: "Catwoman", image: "img/personajes_actores/Zoe(selenia kyle).jpeg" },
            { name: "Paul Dano", character: "Riddler", image: "img/personajes_actores/Paul(riddler).jpeg" }
        ]
    },
    {
        id: 3,
        title: "Spider-Man: No Way Home",
        year: 2021,
        genre: "Aventura",
        rating: 4.7,
        image: "img/portadas/spiderman.webp",
        synopsis: "Peter Parker desenmascarado ya no puede separar su vida normal de los altos riesgos de ser un súper héroe. Cuando pide ayuda al Doctor Strange, los riesgos se vuelven aún más peligrosos.",
        cast: [
            { name: "Tom Holland", character: "Spider-Man", image: "img/personajes_actores/TomHallan(spiderman).jpeg" },
            { name: "Zendaya", character: "MJ", image: "img/personajes_actores/zendaya(MJ).jpeg" },
            { name: "Benedict Cumberbatch", character: "Doctor Strange", image: "img/personajes_actores/benedict(doctorstange).jpeg" }
        ]
    },
    {
        id: 4,
        title: "Dune",
        year: 2021,
        genre: "Ciencia Ficción",
        rating: 4.3,
        image: "img/portadas/dune.webp",
        synopsis: "Paul Atreides, un joven brillante y talentoso nacido en un gran destino más allá de su comprensión, debe viajar al planeta más peligroso del universo para asegurar el futuro de su familia y su pueblo.",
        cast: [
            { name: "Timothée Chalamet", character: "Paul Atreides", image: "img/personajes_actores/chalament(atreides).jpeg" },
            { name: "Rebecca Ferguson", character: "Lady Jessica", image: "img/personajes_actores/rebecca(leydyatreides).jpeg" },
            { name: "Oscar Isaac", character: "Duke Leto", image: "img/personajes_actores/oscar(dukeatreides).jpeg" }
        ]
    },
    {
        id: 5,
        title: "Top Gun: Maverick",
        year: 2022,
        genre: "Acción",
        rating: 4.6,
        image: "img/portadas/top_gun.webp",
        synopsis: "Después de más de treinta años de servicio como uno de los mejores aviadores de la Marina, Pete 'Maverick' Mitchell está en casa, forzando los límites como un valiente piloto de pruebas.",
        cast: [
            { name: "Tom Cruise", character: "Maverick", image: "img/personajes_actores/Tomcruz(maverick).jpeg" },
            { name: "Miles Teller", character: "Rooster", image: "img/personajes_actores/miles(rooster).jpeg" },
            { name: "Jennifer Connelly", character: "Penny", image: "img/personajes_actores/jennifer(penny).jpeg" }
        ]
    },
    {
        id: 6,
        title: "Black Panther: Wakanda Forever",
        year: 2022,
        genre: "Acción",
        rating: 4.2,
        image: "img/portadas/black_panter.webp",
        synopsis: "La gente de Wakanda lucha para embarcarse en un nuevo capítulo tras la muerte del Rey T'Challa, combinando sus esfuerzos con la guerra que libran bajo el agua de la nación de Talokan.",
        cast: [
            { name: "Letitia Wright", character: "Shuri", image: "img/personajes_actores/letitia(shuri).jpeg" },
            { name: "Lupita Nyong'o", character: "Nakia", image: "img/personajes_actores/lupita(nakia).jpeg" },
            { name: "Danai Gurira", character: "Okoye", image: "img/personajes_actores/danai(okoye).jpeg" }
        ]
    },
    {
        id: 7,
        title: "Everything Everywhere All at Once",
        year: 2022,
        genre: "Comedia",
        rating: 4.9,
        image: "img/portadas/everithing.webp",
        synopsis: "Una inmigrante china anciana se ve envuelta en una aventura loca donde solo ella puede salvar el mundo explorando otros universos que se conectan con las vidas que podría haber llevado.",
        cast: [
            { name: "Michelle Yeoh", character: "Evelyn Wang", image: "img/personajes_actores/michelle(evelyn).jpeg" },
            { name: "Stephanie Hsu", character: "Joy Wang", image: "img/personajes_actores/stephanie(joy wang).jpeg" },
            { name: "Ke Huy Quan", character: "Waymond Wang", image: "img/personajes_actores/kehuyquan(waymond_wang).jpeg" }
        ]
    },
    {
        id: 8,
        title: "The Flash",
        year: 2023,
        genre: "Acción",
        rating: 4.0,
        image: "img/portadas/flash.webp",
        synopsis: "Barry Allen utiliza sus superpoderes para viajar en el tiempo y cambiar los eventos del pasado, pero su intento de salvar a su familia crea un mundo sin superhéroes.",
        cast: [
            { name: "Ezra Miller", character: "The Flash", image: "img/personajes_actores/ezra(flah).jpeg" },
            { name: "Michael Keaton", character: "Batman", image: "img/personajes_actores/michael(brucewayne).jpeg" },
            { name: "Sasha Calle", character: "Supergirl", image: "img/personajes_actores/sasha(karazorel).jpeg" }
        ]
    }
  ];

  getMovies(): Movie[] {
    return this.moviesData;
  }

  getMovieById(id: number): Movie | undefined {
    return this.moviesData.find(movie => movie.id === id);
  }

  filterMovies(genre?: string, year?: number, searchTerm?: string): Movie[] {
    let filtered = [...this.moviesData];

    if (genre && genre !== 'Todos los géneros') {
      filtered = filtered.filter(movie => movie.genre === genre);
    }

    if (year) {
      filtered = filtered.filter(movie => movie.year === year);
    }

    if (searchTerm) {
      filtered = filtered.filter(movie => 
        movie.title.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }

    return filtered;
  }
}
