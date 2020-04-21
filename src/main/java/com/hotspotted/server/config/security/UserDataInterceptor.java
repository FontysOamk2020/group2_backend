package com.hotspotted.server.config.security;

import com.hotspotted.server.entity.Student;
import com.hotspotted.server.logic.StudentLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class UserDataInterceptor extends HandlerInterceptorAdapter {
    private final StudentLogic studentLogic;

    @Autowired
    public UserDataInterceptor(StudentLogic studentLogic) {
        this.studentLogic = studentLogic;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        if (isAuthenticated()) {
            Map<String, Object> claims = ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getToken().getClaims();

            Student student = studentLogic.findBySub((String) claims.get("sub")).orElse(new Student());

            student.setSub((String) claims.get("sub"));
            student.setName((String) claims.get("https://hotspotted.com/name"));
            student.setEmail((String) claims.get("https://hotspotted.com/email"));
            student.setNickname((String) claims.get("https://hotspotted.com/nickname"));
            student.setPicture((String) claims.get("https://hotspotted.com/picture"));

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
