# Service Web SOAP - Gestion des Personnes

Ce projet implémente un service web SOAP pour la gestion des personnes avec une base de données MySQL.

## Technologies utilisées

- Java
- JAX-WS (Java API for XML Web Services)
- MySQL
- JDBC

## Fonctionnalités

- Ajouter une personne
- Supprimer une personne
- Rechercher une personne par ID
- Rechercher une personne par nom
- Lister toutes les personnes

## Structure du projet

```
SOA1/
├── src/
│   └── com/
│       └── info/
│           ├── model/
│           │   └── Person.java
│           └── service/
│               ├── PersonService.java
│               ├── PersonServiceImpl.java
│               ├── SOAPPublisher.java
│               ├── SOAPPublisherClient.java
│               └── Test.java
```

## Configuration

1. Créer une base de données MySQL nommée `persondb`
2. Créer une table `person` :
```sql
CREATE TABLE person (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);
```

## Démarrage

1. Démarrer le serveur :
```bash
java com.info.service.SOAPPublisher
```

2. Le service sera disponible à :
```
http://localhost:8888/ws/person
```

3. Pour tester, exécuter le client :
```bash
java com.info.service.SOAPPublisherClient
```

## Points d'attention

- Le service utilise le port 8888
- Les requêtes SQL sont sécurisées contre les injections SQL
- La gestion des erreurs est implémentée pour toutes les opérations
