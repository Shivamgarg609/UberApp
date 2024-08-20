package com.project.uber.UberApp.Advices;



import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
       List<String> allowedRoutes = List.of("/v3/api-docs","/actuator");

       boolean isAlllowed = allowedRoutes
                            .stream()
                            .anyMatch(route-> request.getURI().getPath().contains(route));

      //  if(request.getURI().getPath().contains("/v3/api-docs")) return body;

        if(body instanceof ApiResponse<?>|| isAlllowed) {
            return body;
        }

        return new ApiResponse<>(body);
    }

}
