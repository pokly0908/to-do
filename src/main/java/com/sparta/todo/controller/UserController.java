package com.sparta.todo.controller;


import com.sparta.todo.dto.LoginRequestDto;
import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.dto.SignupResponseDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
        String successMessage= "회원가입 성공";

        return ResponseEntity.ok().body(new SignupResponseDto(successMessage, HttpStatus.OK));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        userService.login(requestDto, res);
        return "로그인 성공";
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        return userService.getUsers();
    }


}
