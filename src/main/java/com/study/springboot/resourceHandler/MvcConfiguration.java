package com.study.springboot.resourceHandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//aws 호스팅을 활용해서 디렉토리를 지정하기 위해 설정 오버라이드
//참조 사이트: https://mopil.tistory.com/58#%--%--%EC%-A%A-%ED%--%--%EB%A-%--%--%EB%B-%--%ED%-A%B-%--%EC%--%A-%EC%A-%--
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**")
    .addResourceLocations("file:///home/ubuntu/nmomg/assets/img/");
  }

}
