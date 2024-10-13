package org.example.viewcontroller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class TestController {

    /**
     * Controller 에서의 return은 화면을 반환 (xxx.html)
     * 그러나, ajax로 요청하면 ajax에서 success의 로직을 수행하게되면서 페이지 이동을 하지 않음.
     * success: function (result) { ... } 에서 result 값이 html 내용 들어있음....
     */

    @GetMapping("/controller")
    public String submit(Dto dto) {
        log.info("{}", dto);
        return "view";  // 타임리프 templates/view.html 로 화면 그림
    }

    @PostMapping("/controller")
    public String post_submit(@Valid Dto dto) { //@Valid RestController에서만 동작...
        log.info("{}", dto);
        return "view";  // 타임리프 templates/view.html 로 화면 그림
    }
}
