package snippet;

public class Snippet {
	server.port=8085
	
	spring.datasource.url = jdbc:mysql://localhost:3306/user-service
	spring.datasource.username =root
	spring.datasource.password = 
	
	# Keep the connection alive if idle for a long time (needed in production)
	## Database connection pooling properties
	# Number of ms to wait before throwing an exception if no connection is available.
	
	# Show or not log for each sql query
	#spring.jpa.open-in-view set to true avoid the lazy loading exception, when you try to access a reference outside the transantion by 
	# calling the get..() methode. because the transaction you start a the service layer continuos in the  web layer. so you continous to use the proxy . 
	spring.jpa.open-in-view=false
	 # Enable logging
	#logging.level.org.hibernate.SQL=DEBUG 
	#logs all JDBC parameters
	#logging.level.org.hibernate.type=TRACE 
	
	# JPA specific configs
	spring.jpa.show-sql=true
	spring.jpa.properties.hibernate.format_sql=true
	spring.jpa.properties.hibernate.generate_statistics=true
	
	# Hibernate ddl auto (create, create-drop, update)
	spring.jpa.hibernate.ddl-auto = update
	
	
	spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	#spring.datasource.driverClassName
	
	#connection pooling config tuto und values: https://howtodoinjava.com/spring-boot2/datasource-configuration/
	#spring.datasource.tomcat.initial-size=15
	#spring.datasource.tomcat.max-wait=20000
	#spring.datasource.tomcat.max-active=50
	#spring.datasource.tomcat.max-idle=15
	#spring.datasource.tomcat.min-idle=8
	#spring.datasource.tomcat.default-auto-commit=true  
	
	#maximum number of milliseconds that a client will wait for a connection
	spring.datasource.hikari.connection-timeout = 30000 
	#minimum number of idle connections maintained by HikariCP in a connection pool
	spring.datasource.hikari.minimum-idle= 5 
	#maximum pool size
	spring.datasource.hikari.maximum-pool-size= 10 
	#maximum idle time for connection
	spring.datasource.hikari.idle-timeout=30000 
	 # maximum lifetime in milliseconds of a connection in the pool after it is closed.
	spring.datasource.hikari.max-lifetime= 1000
	#default auto-commit behavior. 
	spring.datasource.hikari.auto-commit =true 
	spring.datasource.hikari.pool-name=ame=SpringBootJPAHikariCP
	
	
	
	#spring:
	#    datasource:
	#        url: 'jdbc:mysql://localhost/db?useSSL=false'
	#        username: root
	#        password: pass
	#        driver: com.mysql.jdbc.Driver
	#        hikari:
	#            minIdle: 10
	#            idle-timeout: 10000
	#            maximumPoolSize: 30
	
	
}

