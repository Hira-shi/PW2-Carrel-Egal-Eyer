# Contribuer au projet

Ce document décrit les conventions de branches et de commits du projet. (Cela a été généré par IA)

## Branches

La branche `main` doit toujours compiler et rester fonctionnelle. On ne pousse
pas directement dessus : tout changement passe par une branche et une pull request.

### Nommage

```text
<type>/<description-courte>
```

- en minuscules ;
- mots séparés par des tirets ;
- description courte et explicite ;
- mêmes types que pour les commits.

Exemples :

```text
feat/server-command
fix/client-connection-timeout
docs/update-readme
refactor/message-parser
```

## Commits

Les messages suivent la convention
[Conventional Commits](https://www.conventionalcommits.org/fr/v1.0.0/) :

```text
<type>(<portée optionnelle>): <description>

<corps optionnel>
```

### Types

| Type       | Utilisation                                             |
|------------|---------------------------------------------------------|
| `feat`     | Ajout d'une fonctionnalité                              |
| `fix`      | Correction d'un bug                                     |
| `docs`     | Modification de la documentation                        |
| `style`    | Mise en forme, sans changement de comportement          |
| `refactor` | Restructuration, sans nouvelle fonctionnalité ni correctif |
| `test`     | Ajout ou modification de tests                          |
| `build`    | Maven, dépendances ou packaging                         |
| `ci`       | Intégration continue                                    |
| `chore`    | Autres tâches de maintenance                            |

### Portées

Portées courantes : `client`, `server`, `cli`.

### Règles

- écrire la description en français, à l'impératif ;
- commencer la description par une minuscule ;
- ne pas terminer la description par un point ;
- limiter la première ligne à 72 caractères ;
- faire des commits petits, centrés sur un seul changement ;
- utiliser le corps du message pour expliquer le pourquoi, si nécessaire.

Exemples :

```text
feat(server): ajoute la sous-commande server
fix(client): corrige la gestion d'une connexion refusée
docs: décrit la compilation du projet
build: ajoute maven-shade-plugin
```

Un changement incompatible est signalé par `!` :

```text
feat(cli)!: renomme l'option --host en --address
```

## Workflow

1. Mettre à jour `main` :

   ```bash
   git switch main
   git pull
   ```

2. Créer une branche :

   ```bash
   git switch -c feat/ma-fonctionnalite
   ```

3. Faire les modifications et les commits.

4. Vérifier que le projet compile et que les tests passent :

   ```bash
   ./mvnw clean package
   ```

5. Pousser la branche :

   ```bash
   git push -u origin feat/ma-fonctionnalite
   ```

6. Ouvrir une pull request vers `main`.

## Pull requests

- le titre suit le format des commits ;
- la description résume le changement et indique comment le tester ;
- au moins un autre membre du groupe relit la pull request ;
- la branche est supprimée après la fusion.