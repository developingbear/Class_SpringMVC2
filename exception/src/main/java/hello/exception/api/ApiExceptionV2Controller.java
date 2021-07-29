package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    @GetMapping("/api2/members/{id}")
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
