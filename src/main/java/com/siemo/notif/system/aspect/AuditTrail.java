package com.siemo.notif.system.aspect;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.siemo.notif.system.model.CredentialToken;
import com.siemo.notif.system.repository.RepositoryAuthToken;



@Aspect
@Qualifier("auditTrail")
public class AuditTrail {
	
	@Autowired
	private RepositoryAuthToken repositoryAuth;
	
	org.slf4j.Logger LOG_CONTROLLER = LoggerFactory.getLogger("auditController");
	org.slf4j.Logger LOG_SERVICE = LoggerFactory.getLogger("auditService");
	
	
	String token, userId;
		
		
	    @Before("execution(* com.siemo.notif.system.controller.*.*(..))")
	    public void before(JoinPoint joinPoint) {
	    	LOG_CONTROLLER.info("before method" + joinPoint.getSignature().getName() + "Class" 
	    						+ joinPoint.getTarget().getClass().getSimpleName());
	    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	    	this.token = request.getHeader("token_addr");
	    	this.userId = request.getHeader("user_addr");
	    }
		
	    @Cacheable("token")
		public String getToken() {
			String response;
			CredentialToken data = repositoryAuth.findByUserId(userId);
	    	if(token.equals(data.getToken())) {
	    		 response = "true";
	    		System.out.println("TRUE");
	    	}
	    	else {
	    		 response = "false";
	    		System.out.println("FALSE");
	    	}
	    	return response;
		}
		
	    
	    @AfterReturning(pointcut = "(execution(* com.siemo.notif.system.controller.*.*(..))) && args(request)", returning = "result")
	    public void afterReturning(JoinPoint joinPoint, Object request, Object result) throws IOException {
	    	
	    ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	    String req = objectWriter.writeValueAsString(request);
	    String res = objectWriter.writeValueAsString(result);
	    
	    System.out.println(req);
	    System.out.println(res);
	    }
	    
}
