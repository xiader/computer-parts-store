package com.gmail.sasha.myproject.service.service.aspect;


import com.gmail.sasha.myproject.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class HistoryLogger {
    private static final Logger logger = LogManager.getLogger(HistoryLogger.class);


    @Pointcut("@annotation(com.gmail.sasha.myproject.service.service.aspect.LogIt)")
    private void logAnyMethodWithAnnotation() {
    }

    @Before("logAnyMethodWithAnnotation() && args(userDTO)")
    public void logSomeHistory(UserDTO userDTO) {

        if (userDTO != null) {
            //todo save into audit table
            logger.info("New user has benn registered");
        }


    }
}
