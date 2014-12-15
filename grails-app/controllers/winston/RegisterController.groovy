package winston

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import java.util.regex.Matcher
import java.util.regex.Pattern

import sk.upjs.winston.WinstonRole
import sk.upjs.winston.WinstonUser
import sk.upjs.winston.WinstonUserWinstonRole;

class RegisterController {
	
	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService

    def index() { 
		if (springSecurityService.isLoggedIn()) {
			redirect(uri: "/")
		}
		else {
			String view = 'register'
			render view: view
		}
	}
	

	def registerUser() {
		String view = 'register'
		println(params)

		String email = params.email
		
		WinstonUser user = WinstonUser.findByUsername(params.email)
		if (user != null) {
			//TODO email already in use
			render view: view
			return
		}
		
		String password = params.password
		String passwordAgain = params.password_again
		if (password != null && !password.equals(passwordAgain)) {
			//TODO passwords do not match
			render view: view
			return
		}
		
		// everything is ok, save new user
    	WinstonUser newUser = new WinstonUser(username: email, enabled: true, password: password)
		newUser.save(flush: true)
		println("USER:${newUser}")
		def userRole = WinstonRole.findByAuthority(WinstonRole.ROLE_USER)
		WinstonUserWinstonRole.create newUser, userRole, true
		
    	render(controller: 'login', view: 'auth')
	}

}
