
package co.edu.unbosque.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Cine;
import co.edu.unbosque.model.Festival;
import co.edu.unbosque.model.Peliculas;
import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.Premio;
import co.edu.unbosque.model.Proyeccion;
import co.edu.unbosque.model.RequestCertamen;
import co.edu.unbosque.model.RequestProyeccion;
import co.edu.unbosque.model.RequestSala;
import co.edu.unbosque.model.ResultReporte1;
import co.edu.unbosque.model.ResultReporte2;
import co.edu.unbosque.model.ResultReporte3;
import co.edu.unbosque.model.ResultReporte4;
import co.edu.unbosque.model.ResultReporte5;
import co.edu.unbosque.model.Tarea;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
public class AllController {

	private MongoConnection mongo = new MongoConnection();

	// peliculas
	@PostMapping(path = "/createMovie", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createMovie(@RequestBody Peliculas newPelicula) {
		mongo.getDatastore().save(newPelicula);
		return new ResponseEntity<String>("Movie created successfully.", HttpStatus.CREATED);
	}

	@GetMapping(path = "/getPeliculas")
	public ResponseEntity<List<Peliculas>> getPeliculas() {
		Query<Peliculas> query = mongo.getDatastore().find(Peliculas.class);
		List<Peliculas> aux = query.iterator().toList();

		if (aux.isEmpty()) {
			return new ResponseEntity<List<Peliculas>>(aux, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Peliculas>>(aux, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = "/deletePelicula")
	public ResponseEntity<String> deletePelicula(@RequestParam String name) {
		Query<Peliculas> q = mongo.getDatastore().find(Peliculas.class);
		Peliculas c = q.filter(Filters.eq("id", name)).first();

		mongo.getDatastore().delete(c);
		return new ResponseEntity<String>("Pelicula deleted succesfully.", HttpStatus.CREATED);
	}

	// proyecciones
	@PostMapping(path = "/createProyeccion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProyeccion(@RequestBody RequestProyeccion proyeccion) {
		Query<Peliculas> q = mongo.getDatastore().find(Peliculas.class);
		Peliculas peliPrincipal = q.filter(Filters.eq("id", proyeccion.getCip())).first();
		if (peliPrincipal != null) {

			if (peliPrincipal.getProyecciones() != null) {
				peliPrincipal.getProyecciones().add(proyeccion.getProyeccion());
			} else {
				peliPrincipal.setProyecciones(new ArrayList<>());
				peliPrincipal.getProyecciones().add(proyeccion.getProyeccion());
			}
			mongo.getDatastore().save(peliPrincipal);
			return new ResponseEntity<String>("Proyeccion created successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("An error has ocurred.", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/deleteProyeccion")
	public ResponseEntity<String> deleteProyeccion(@RequestParam String Cine, @RequestParam int numeroSala,
			@RequestParam LocalDate fecha, @RequestParam String id) {
		Query<Peliculas> q = mongo.getDatastore().find(Peliculas.class);
		Peliculas peliPrincipal = q.filter(Filters.eq("id", id)).first();

		if (peliPrincipal != null) {
			if (peliPrincipal.getProyecciones() != null) {
				for (int i = 0; i < peliPrincipal.getProyecciones().size(); i++) {
					List<Proyeccion> list = peliPrincipal.getProyecciones();
					if (list.get(i).getSala() == numeroSala && list.get(i).getFechaEstreno().equals(fecha)
							&& list.get(i).getCine().equals(Cine)) {
						peliPrincipal.getProyecciones().remove(i);
					}
				}
			}
			mongo.getDatastore().save(peliPrincipal);
			return new ResponseEntity<String>("Proyeccion removed successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Pelicula no encontrada.", HttpStatus.NOT_FOUND);
		}

	}

	// festivales
	@PostMapping(path = "/createFestival", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createFestival(@RequestBody Festival newFestival) {
		mongo.getDatastore().save(newFestival);
		return new ResponseEntity<String>("Festival created successfully.", HttpStatus.CREATED);
	}

	@GetMapping(path = "/getFestivales")
	public ResponseEntity<List<Festival>> getFestivales() {
		Query<Festival> query = mongo.getDatastore().find(Festival.class);
		List<Festival> aux = query.iterator().toList();

		if (aux.isEmpty()) {
			return new ResponseEntity<List<Festival>>(aux, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Festival>>(aux, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = "/deleteFestival")
	public ResponseEntity<String> deleteFestival(@RequestParam String name) {
		Query<Festival> q = mongo.getDatastore().find(Festival.class);
		Festival c = q.filter(Filters.eq("id", name)).first();

		mongo.getDatastore().delete(c);
		return new ResponseEntity<String>("Festival deleted succesfully.", HttpStatus.CREATED);
	}

	// certamenes
	@PostMapping(path = "/createCertamen", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createCertamen(@RequestBody RequestCertamen certamen) {
		Query<Festival> q = mongo.getDatastore().find(Festival.class);
		Festival festPrincipal = q.filter(Filters.eq("id", certamen.getFestival())).first();
		if (festPrincipal != null) {

			if (festPrincipal.getCertamenes() != null) {
				festPrincipal.getCertamenes().add(certamen.getCertamen());
			} else {
				festPrincipal.setCertamenes(new ArrayList<>());
				festPrincipal.getCertamenes().add(certamen.getCertamen());
			}
			mongo.getDatastore().save(festPrincipal);
			return new ResponseEntity<String>("Certamen created successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("An error has ocurred.", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/deleteCertamen")
	public ResponseEntity<String> deleteCertamen(@RequestParam String name, @RequestParam int ano) {
		Query<Festival> q = mongo.getDatastore().find(Festival.class);
		Festival festPrincipal = q.filter(Filters.eq("id", name)).first();

		if (festPrincipal != null) {
			if (festPrincipal.getCertamenes() != null) {
				for (int i = 0; i < festPrincipal.getCertamenes().size(); i++) {
					if (festPrincipal.getCertamenes().get(i).getAno() == ano) {
						festPrincipal.getCertamenes().remove(i);
					}
				}
			}
			mongo.getDatastore().save(festPrincipal);
			return new ResponseEntity<String>("Certamen removed successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Festival no encontrado.", HttpStatus.NOT_FOUND);
		}
	}

	// cines
	@PostMapping(path = "/createCine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createCines(@RequestBody Cine newCines) {
		mongo.getDatastore().save(newCines);
		return new ResponseEntity<String>("Cine created successfully.", HttpStatus.CREATED);
	}

	@GetMapping(path = "/getCine")
	public ResponseEntity<List<Cine>> getCines() {
		Query<Cine> query = mongo.getDatastore().find(Cine.class);
		List<Cine> aux = query.iterator().toList();

		if (aux.isEmpty()) {
			return new ResponseEntity<List<Cine>>(aux, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Cine>>(aux, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = "/deleteCine")
	public ResponseEntity<String> deleteCines(@RequestParam String name) {
		Query<Cine> q = mongo.getDatastore().find(Cine.class);
		Cine c = q.filter(Filters.eq("id", name)).first();

		mongo.getDatastore().delete(c);
		return new ResponseEntity<String>("Cine deleted succesfully.", HttpStatus.CREATED);
	}

	// salas
	@PostMapping(path = "/createSala", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createSala(@RequestBody RequestSala sala) {
		Query<Cine> q = mongo.getDatastore().find(Cine.class);
		Cine cinePrincipal = q.filter(Filters.eq("id", sala.getId())).first();

		if (cinePrincipal != null) {
			if (cinePrincipal.getSalas() != null) {
				cinePrincipal.getSalas().add(sala.getSala());
			} else {
				cinePrincipal.setSalas(new ArrayList<>());
				cinePrincipal.getSalas().add(sala.getSala());
			}
			mongo.getDatastore().save(cinePrincipal);
			return new ResponseEntity<String>("Sala created successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Cine no encontrado.", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/deleteSala")
	public ResponseEntity<String> deleteSala(@RequestParam int numeroSala, @RequestParam String id) {
		Query<Cine> q = mongo.getDatastore().find(Cine.class);
		Cine cinePrincipal = q.filter(Filters.eq("id", id)).first();

		if (cinePrincipal != null) {
			if (cinePrincipal.getSalas() != null) {
				for (int i = 0; i < cinePrincipal.getSalas().size(); i++) {
					if (cinePrincipal.getSalas().get(i).getSala() == numeroSala) {
						cinePrincipal.getSalas().remove(i);
					}
				}
			}
			mongo.getDatastore().save(cinePrincipal);
			return new ResponseEntity<String>("Sala removed successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Cine no encontrado.", HttpStatus.NOT_FOUND);
		}

	}

	// personas
	@PostMapping(path = "/createPersona", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPersona(@RequestBody Persona persona) {

		mongo.getDatastore().save(persona);

		return new ResponseEntity<String>("Person created successfully.", HttpStatus.CREATED);

	}

	@GetMapping(path = "/getPersonas")
	public ResponseEntity<List<Persona>> getPersonas() {
		Query<Persona> query = mongo.getDatastore().find(Persona.class);
		List<Persona> aux = query.iterator().toList();

		if (aux.isEmpty()) {
			return new ResponseEntity<List<Persona>>(aux, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Persona>>(aux, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = "/deletePersona")
	public ResponseEntity<String> deletePersona(@RequestParam String name) {
		Query<Persona> q = mongo.getDatastore().find(Persona.class);
		Persona c = q.filter(Filters.eq("id", name)).first();

		mongo.getDatastore().delete(c);
		return new ResponseEntity<String>("Persona deleted succesfully.", HttpStatus.CREATED);
	}

	// premios
	@PostMapping(path = "/createPremio", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPremio(@RequestBody Premio premio) {
		Query<Persona> q = mongo.getDatastore().find(Persona.class);

		Persona p = q.filter(Filters.eq("id", premio.getNombre_persona())).first();

		if (p != null) {
			if (p.getPremios() != null) {
				p.getPremios().add(premio);
			} else {
				p.setPremios(new ArrayList<>());
				p.getPremios().add(premio);
			}

			mongo.getDatastore().save(p);
			return new ResponseEntity<String>("Premio created successfully.", HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>("Staff not found.", HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping(path = "/deletePremio")
	public ResponseEntity<String> deletePremio(@RequestParam String name, @RequestParam String id) {
		Query<Persona> q = mongo.getDatastore().find(Persona.class);
		Persona c = q.filter(Filters.eq("id", name)).first();

		for (int i = 0; i < c.getPremios().size(); i++) {
			if (c.getPremios().get(i).getId().equals(id)) {
				c.getPremios().remove(i);
			}
		}

		mongo.getDatastore().save(c);
		return new ResponseEntity<String>("Premio removed succesfully.", HttpStatus.CREATED);
	}

	// trabajos
	@PostMapping(path = "/createTrabajo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTrabajo(@RequestBody Tarea newTrabajo) {

		Query<Peliculas> q = mongo.getDatastore().find(Peliculas.class);
		Peliculas p = q.filter(Filters.eq("id", newTrabajo.getCip())).first();

		if (p != null) {
			if (p.getTareas() != null) {
				p.getTareas().add(newTrabajo);
			} else {
				p.setTareas(new ArrayList<>());
				p.getTareas().add(newTrabajo);
			}
			mongo.getDatastore().save(p);
			return new ResponseEntity<String>("Trabajo created successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Movie not found.", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/deleteTrabajo")
	public ResponseEntity<String> deleteTrabajo(@RequestParam String cip, @RequestParam String id) {
		Query<Peliculas> q = mongo.getDatastore().find(Peliculas.class);
		Peliculas c = q.filter(Filters.eq("id", cip)).first();

		for (int i = 0; i < c.getTareas().size(); i++) {
			if (c.getTareas().get(i).getId().equals(id)) {
				c.getTareas().remove(i);
			}

		}
		mongo.getDatastore().save(c);
		return new ResponseEntity<String>("Work deleted succesfully.", HttpStatus.CREATED);
	}

	// reportes
	@GetMapping(path = "/reportePremiosPersona")
	public ResponseEntity<List<ResultReporte1>> reportePremiosPersona() {
		Query<Peliculas> pl = mongo.getDatastore().find(Peliculas.class);
		List<Peliculas> peliculas = pl.iterator().toList();
		Query<Persona> p2 = mongo.getDatastore().find(Persona.class);

		List<Persona> personas = p2.iterator().toList();

		List<ResultReporte1> resultados = new ArrayList<>();

		for (Peliculas pelicula : peliculas) {
			for (Tarea tarea : pelicula.getTareas()) {
				Persona persona = personas.stream().filter(p -> p.getId().equals(tarea.getNombre_Persona())).findFirst()
						.orElse(null);

				if (persona != null) {
					for (Premio premio : persona.getPremios()) {
						if (premio.getCip().equals(pelicula.getId())) {
							ResultReporte1 reporte = new ResultReporte1();
							reporte.setPersona(persona.getId());
							reporte.setPelicula(pelicula.getTitulo());
							reporte.setPremio(premio.getPremio());
							reporte.setFestival(premio.getFestival());
							reporte.setCertamen(premio.getCertamen());

							resultados.add(reporte);
						}
					}
				}
			}
		}
		return new ResponseEntity<List<ResultReporte1>>(resultados, HttpStatus.CREATED);
	}

	@GetMapping(path = "/reportePeliculasDirector")
	public ResponseEntity<List<ResultReporte2>> reportePeliculasDirector() {
		Query<Peliculas> pl = mongo.getDatastore().find(Peliculas.class);
		List<Peliculas> peliculas = pl.iterator().toList();
		Query<Persona> p2 = mongo.getDatastore().find(Persona.class);
		List<Persona> personas = p2.iterator().toList();

		List<ResultReporte2> resultados = new ArrayList<>();

		for (Peliculas pelicula : peliculas) {
			for (Tarea tarea : pelicula.getTareas()) {
				Persona persona = personas.stream().filter(p -> p.getId().equals(tarea.getNombre_Persona())).findFirst()
						.orElse(null);

				if (persona != null && "Director".equals(tarea.getTarea())) {
					ResultReporte2 reporte = resultados.stream().filter(r -> r.getPersona().equals(persona.getId()))
							.findFirst().orElse(null);

					if (reporte == null) {
						reporte = new ResultReporte2(persona.getId());
						resultados.add(reporte);
					}

					reporte.addPelicula(pelicula.getTitulo());
				}
			}
		}
		return new ResponseEntity<List<ResultReporte2>>(resultados, HttpStatus.CREATED);
	}

	@GetMapping(path = "/reporteRecaudacionPromedio")
	public ResponseEntity<List<ResultReporte3>> reporteRecaudacionPromedio() {
		Query<Peliculas> pl = mongo.getDatastore().find(Peliculas.class);
		List<Peliculas> peliculas = pl.iterator().toList();

		Map<String, Map<Integer, List<Integer>>> cineSalaRecaudaciones = new HashMap<>();

		for (Peliculas pelicula : peliculas) {
			for (Proyeccion proyeccion : pelicula.getProyecciones()) {
				String cine = proyeccion.getCine();
				int sala = proyeccion.getSala();

				cineSalaRecaudaciones.putIfAbsent(cine, new HashMap<>());
				cineSalaRecaudaciones.get(cine).putIfAbsent(sala, new ArrayList<>());

				cineSalaRecaudaciones.get(cine).get(sala).add((int) proyeccion.getRecaudacion());
			}
		}

		List<ResultReporte3> resultados = new ArrayList<>();

		for (Map.Entry<String, Map<Integer, List<Integer>>> entryCine : cineSalaRecaudaciones.entrySet()) {
			String cine = entryCine.getKey();
			for (Map.Entry<Integer, List<Integer>> entrySala : entryCine.getValue().entrySet()) {
				int sala = entrySala.getKey();
				List<Integer> recaudaciones = entrySala.getValue();

				double promedio = recaudaciones.stream().mapToInt(Integer::intValue).average().orElse(0.0);

				ResultReporte3 resultado = new ResultReporte3(cine, sala, promedio);
				resultados.add(resultado);
			}
		}

		return new ResponseEntity<>(resultados, HttpStatus.OK);
	}

	@GetMapping(path = "/reporteProyeccionesCiudad")
	public ResponseEntity<List<ResultReporte4>> reporteProyeccionesCiudad() {
		Query<Peliculas> pl = mongo.getDatastore().find(Peliculas.class);
		List<Peliculas> peliculas = pl.iterator().toList();
		Map<String, List<Proyeccion>> ciudadProyecciones = new HashMap<>();

		for (Peliculas pelicula : peliculas) {
			for (Proyeccion proyeccion : pelicula.getProyecciones()) {
				String cineId = proyeccion.getCine();

				Cine cine = mongo.getDatastore().find(Cine.class).filter(Filters.eq("id", cineId)).first();
				if (cine != null) {
					String ciudad = cine.getCiudad();

					ciudadProyecciones.putIfAbsent(ciudad, new ArrayList<>());

					ciudadProyecciones.get(ciudad).add(proyeccion);
				}
			}
		}

		List<ResultReporte4> resultados = new ArrayList<>();

		for (Map.Entry<String, List<Proyeccion>> entry : ciudadProyecciones.entrySet()) {
			String ciudad = entry.getKey();
			List<Proyeccion> proyecciones = entry.getValue();

			ResultReporte4 reporteCiudad = new ResultReporte4(ciudad, proyecciones);
			resultados.add(reporteCiudad);
		}

		return new ResponseEntity<>(resultados, HttpStatus.OK);
	}

	@GetMapping(path = "/reporteProyeccionesCine")
	public ResponseEntity<List<ResultReporte5>> reporteProyeccionesCine(@RequestParam String nombre) {
		Query<Peliculas> pl = mongo.getDatastore().find(Peliculas.class);
		List<Peliculas> peliculas = pl.iterator().toList();

		List<ResultReporte5> reportes = new ArrayList<>();

		for (Peliculas pelicula : peliculas) {
			for (Proyeccion proyeccion : pelicula.getProyecciones()) {
				if (proyeccion.getCine().equals(nombre)) {
					ResultReporte5 reporte = new ResultReporte5(pelicula.getTitulo(), proyeccion.getSala(),
							proyeccion.getFechaEstreno(), proyeccion.getDiasEstreno(), proyeccion.getEspectadores(),
							(int) proyeccion.getRecaudacion());
					reportes.add(reporte);
				}
			}
		}

		return new ResponseEntity<>(reportes, HttpStatus.OK);
	}

}
