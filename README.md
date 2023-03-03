# spring-data-jpa
Project Spring Data JPA

````
1. La DB se levanta con "docker-compose.yml" por default se llama "postgres"
    Ver: docker-compose.yml
    - Para ejecutarla y que corra en Docker Desktop 
        - Si se corre desde IntelliJ (con el plugin de Docker) 
            - solo darle a >>
        - Si se corre de linea de comendo: 
            - $ docker-compose -f docker-compose.yml up -d

2. Se configuran las properties para que conecte contra la DB postgres
    Ver: src/main/resources/application.properties

3. Para acceder a la DB: 
    - Por IDE "DBeaver" https://dbeaver.io/
    - Por PlugIn "Database Navigator" https://plugins.jetbrains.com/plugin/1800-database-navigator
    Parametros de conexión:    
        Host: localhost - Port: 54321
        User: user
        Pass: admin
        
        
