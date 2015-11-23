import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.PlayAuthenticate.Resolver;
import com.feth.play.module.pa.exceptions.AccessDeniedException;
import com.feth.play.module.pa.exceptions.AuthException;
import com.feth.play.module.pa.user.AuthUser;
import com.feth.play.module.pa.user.EmailIdentity;
import com.feth.play.module.pa.user.NameIdentity;

import controllers.routes;
import database.MetricaDAO;
import models.LinkedAccount;
import models.Metrica;
import models.SecurityRole;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.db.DB;
import play.mvc.Call;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import providers.MyUsernamePasswordAuthUser;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		PlayAuthenticate.setResolver(new Resolver() {

			@Override
			public Call login() {
				// Your login page
				return routes.Application.login();
			}

			@Override
			public Call afterAuth() {
				// The user will be redirected to this page after authentication
				// if no original URL was saved
				return routes.Application.index();
			}

			@Override
			public Call afterLogout() {
				return routes.Application.index();
			}

			@Override
			public Call auth(final String provider) {
				// You can provide your own authentication implementation,
				// however the default should be sufficient for most cases
				return com.feth.play.module.pa.controllers.routes.Authenticate
						.authenticate(provider);
			}

			@Override
			public Call askMerge() {
				return routes.Account.askMerge();
			}

			@Override
			public Call askLink() {
				return routes.Account.askLink();
			}

			@Override
			public Call onException(final AuthException e) {
				if (e instanceof AccessDeniedException) {
					return routes.Signup
							.oAuthDenied(((AccessDeniedException) e)
									.getProviderKey());
				}

				// more custom problem handling here...
				return super.onException(e);
			}
		});

		initialData();
	}

	/**
	 * 
	 */
	private void initialData() {
		if (SecurityRole.find.findRowCount() == 0) {
			for (final String roleName : Arrays.asList(controllers.Application.USER_ROLE, controllers.Application.ADMIN_ROLE)) {
				final SecurityRole role = new SecurityRole();
				role.roleName = roleName;
				role.save();
			}
			
		}
		//Crea el primer admin si no hay admins creados
		if (contarAdmins()==0)
		{
			crearAdmin();
		}
		
		preReports();
		
		MetricaDAO metricaDAO = new MetricaDAO();
		
		//Distancia Real
    	if(metricaDAO.consultarMetricaPorNombre("Distancia Real").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Distancia Real");
    		m.setUnidadMedida("Km");
    		metricaDAO.agregarMetrica(m);
    	}
		
    	//Tiempo Real
    	if(metricaDAO.consultarMetricaPorNombre("Tiempo Real").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Tiempo Real");
    		m.setUnidadMedida("min");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Velocidad Media
    	if(metricaDAO.consultarMetricaPorNombre("Velocidad Media").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Velocidad Media");
    		m.setUnidadMedida("Km/h");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Distancia Estimada
    	if(metricaDAO.consultarMetricaPorNombre("Distancia Estimada").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Distancia Estimada");
    		m.setUnidadMedida("Km");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Tiempo Estimado
    	if(metricaDAO.consultarMetricaPorNombre("Tiempo Estimado").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Tiempo Estimado");
    		m.setUnidadMedida("min");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Temperatura Origen
    	if(metricaDAO.consultarMetricaPorNombre("Clima Temperatura Origen").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Temperatura Origen");
    		m.setUnidadMedida("°C");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Temperatura Destino
    	if(metricaDAO.consultarMetricaPorNombre("Clima Temperatura Destino").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Temperatura Destino");
    		m.setUnidadMedida("°C");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Humedad Origen
    	if(metricaDAO.consultarMetricaPorNombre("Clima Humedad Origen").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Humedad Origen");
    		m.setUnidadMedida("%");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Humedad Destino
    	if(metricaDAO.consultarMetricaPorNombre("Clima Humedad Destino").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Humedad Destino");
    		m.setUnidadMedida("%");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Nubosidad Origen
    	if(metricaDAO.consultarMetricaPorNombre("Clima Nubosidad Origen").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Nubosidad Origen");
    		m.setUnidadMedida("%");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Humedad Destino
    	if(metricaDAO.consultarMetricaPorNombre("Clima Nubosidad Destino").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Nubosidad Destino");
    		m.setUnidadMedida("%");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Viento Origen
    	if(metricaDAO.consultarMetricaPorNombre("Clima Viento Origen").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Viento Origen");
    		m.setUnidadMedida("m/s");
    		metricaDAO.agregarMetrica(m);
    	}
    	
    	//Clima Humedad Destino
    	if(metricaDAO.consultarMetricaPorNombre("Clima Viento Destino").size() <= 0){
    		Metrica m = new Metrica();
    		m.setNombreMetrica("Clima Viento Destino");
    		m.setUnidadMedida("m/s");
    		metricaDAO.agregarMetrica(m);
    	}
		
	}
	
	/**
	 * 
	 */
	private void crearAdmin(){
		
		//PARAMETROS
		
		String defaultAdminEmail= "da.salinas3247@uniandes.edu.co";
		String defaultAdminName = "Default Adminsitrator";
		String defaultAdminPassword = "123456";
		
		//SIGN UP SIMULADO
		MySignup ms = new MySignup();
		ms.email = defaultAdminEmail;
		ms.name = defaultAdminName;
		ms.password = defaultAdminPassword;
		ms.repeatPassword = defaultAdminPassword;
			
		//My AUTH USER para linkear una cuenta al usuario admin
		AuthUser au = new MyUsernamePasswordAuthUser(ms);

		//Creación del usuario con rol de admin
		final User user = new User();
		user.roles = Collections.singletonList(SecurityRole
				.findByRoleName(controllers.Application.ADMIN_ROLE));
		user.active = true;
		user.lastLogin = new Date();
		user.linkedAccounts = Collections.singletonList(LinkedAccount
				.create(au));
		
		//UsernamePasswordAuthUser implementa esto
		if (au instanceof EmailIdentity) {
			final EmailIdentity identity = (EmailIdentity) au;
			// Remember, even when getting them from FB & Co., emails should be
			// verified within the application as a security breach there might
			// break your security as well!
			user.email = identity.getEmail();
			if (contarAdmins()==0)
			{
				//El primer admin lo damos por verificado
				user.emailValidated = true;
			}
			else
			{
				//Los demas requeiren verificacion en correo
				user.emailValidated = false;
			}
		}
		
		//MyUsernamePasswordAuthUser implementa esto
		if (au instanceof NameIdentity) {
			final NameIdentity identity = (NameIdentity) au;
			final String name = identity.getName();
			if (name != null) {
				user.name = name;
			}
		}
		
		user.save();
		// Ebean.saveManyToManyAssociations(user, "roles");
		// Ebean.saveManyToManyAssociations(user, "permissions");
		
	}
	
	/**
	 * 
	 * @return
	 */
	private static int contarAdmins()
	{
		int cont= 0;
		
		SecurityRole adminRole = SecurityRole.findByRoleName(controllers.Application.ADMIN_ROLE);
		
		List<User> admins = User.find.where().eq("roles.id", adminRole.id).findList();
		
		if (admins.size()>0)
		{
			System.out.println("Numero de Admins="+admins.size());
			
			for (int i=0; i< admins.size();i++) {
				User adm = admins.get(i);
				System.out.println("Admin "+i+"= "+" Email: "+adm.email+" Name: "+adm.name);
			}
		}
		else
		{
			System.out.println("No hay administradores, Se creara una admin por defecto");
		}
		cont = admins.size();
		
		
		return cont;
	}
	
	public static void preReports(){
		
		try{			
			Connection conn = DB.getConnection();
			PreparedStatement stmt = null;
			
			stmt = conn.prepareStatement("create extension tablefunc");
			stmt.executeUpdate();
			System.out.println("Ejecuta tablefunc");
			
			conn.close();
		}
		catch(Exception e){
			System.out.println("tablefunc ya existe!!");
		}
		
	}
	
}