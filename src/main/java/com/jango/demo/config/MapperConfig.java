package com.jango.demo.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

//@Configuration
public class MapperConfig {

//  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer configure = new MapperScannerConfigurer();
    configure.setBasePackage("com.jango.demo.mapper");
    configure.setMarkerInterface(Mapper.class);
    return configure;
  }
  
}
