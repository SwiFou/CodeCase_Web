# CodeCase Web

Module frontend du projet **CodeCase**, un réseau social permettant aux développeurs de partager des posts de code.

Ce module fait partie d'une application Maven multi-module, développée dans le cadre d'un stage CDA (Concepteur Développeur d'Applications) à l'AFPA. Il consomme les services exposés par le module `codecase_api`.

## 📌 Contexte

CodeCase Web est le client web du projet, construit avec **Thymeleaf**. Il communique avec `codecase_api` (port `9002`) pour l'ensemble des opérations métier (authentification, création de posts, etc.).

- **Deadline projet :** 17/07/2026
- **Port :** `9001`
- **Stratégie d'évolution :** Thymeleaf dans un premier temps, migration vers React envisagée par la suite (objectif PWA)

## 🛠️ Stack technique

- Java / Spring Boot `4.0.6`
- Thymeleaf
- Spring Web (client REST vers `codecase_api` via `RestTemplate`)
- Lombok (`@RequiredArgsConstructor` — nécessite des champs `final`)
- CSS (Flexbox, `clamp()`) pour le responsive
- Maven (outil de gestion de projet et de build)

## 🏗️ Architecture

### Frontend / Vues

- Architecture en fragments Thymeleaf : `th:insert`, `th:replace`, `th:each`
- Distinction `th:text` (échappement XSS) vs `th:utext`
- Rendu du code posté via `<pre><code>`

### Gestion des erreurs

- `CodeCaseWebException` : exception métier centralisée
- `ExceptionManagerWeb` (`@ControllerAdvice`) : gestionnaire d'erreurs centralisé
- Messages d'erreur utilisateur en français, journalisés via l'annotation `@SLF4J`

### Points d'attention connus (bugs récurrents traités)

- `@RequiredArgsConstructor` exige des champs `final`
- Placement correct du `BindingResult` (juste après l'objet validé par `@Valid`)
- `NumberFormatException` sur les `<select>` non renseignés
- `DataIntegrityViolationException` liée aux clés étrangères non définies
- Incompatibilité `th:field` avec des champs de formulaire typés entité → utilisation manuelle de `bindingResult.rejectValue()`

### Communication avec l'API

Le module web appelle l'API via `RestTemplate`, avec gestion des exceptions :
- `HttpClientErrorException`
- `HttpServerErrorException`
- `ResourceAccessException`

## 🚀 Lancement du projet

```bash
# Depuis la racine du projet multi-module
cd codecase_web
mvn spring-boot:run
```

L'application sera accessible sur `http://localhost:9001`.

> ⚠️ Le module `codecase_api` doit être démarré au préalable (port `9002`) pour que l'application web fonctionne correctement.

### Configuration

Les propriétés de configuration se trouvent dans :
```
src/main/resources/application.properties
```

<!-- À COMPLÉTER : URL de l'API configurée, éventuelles variables d'environnement -->

## 🧪 Tests

Les tests sont effectués manuellement directement dans les vues en lançant le projet.
D'autres tests viendront par la suite notamment des tests Mockito, des tests de bout en bout avec Selenium.
<!-- À COMPLÉTER -->

## 👥 Auteurs

Projet réalisé par Alexandre CALDEROLI dans le cadre du titre CDA à l'AFPA.
