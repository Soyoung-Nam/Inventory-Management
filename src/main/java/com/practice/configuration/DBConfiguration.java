package com.practice.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration //Configuration & Bean
@PropertySource("classpath:/application.properties") //해당 클래스에서 참조할 properties의 경로를 선언(지정)한다.
public class DBConfiguration {

    @Autowired
    private ApplicationContext context; //스프링 컨테이너

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari") //spring.datasource.hikari로 시작하는 설정을 모두 읽어 들여 해당 메서드에 바인딩한다.
    public HikariConfig hikariConfig() { //히카리CP(Connection Pool, 커넥션풀)객체를 생성한다.
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() { //데이터소스 객체를 생성한다. 커넥션 풀을 지원하기 위한 인터페이스
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception { //데이터소스를 참조하며 XML Mapper 경로와 설정파일 경로 등의 정보를 갖는 객체
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception { //SqlSessionFactory를 통해 생성되고 DB의 커밋 및 롤백 등..
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}