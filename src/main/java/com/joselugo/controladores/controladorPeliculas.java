package com.joselugo.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class controladorPeliculas {

    // Crear un HashMap para almacenar las películas y sus directores
    private static HashMap<String, String> listaPeliculas = new HashMap<>();

    // Constructor: Aquí agregamos las películas y directores al HashMap
    public controladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");
        listaPeliculas.put("Big Hero 6", "Don Hall");
    }

    // Este método devolverá todas las películas y directores
    @GetMapping("/peliculas")
    public String obtenerTodasLasPeliculas() {
        String todasLasPeliculas = "";  // Usamos una cadena de texto simple para el resultado

        // Recorremos el HashMap para obtener cada película y su director
        for (String pelicula : listaPeliculas.keySet()) {
            String director = listaPeliculas.get(pelicula);  // Obtenemos el director
            todasLasPeliculas += "Película: " + pelicula + ", Director: " + director;
        }

        return todasLasPeliculas;  
    }
    
    
    @GetMapping("/peliculas/{nombre}") 
    public String obtenerPeliculaPorNombre(@PathVariable("nombre") String nombre) {
		if(listaPeliculas.get(nombre) == null) {
			
			return "La película no se encuentra en nuestra lista.";
		}
		return "la pelicula:" +"  "+ nombre + " "+ "fue dirigida por:"+" " + listaPeliculas.get(nombre) ;
	}
    
    
    @GetMapping("/peliculas/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable("nombre") String director) {
        String peliculasDirigidas = "";
        System.out.println("Buscando películas dirigidas por: " + director);
        
        // Recorremos el HashMap para buscar las películas del director
        for (String pelicula : listaPeliculas.keySet()) {
            String directorDePelicula = listaPeliculas.get(pelicula);
            System.out.println("Verificando película: " + pelicula + ", Director: " + directorDePelicula);
            
            // Usa equalsIgnoreCase para evitar problemas con mayúsculas/minúsculas
            if (directorDePelicula.equalsIgnoreCase(director)) {
                peliculasDirigidas += "Película: " + pelicula + "<br>";
            }
        }
        
        // Si no encontramos ninguna película para ese director, devolvemos un mensaje
        if (peliculasDirigidas.isEmpty()) {
            return "No contamos con películas con ese director en nuestra lista.";
        }
        
        return "Películas dirigidas por " + director + ":<br>" + peliculasDirigidas;
    }   
}
