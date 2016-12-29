package pomodoro.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import pomodoro.backend.model.Assunto;
import pomodoro.backend.model.Ficha;
import pomodoro.backend.model.FlashCard;
import pomodoro.backend.model.Materia;
import pomodoro.backend.model.Service;

@Path("/materia")
public class PomodoroService {

	private static Service service = new Service();
	
	@GET
	@Produces("application/json")
	public Response getTodasMaterias(){
		List<Materia> materias = service.getTodasMaterias();
		Gson gson = new Gson();
		String result = gson.toJson(materias);
		String result2 = "{materias: "+result+"}";
		return Response.status(200).entity(result2).build();
	}
	
	@Path("{d}/assuntos")
	@GET
	@Produces("application/json")
	public Response getTodosAssuntosPorMateria(@PathParam("d") float f){
		
		Materia materiaParaBuscar = null;
		List<Materia> materias = service.getTodasMaterias();
		for (Materia materia : materias) {
			if(materia.getId() == f){
				materiaParaBuscar = materia;
			}
		}
		List<Assunto> assuntos	 = service.getTodosAssuntosPorMateria(materiaParaBuscar);
		Gson gson = new Gson();
		String result = gson.toJson(assuntos);
		String result2 = "{assuntos:" + result+"}";
		return Response.status(200).entity(result2).build();
	}
	
	@Path("{d}/assuntos/{f}/flashcards")
	@GET
	@Produces("application/json")
	public Response getTodosFlashCardsPorAssunto(@PathParam("d") float d, @PathParam("f") float f){
		
		Materia materiaParaBuscar = null;
		
		List<Materia> materias = service.getTodasMaterias();
		for (Materia materia : materias) {
			if(materia.getId() == d){
				materiaParaBuscar = materia;
			}
		}
		
		List<Assunto> assuntos	 = service.getTodosAssuntosPorMateria(materiaParaBuscar);
		Assunto assuntoParaBuscar = null;
		
		for (Assunto assunto : assuntos) {
			if(assunto.getId() == f){
				assuntoParaBuscar = assunto;
			}
		}
		
		List<FlashCard> flashcards = service.getTodosFlashCardsPorAssunto(assuntoParaBuscar);
		Gson gson = new Gson();
		String result = gson.toJson(flashcards);
		String result2 = "{flashcards: " + result +"}";
		return Response.status(200).entity(result2).build();
	}
	
	@Path("{d}/assuntos/{f}/fichas")
	@GET
	@Produces("application/json")
	public Response getTodasFichasPorAssunto(@PathParam("d") float d, @PathParam("f") float f){
		
		Materia materiaParaBuscar = null;
		
		List<Materia> materias = service.getTodasMaterias();
		for (Materia materia : materias) {
			if(materia.getId() == d){
				materiaParaBuscar = materia;
			}
		}
		
		List<Assunto> assuntos	 = service.getTodosAssuntosPorMateria(materiaParaBuscar);
		Assunto assuntoParaBuscar = null;
		
		for (Assunto assunto : assuntos) {
			if(assunto.getId() == f){
				assuntoParaBuscar = assunto;
			}
		}
		
		List<Ficha> fichas = service.getTodasFichasPorAssunto(assuntoParaBuscar);

		Gson gson = new Gson();
		String result = gson.toJson(fichas);
		String result2 = "{fichas: " + result +"}";
		return Response.status(200).entity(result2).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criarMateria(String message){
		Gson gson = new Gson();
		Materia materia = gson.fromJson(message, Materia.class);
		service.saveMateria(materia);
		
		return Response.status(201).entity(message).build();
	}
	
	@POST
	@Path("/assuntos/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criarAssuntoPorMateria(@PathParam("d") float d, String message){
		Gson gson = new Gson();
		Assunto assunto = gson.fromJson(message, Assunto.class);
		service.saveAssunto(assunto);
		return Response.status(201).entity(message).build();
	}
	
	@POST
	@Path("{d}/assuntos/{f}/flashcards/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criarFlashCardPorAssunto(@PathParam("d") float d, @PathParam("f") float f, String message){
		Gson gson = new Gson();
		FlashCard flashcard = gson.fromJson(message, FlashCard.class);
		service.saveFlashCard(flashcard);
		return Response.status(201).entity(message).build();
	}
	
	@POST
	@Path("{d}/assuntos/{f}/fichas/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criarFichaPorAssunto(@PathParam("d") float d, @PathParam("f") float f, String message){
		Gson gson = new Gson();
		Ficha ficha = gson.fromJson(message, Ficha.class);
		service.saveFicha(ficha);
		return Response.status(201).entity(message).build();
	}
}
