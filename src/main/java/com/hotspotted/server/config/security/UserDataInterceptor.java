package com.hotspotted.server.config.security;

import com.hotspotted.server.dto.Auth0User;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.logic.Auth0Logic;

import com.hotspotted.server.logic.StudentLogic;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class UserDataInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(UserDataInterceptor.class);
    private final ModelMapper modelMapper = new ModelMapper();
    private final Auth0Logic auth0Logic;
    private final StudentLogic studentLogic;

    @Autowired
    public UserDataInterceptor(Auth0Logic auth0Logic, StudentLogic studentLogic) {
        this.auth0Logic = auth0Logic;
        this.studentLogic = studentLogic;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        if (isAuthenticated()) {
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            Auth0User user = auth0Logic.findUser(jwtAuthenticationToken.getToken().getTokenValue());

            Optional<Student> foundStudent = studentLogic.findBySub(user.getSub());
            Student student = new Student();
            
            if(foundStudent.isPresent()) {
                modelMapper.map(user, foundStudent.get());
                modelMapper.map(foundStudent.get(), student);
            } else {
                student = modelMapper.map(user, Student.class);
            }

            Student savedStudent = studentLogic.createOrUpdate(student);
            request.setAttribute("student", savedStudent);
        }
        return true;
    }

    static boolean isAuthenticated() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                    && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
        } catch (Exception e) {
            return false;
        }
    }
}
