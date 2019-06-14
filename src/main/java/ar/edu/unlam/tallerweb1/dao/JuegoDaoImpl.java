package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.Respuesta;
import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("JuegoDao")
public class JuegoDaoImpl implements JuegoDao {
	
	@Inject
    private SessionFactory sessionFactory;
		 
	@Override
	public void guardarJugador(Jugador jugador)
	{
		final Session session = sessionFactory.getCurrentSession();
		session.save(jugador);
	}
	
	
	@Override
	public void guardarPartida(Jugador mij)
	{
		final Session session = sessionFactory.getCurrentSession();
		session.update(mij);
	}

	@Override
	public Jugador estadisticasJugador(Jugador mij) {
		
		final Session session = sessionFactory.getCurrentSession();	
        return (Jugador) session.createCriteria(Jugador.class)
				
				.add(Restrictions.eq("id",mij.getId()) )
				.uniqueResult();
	}

	@Override
	public Pregunta buscarPregunta(Respuesta rta) {
		final Session session = sessionFactory.getCurrentSession();	
		
		Long id = rta.getId();
		Respuesta r = (Respuesta) session.createCriteria(Respuesta.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		
		return r.getPreguntaSiguiente();
	}

	@Override
	public List<Respuesta> buscarRespuestas(Pregunta siguientePregunta) {
		final Session session = sessionFactory.getCurrentSession();	
		
		List<Respuesta> respuestas = session.createCriteria(Respuesta.class)
									.add(Restrictions.eq("pregunta", siguientePregunta))
									.list();
		return respuestas;
	}
}
