package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Jugador;
import ar.edu.unlam.tallerweb1.modelo.Opcion;
import ar.edu.unlam.tallerweb1.modelo.Respuesta;
import ar.edu.unlam.tallerweb1.modelo.Ruta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioJuego;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorJuego {

	@Inject
	private ServicioJuego servicioJuego;

	Jugador mij = new Jugador();
	
	@RequestMapping("/inicio")
	public ModelAndView comenzar()
			
	{
	 	//Guardar Jugador
		
		mij.setDinero(2);
		mij.setEstres(0);
		mij.setPuntaje(0);
		mij.setRendimiento(0);
		mij.setSocial(0);
		servicioJuego.guardarJugador(mij);
		return new ModelAndView("inicio");
	}
	
	
	
	@RequestMapping(value = "/mostrarRuta", method = RequestMethod.POST)
	public ModelAndView respuesta(@ModelAttribute("respuesta")Respuesta respuesta, HttpServletRequest request) //Requerido )	
	{
		ModelMap modelo = new ModelMap();
	
		//Recibo el objeto respuesta que tenga la ruta asociada a la respuesta elegida por el jugador
		Respuesta objRespuesta=servicioJuego.consultarCodigoRuta(respuesta.getRespuesta());
		
		//Consulto id que contiene la ruta asociada
		Long idRut = objRespuesta.getRuta().getId();
		
		modelo.put("ruta", idRut);
		
		
		return new ModelAndView("juego",modelo);	
	}
	

}