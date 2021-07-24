package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        //create session
        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        member.setName("choki");
        member.setLoginId("satelites");

        sessionManager.createSession(member, response);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object res = sessionManager.getSession(request);
        Assertions.assertThat(res).isEqualTo(member);

        // expire session
        sessionManager.expire(request);

        Object expiredRes = sessionManager.getSession(request);
        Assertions.assertThat(expiredRes).isNull();
    }

}