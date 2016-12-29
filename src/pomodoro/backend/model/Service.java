package pomodoro.backend.model;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Service{
	
	private Session session;
	private static SessionFactory sessionFactory;
	
	public Service(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure().buildSessionFactory();	
		}
	}
	
	
	public void openConnection(){
		session = sessionFactory.openSession();
		session.beginTransaction();
	}
	
	public void closeConnection(){
		session.close();
	}

	public List<Materia> getTodasMaterias() {
		openConnection();
		Query q = session.createQuery("from Materia");
		List<Materia> allMateria = (List<Materia>) q.list();
		closeConnection();
		return allMateria;
	}

	public List<Assunto> getTodosAssuntosPorMateria(Materia materia) {
		openConnection();
		Query q = session.createQuery("from Assunto where materia = :materia");
		q.setParameter("materia", materia);
		List<Assunto> allAssunto = (List<Assunto>) q.list();
		closeConnection();
		return allAssunto;
	}

	public List<FlashCard> getTodosFlashCardsPorAssunto(Assunto assunto) {
		openConnection();
		Query q = session.createQuery("from FlashCard where assunto = :assunto");
		q.setParameter("assunto", assunto);
		List<FlashCard> allFlashCard = (List<FlashCard>) q.list();
		closeConnection();
		return allFlashCard;
	}

	public List<Ficha> getTodasFichasPorAssunto(Assunto assunto) {
		openConnection();
		Query q = session.createQuery("from Ficha where assunto = :assunto");
		q.setParameter("assunto", assunto);
		List<Ficha> allFichas = (List<Ficha>) q.list();
		closeConnection();
		return allFichas;
	}

	public void saveMateria(Materia materia) {
		openConnection();
		session.save(materia);
		session.getTransaction().commit();
		System.out.println("commitado no banco "+materia.getNome());
		closeConnection();
	}

	public void saveAssunto(Assunto assunto) {
		openConnection();
		session.save(assunto);
		session.getTransaction().commit();
		closeConnection();		
	}

	public void saveFlashCard(FlashCard flashcard) {
		openConnection();
		session.save(flashcard);
		session.getTransaction().commit();
		closeConnection();
		
	}

	public void saveFicha(Ficha ficha) {
		openConnection();
		session.save(ficha);
		session.getTransaction().commit();
		closeConnection();	
	}

}
