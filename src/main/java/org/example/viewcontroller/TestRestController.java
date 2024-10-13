package org.example.viewcontroller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestRestController {

    @PostMapping("/rest-controller")
    public Dto post_submit(@Valid Dto dto) { //@Valid RestController에서만 동작함......
        log.info("{}", dto);
        return dto;  // JSON 반환
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException e) {
        // 출력결과 : Validation failed for argument [0] in public java.lang.String org.example.viewcontroller.TestController.post_submit(org.example.viewcontroller.Dto): [Field error in object 'dto' on field 'email': rejected value [pleasantckh]; codes [Email.dto.email,Email.email,Email.java.lang.String,Email]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [dto.email,email]; arguments []; default message [email],[Ljakarta.validation.constraints.Pattern$Flag;@1216162c,.*]; default message [이메일형식이 아님]]
        log.error("{}", e.getMessage()); // 로그 전체 출력...
        // 출력결과 : 이메일 형식이 아님
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        Map<String, Object> res = new HashMap<>();
        res.put("error-status", 400);
        res.put("error-message", errorMessage);

        log.error("error: {}", res);

        //서버에서 예외 발생식 http 응답을 200으로 보내면 ajax error에서 값을 받지 못해서 ResponseEntity를 사용해야함....
        return ResponseEntity.badRequest().body(res);
    }
}
