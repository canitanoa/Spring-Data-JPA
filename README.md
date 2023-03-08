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
````       

JPQL snippet: 
- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

| Keyword            	| Sample                                                  	| JPQL snippet                                                   	|
|--------------------	|---------------------------------------------------------	|----------------------------------------------------------------	|
| Distinct           	| findDistinctByLastnameAndFirstname                      	| select distinct … where x.lastname = ?1 and x.firstname = ?2   	|
| And                	| findByLastnameAndFirstname                              	| … where x.lastname = ?1 and x.firstname = ?2                   	|
| Or                 	| findByLastnameOrFirstname                               	| … where x.lastname = ?1 or x.firstname = ?2                    	|
| Is, Equals         	| findByFirstname,findByFirstnameIs,findByFirstnameEquals 	| … where x.firstname = ?1                                       	|
| Between            	| findByStartDateBetween                                  	| … where x.startDate between ?1 and ?2                          	|
| LessThan           	| findByAgeLessThan                                       	| … where x.age < ?1                                             	|
| LessThanEqual      	| findByAgeLessThanEqual                                  	| … where x.age <= ?1                                            	|
| GreaterThan        	| findByAgeGreaterThan                                    	| … where x.age > ?1                                             	|
| GreaterThanEqual   	| findByAgeGreaterThanEqual                               	| … where x.age >= ?1                                            	|
| After              	| findByStartDateAfter                                    	| … where x.startDate > ?1                                       	|
| Before             	| findByStartDateBefore                                   	| … where x.startDate < ?1                                       	|
| IsNull, Null       	| findByAge(Is)Null                                       	| … where x.age is null                                          	|
| IsNotNull, NotNull 	| findByAge(Is)NotNull                                    	| … where x.age not null                                         	|
| Like               	| findByFirstnameLike                                     	| … where x.firstname like ?1                                    	|
| NotLike            	| findByFirstnameNotLike                                  	| … where x.firstname not like ?1                                	|
| StartingWith       	| findByFirstnameStartingWith                             	| … where x.firstname like ?1 (parameter bound with appended %)  	|
| EndingWith         	| findByFirstnameEndingWith                               	| … where x.firstname like ?1 (parameter bound with prepended %) 	|
| Containing         	| findByFirstnameContaining                               	| … where x.firstname like ?1 (parameter bound wrapped in %)     	|
| OrderBy            	| findByAgeOrderByLastnameDesc                            	| … where x.age = ?1 order by x.lastname desc                    	|
| Not                	| findByLastnameNot                                       	| … where x.lastname <> ?1                                       	|
| In                 	| findByAgeIn(Collection<Age> ages)                       	| … where x.age in ?1                                            	|
| NotIn              	| findByAgeNotIn(Collection<Age> ages)                    	| … where x.age not in ?1                                        	|
| True               	| findByActiveTrue()                                      	| … where x.active = true                                        	|
| False              	| findByActiveFalse()                                     	| … where x.active = false                                       	|
| IgnoreCase         	| findByFirstnameIgnoreCase                               	| … where UPPER(x.firstname) = UPPER(?1)                         	|



<img src="https://github.com/canitanoa/spring-data-jpa/blob/master/DER.png"  width="600" height="300">