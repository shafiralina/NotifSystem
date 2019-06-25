package com.siemo.notif.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.siemo.notif.system.aspect.AuditTrail;

@SpringBootApplication
public class NotifSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifSystemApplication.class, args);
		
	}
	
	@Bean(name = "auditTrail")
	public AuditTrail auditTrail() {
		return new AuditTrail();
	}

}
