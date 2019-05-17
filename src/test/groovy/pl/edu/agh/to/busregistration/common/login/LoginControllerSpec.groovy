package pl.edu.agh.to.busregistration.common.login

import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification

class LoginControllerSpec extends Specification {

    def loginController = new LoginController()

    def "should redirect user to admin home page when user is admin"() {
        given: "mock authentication with ADMIN authority"
        def adminAuthentication = Stub(Authentication) {
            getAuthorities() >> [new SimpleGrantedAuthority("ADMIN")]
        }

        and: "security context with authentication"
        SecurityContextHolder.getContext().setAuthentication(adminAuthentication)

        when: "handling login"
        def redirection = loginController.handleLogin()

        then: "user is redirected to admin home page"
        redirection == "redirect:admin-home"
    }

    def "should redirect user to default home page when user not have admin role"() {
        given: "mock authentication without ADMIN authority"
        def adminAuthentication = Stub(Authentication) {
            getAuthorities() >> [new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("Other_role")]
        }

        and: "security context with authentication"
        SecurityContextHolder.getContext().setAuthentication(adminAuthentication)

        when: "handling login"
        def redirection = loginController.handleLogin()

        then: "user is redirected to default home page"
        redirection == "redirect:home"
    }
}
