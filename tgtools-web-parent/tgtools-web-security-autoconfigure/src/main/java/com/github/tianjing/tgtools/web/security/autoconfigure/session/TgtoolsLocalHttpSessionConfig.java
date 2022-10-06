package com.github.tianjing.tgtools.web.security.autoconfigure.session;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSessionRepository;

import java.util.HashMap;

/**
 * @author 田径
 * @date 2019-07-12 20:15
 * @desc
 **/


public class TgtoolsLocalHttpSessionConfig extends AbstractCustomHttpSession {

    @Value("${server.servlet.session.timeout:1800}")
    private int timeout;
    @Bean
    public MapSessionRepository mapSessionRepository() {
        MapSessionRepository vMapSessionRepository = new MapSessionRepository(new HashMap<>());
        vMapSessionRepository.setDefaultMaxInactiveInterval(timeout);
        return vMapSessionRepository;
    }

}
