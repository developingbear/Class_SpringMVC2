package hello.exception.api;

import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionV3Controller {

    @GetMapping("/api3/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {


        if (id.equals("ex")) {
            throw new RuntimeException("wrong user");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("bad user request");
        }
        if (id.equals("user-ex")) {
            throw new UserException("user error");
        }
        return new MemberDto(id, "choki");
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

}
