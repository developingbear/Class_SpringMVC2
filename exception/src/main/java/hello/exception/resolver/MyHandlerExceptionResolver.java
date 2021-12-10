package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
        log.info("call resolver");

        try {
            if (ex instanceof IOException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
//                ModelAndView mv = new ModelAndView();
//                mv.setViewName("error/500"); 여기서 그냥 View를 지정할 수도 있다.!
                return new ModelAndView(); // empty mv를 보냄으로써 error를 그냥 먹어?버리는 느낌
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("resolver ex: {}",e);
        }
        return null;
    }
}
