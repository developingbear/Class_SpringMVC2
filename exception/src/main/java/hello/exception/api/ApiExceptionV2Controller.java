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

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 이 값을 붙이지않으면 200 OK가 나가기 때문에 오해가 생길수 있다.
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler]: {}", e.getClass());
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler // method pram에 exception을 적어주면 어노테이션에서는 생략이 가능하다.
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler]: {}", e.getClass());
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler]: {}", e.getClass());
        return new ErrorResult("ex", e.getMessage());
    }

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
