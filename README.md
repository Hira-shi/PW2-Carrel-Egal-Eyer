# PW2 - Quiz multijoueur en TCP

Ce projet est un jeu de quiz multijoueur fonctionnant en TCP.
Un serveur multi-clients gère les connexions et le déroulement des parties.
Chaque joueur s'y connecte avec un client REPL, directement depuis son terminal.
L'application est écrite en Java et distribuée sous forme de JAR exécutable.

## Spécification du protocole

Le protocole applicatif est décrit dans [docs/PROTOCOL.md](docs/PROTOCOL.md).


## Règles du jeu

Cette section précise les règles du quiz, complémentaires à la [spécification du protocole](docs/PROTOCOL.md).

- **Nombre de questions (`N`) :** Une partie comporte `5` questions tirées aléatoirement. Si un filtre de thème ou de sous-thème contient moins de `5` questions, la partie se joue sur toutes les questions disponibles pour ce filtre.


- **Temps de réponse :** Chaque question dispose d'un délai maximum de `20` secondes. Elle se clôture plus tôt dès que tous les joueurs présents ont répondu.


- **Attribution des points :** Seules les bonnes réponses rapportent des points, en fonction de l'ordre d'arrivée parmi les bonnes réponses :
  - 1er bonne réponse : `1000` points
  - 2e bonne réponse : `800` points
  - 3e bonne réponse : `600` points
  - 4e bonne réponse : `400` points
  - 5e bonne réponse et suivantes : `200` points
  - Mauvaise réponse ou délai dépassé (`TIMEOUT`) : `0` point


- **Classement et départage :** Les joueurs sont classés par score décroissant. En cas d'égalité de score, le départage se fait par ordre alphabétique croissant du pseudo, sans tenir compte de la casse (`Alice` est devant `bob`).


- **Fin de partie :** Après l'affichage du podium (`END`), tous les joueurs reviennent automatiquement dans le lobby et les scores repartent à `0` à la partie suivante.

## Prérequis

- [Java 25](https://openjdk.org/projects/jdk/25/)
- [Docker](https://docs.docker.com/get-docker/)
- [Git](https://git-scm.com/downloads)

Maven n'a pas besoin d'être installé : le projet utilise le Maven Wrapper (`./mvnw`).

## Cloner et compiler

```bash
git clone https://github.com/Hira-shi/PW2-Carrel-Egal-Eyer.git
cd PW2-Carrel-Egal-Eyer
./mvnw clean package
```

Le JAR exécutable est généré dans `target/app.jar`.

## Lancer l'application avec le JAR

Afficher l'aide générale :

```bash
java -jar target/app.jar --help
```

### Serveur

```bash
java -jar target/app.jar server
```

Afficher les options du serveur :

```bash
java -jar target/app.jar server --help
```

### Client

Dans un autre terminal :

```bash
java -jar target/app.jar client
```

Afficher les options du client :

```bash
java -jar target/app.jar client --help
```

> **TODO :** documenter les options picocli du serveur et du client (hôte, port, etc.)
> et ajouter un exemple complet lorsqu'elles seront implémentées.

## Lancer avec Docker

> **TODO (#28) :** ajouter les commandes pour récupérer l'image et lancer le serveur
> et le client avec Docker.

## Construire et publier l'image

> **TODO (#28) :** ajouter les commandes pour construire l'image Docker et la publier.

## Lancer les tests

```bash
./mvnw test
```

## Dépendances

| Dépendance | Version | Utilisation |
|---|---|---|
| [picocli](https://picocli.info/) | 4.7.7 | Définition de la ligne de commande, des sous-commandes et de leurs options |
| [JUnit 5](https://junit.org/junit5/) | 5.11.0 | Écriture et exécution des tests |

La partie réseau utilise uniquement les classes Java vues dans le cours, sans
bibliothèque réseau externe.

Le plugin de build [Maven Shade](https://maven.apache.org/plugins/maven-shade-plugin/)
sert à produire un JAR exécutable contenant les dépendances nécessaires.

## Sources et usage de l'IA

### Sources

- [Documentation de picocli](https://picocli.info/)
- [Documentation de Maven Wrapper](https://maven.apache.org/tools/wrapper/)
- [Documentation de Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/)
- [Conventional Commits](https://www.conventionalcommits.org/fr/v1.0.0/)

> **TODO :** ajouter les supports de cours et les autres sources utilisées.

### Usage de l'IA

Claude (Anthropic) a été utilisé pour :

- Généré les README.md et CONTRIBUTING.md

Les propositions ont été relues, adaptées et testées par l'équipe.

> **TODO :** compléter cette section avec les autres usages de l'IA avant la livraison.

## Auteurs

- Jérémie Carrel
- Omar Egal Ahmed
- Kaeno Eyer