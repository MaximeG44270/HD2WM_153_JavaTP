package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Member;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class IDAOAuthMock implements IDAOAuth {

    List<Member> members = Arrays.asList(
            new Member("real-madrid@gmail.com", "15"),
            new Member("halamadrid@gmail.com", "KB9")
    );

    @Override
    public Member login(String email, String password) {
        Member foundMember = members.stream().filter(
                        member -> member.email.equals(email) && member.password.equals(password))
                .findFirst().orElse(null);

        return foundMember;
    }
}
