# spring-boot-cdafad2501
Projet de cours Java Spring Boot

### 1 Cloner ou forker le repository github
```sh
git clone https://github.com/mithridatem/spring-boot-cdafad2501.git games
cd games
```

### 2 Créer la base de données :
```sql
CREATE DATABASE IF NOT EXISTS nom_bdd CHARSET utf8mb4;
```

### 3 Configurer le fichier des variables d'environnement :  
**applications.properties** (*avec vos valeurs*)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nom_bdd
spring.datasource.username=login_BDD
spring.datasource.password=Password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver (le driver MYSQl . MariaDB)
security.jwt.public-key=classpath:jwt/jwt-public.pem
security.jwt.private-key=classpath:jwt/jwt-private.pem
# nom de l'api
security.jwt.issuer=demo-api
# expriration du token
security.jwt.expiration-minutes=60
```

### 4 Créer les clé avec openssl (dans le dossier ressources/jwt)
```shell
openssl genpkey -algorithm RSA -out jwt-private.pem -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in jwt-private.pem -out jwt-public.pem
```
