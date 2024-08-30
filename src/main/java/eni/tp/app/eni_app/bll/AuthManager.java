package eni.tp.app.eni_app.bll;

import eni.tp.app.eni_app.bo.Member;
import eni.tp.app.eni_app.dao.IDAOAuth;
import eni.tp.app.eni_app.dao.IDAOAuthMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager {

    @Autowired
    IDAOAuth daoAuth;

    public ManagerResponse<Member> authenticate(String email, String password) {
        Member foundMember = daoAuth.login(email, password);


        // Si couple email/password incorrect erreur code 756
        if(foundMember == null) {
            return ManagerResponse.performResponse("756", "Incorrect email/password combination.", null);
        }

        //// Sinon code 200
        return ManagerResponse.performResponse("200", "You are successfully connected.", null);
    }
}
