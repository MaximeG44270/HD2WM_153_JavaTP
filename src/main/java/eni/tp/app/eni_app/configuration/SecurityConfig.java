package eni.tp.app.eni_app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        //SELECT pseudo, password, 1 FROM UTILISTATEUR WHERE pseudo= ? AND password=?
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email, password, 1 FROM membre WHERE email=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, role FROM membre inner join ROLES ON admin=IS_ADMIN WHERE email=?");
        return jdbcUserDetailsManager;
    }
//    @Bean
//    public SecurityFilterChain web(HttpSecurity http) throws Exception {
//
//
//
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                                //permission pour CSS
//                                .requestMatchers("/css/**").permitAll()
//                                .requestMatchers("/image/**").permitAll()
//                                .requestMatchers("/vendor/**").permitAll()
//                                //hasAuthority() permet de gérer un seul et unique ROLE de l'user connecté
//                                .requestMatchers("/Home").permitAll()
//                                .requestMatchers("/ListOfMovies").permitAll()
//                                .requestMatchers("/movie-detail/**").permitAll()
//                                .requestMatchers("/login").permitAll()
//                                .requestMatchers("/profile").permitAll()
//
//                        /*
//                                // ** sert à remplacer {id} de l'url show-aliment/{id}
//                                .requestMatchers("/show-aliment/**").hasAuthority("ROLE_FORMATEUR")
//                                //hasAnyAuthority() permet de gérer plusieurs ROLES de l'user connecté
//                                .requestMatchers("/demo-debug").hasAnyAuthority("ROLE_EMPLOYE", "ROLE_FORMATEUR", "ROLE_ADMIN")
//                                .requestMatchers(HttpMethod.GET, "/show-aliment-form").hasAnyAuthority("ROLE_EMPLOYE", "ROLE_FORMATEUR")
//                                //la méthode requestMatchers est surchargée: elle peut prendre un HttpMethod qui impose un type de requete GET ou POST
//                                //on peut ainsi distinguer des autorisations d'accès sur un même nom d'url accessibles par divers méthodes
//                                .requestMatchers(HttpMethod.POST, "/show-aliment-form").hasAnyAuthority("ROLE_EMPLOYE", "ROLE_FORMATEUR")
//                                .requestMatchers("/make-basket").hasAuthority("ROLE_ADMIN")
//                                .requestMatchers("/make-basket-2").hasAuthority("ROLE_ADMIN")
//                                .requestMatchers("/clear-basket").hasAuthority("ROLE_ADMIN")
//                                //permitAll() permet l'accès à toute personne authentifiée ou non
//                                .requestMatchers("/login").permitAll()
//                                //authenticated() permet l'accès à toute personne authentifiée
//                                .requestMatchers("/logout").authenticated()
//                                //toutes les requêtes commençant par vendor (ex: celles liées aux CSS et JS de uikit) sont autorisées
//                                //ainsi SpringSecurity ne bloque plus le chargement d éléments stockés dans vendor
//                                .requestMatchers("/Vendor/**").permitAll()
//                                .requestMatchers("/css/**").permitAll()
//                                .requestMatchers("/images/**").permitAll()
//                                .requestMatchers("/").permitAll()
//                                //Pour toutes les autres requêtes qui ne sont pas explicitement déclarées
//                                // ci-dessus, alors on peut soit autorisé si authentification via .anyRequest().authenticated()
//                                // ou alors plutôt les rejettées par défaut via:
//                                .anyRequest().denyAll()
//
//                         */
//
//                        // remarque : hasRole("ADMIN") est utilisable en lieu et place de hasAuthority("ROLE_ADMIN")
//                        //idem avec hasAnyRole("EMPLOYE", "FORMATEUR", "ADMIN")) à la place de hasAnyAuthority("ROLE_EMPLOYE", "ROLE_FORMATEUR", "ROLE_ADMIN")
//                );
//
//        http.formLogin(form -> form.loginPage("/login")
//                // permet le retour vers la page url  "" en cas de succès de connexion
//                .defaultSuccessUrl("/Home"));
//
//        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));
//
//        http.logout((logout) ->
//                logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                        .logoutSuccessUrl("/login")
//                        .addLogoutHandler(clearSiteData));
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/image/**").permitAll()
                        .requestMatchers("/vendor/**").permitAll()
                        .requestMatchers("/Home").permitAll()
                        .requestMatchers("/ListOfMovies").permitAll()
                        .requestMatchers("/movie-detail/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/add-movie").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/add-movie/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/profile").authenticated()
                        .anyRequest().denyAll()
                )
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/Home"))
                .logout((logout) ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                .logoutSuccessUrl("/login")
                                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)))
                );

        return http.build();
    }

}
