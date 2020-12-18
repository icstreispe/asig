package ro.x13.asig;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
//@RequiredArgsConstructor
//@Slf4j
public class UiController {

    @GetMapping(value = "/ui")
    public ResponseEntity<String> hello(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String h = e.nextElement();
            sb.append(h).append(":").append(request.getHeader(h)).append("<br>");
        }

        return ResponseEntity.ok().body(sb.toString());
    }
}
