package com.tp.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class WebUtils {

    public static void renderString(HttpServletResponse response, String json) {
        try {
            response.getWriter().write(json);
        }catch ( IOException e){
            e.printStackTrace();
        }

    }
}

