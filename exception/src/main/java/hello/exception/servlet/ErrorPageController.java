package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class ErrorPageController {

    @RequestMapping("/error-page/404")//post get 동시처리
    public String errorPage404(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("errorPage 404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")//post get 동시처리
    public String errorPage500(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("errorPage 500");
        return "error-page/500";
    }

}
