# Job Portal – Backend

Bienvenue sur le backend du Job Portal, le moteur qui alimente la logique métier et les données de la plateforme.

---

## À propos du projet

Ce backend est développé en Java avec Spring Boot (version 17+) et assure la gestion complète des utilisateurs, offres d’emploi, candidatures, et la sécurisation via JWT.

L’architecture est organisée selon le modèle à 3 tiers avec des couches bien distinctes pour :

- L’API REST (contrôleurs)
- Les DTO (Data Transfer Objects) pour la communication
- Les Entities (modèles de données)
- La gestion des exceptions
- La sécurité (authentification JWT optionnelle)
- Les repositories pour la persistance avec MongoDB
- La logique métier dans les services
- Les utilitaires divers pour la gestion interne

---

## Technologies principales

- Java 17+  
- Spring Boot  
- Spring Data MongoDB  
- MongoDB  
- Maven pour la gestion des dépendances  
- (Optionnel) Spring Security + JWT pour l’authentification et la protection des endpoints  

---

## Architecture du projet

- **API (Controllers) :** points d’entrée REST pour gérer les requêtes HTTP  
- **DTO :** objets pour transférer les données entre client et serveur  
- **Entity :** modèles représentant les données dans la base MongoDB  
- **Exception :** gestion personnalisée des erreurs  
- **Repository :** interface d’accès aux données MongoDB  
- **Service :** logique métier et règles de gestion  
- **Utility :** classes utilitaires pour diverses opérations transverses  
- **Security (JWT) :** authentification et autorisation sécurisée  

---

## Prérequis

- Java 17 ou supérieur  
- Maven  
- MongoDB installé et lancé localement ou accessible via cloud  

---

## Installation & lancement

1. Clone le repo :

git clone https://github.com/zairz7018/job-Portal-Backend.git
cd job-portal-backend
