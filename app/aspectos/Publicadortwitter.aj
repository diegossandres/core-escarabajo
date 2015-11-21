package aspectos;


import models.Recorrido;
import models.User;
import play.Logger;
import play.libs.EventSource;
import play.mvc.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controllers.Application;
import controllers.ControllerRetos;



public aspect Publicadortwitter{
	pointcut postwitter(Recorrido recorrido):
	 call(* ControllerMetricas.templateRecorridoWeb(..)) && args(recorrido);
	
 
Object around(Recorrido recorrido):postwitter(recorrido){
	
	System.out.println("esta entrando en el aspecto");
	return ok(views.html.ejecucionRecorrido.render(recorrido ,views.html.publicadorfacebook.render("publicador"), views.html.publicadortwitter.render("publicador")));	
	


 }
 }
	
	
	/*	pointcut postwitter(): 
		call(* ChatApplication.index());
	
	after() : postwitter(){
		
		System.out.println("Valor impuestos ");
	}

}
*/
//around() : postwitter(){
//return ok(views.html.chat.render("Sistema de Mensajeria Instantanea", localUser,views.html.publicadorfacebook.render("publicador"),views.html.publicadortwitter.render("publicador")));
//}
//}