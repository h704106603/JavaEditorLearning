package com.config;

import com.model.Boss;
import com.model.Car;
import com.service.CustomCarEditor;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ImportResource(value = "classpath:spring.xml")
public class BeanConfig {

}
