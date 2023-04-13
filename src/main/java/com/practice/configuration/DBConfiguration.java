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

import javax.annotation.Resource;
import javax.sql.DataSource;

//수동으로 스프링컨테이너에 빈을 등록하기 위해서 @Configuration을 클래스에 붙여주고 @Bean으로 등록한다. 메소드 이름으로 Bean 이름이 결정된다.
@Configuration
@PropertySource("classpath:/application.properties") //@PropertySource : 해당 클래스에서 참조할 properties의 경로를 선언(지정)한다.
public class DBConfiguration {

    @Autowired
    private ApplicationContext context; //스프링 컨테이너

    /////Data Access Layer//////
    //Application - JDBC Interface(+DataSource) - JDBC Driver - DB
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari") //@ConfigurationProperties : prefix(접두사), spring.datasource.hikari로 시작하는 설정을 모두 읽어 들여 해당 메서드에 바인딩(실제 값을 결정짓는 행위)한다.
    public HikariConfig hikariConfig() { //HikariCP(Connection Pool, 커넥션풀)객체를 생성한다.
        return new HikariConfig(); //즉 DB 연결 설정을 통해 데이터베이스와 연결된 커넥션을 미리 만들어놓고 pool로 관리하는 것이다. 필요할 때마다 커넥션풀의 커넥션을 이용하고 반환한다.
    }

    @Bean
    public DataSource dataSource() { //DataSource 객체를 생성한다. 커넥션풀 과정은 DataSource 하는 것이다.
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception { //SqlSessionFactory : DataSource를 참조하여 DB와 myBatis 서버를 연동시킨다.
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*.xml")); //XML Mapper 경로
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception { //SqlSessionFactory를 통해 생성되고 SQL실행만 담당한다.
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}