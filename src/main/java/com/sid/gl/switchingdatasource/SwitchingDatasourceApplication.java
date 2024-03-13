package com.sid.gl.switchingdatasource;


import io.unlogged.Unlogged;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwitchingDatasourceApplication {


	@Unlogged
	public static void main(String[] args) {
		SpringApplication.run(SwitchingDatasourceApplication.class, args);
	}

}
