### 1. Vue d'ensemble
- **But et modèle** : quiz multijoueur, client-serveur, un serveur et plusieurs clients. Le serveur fait autorité (questions, rangs, scores).
- **Lobby** : le joueur rejoint avec un pseudo (JOIN), et les autres sont avertis. Il faut au moins 2 joueurs pour START. On ne peut pas rejoindre une partie en cours.
- **Partie** : N questions à 4 choix. Chaque question se termine quand tous ont répondu ou au timeout, puis viennent le résultat personnel et le classement.
- **Podium** : END donne le top 3, puis retour au lobby.

### 2. Transport

- **TCP**, avec trois justifications demandées :
  - **Fiabilité** : Une réponse perdue fauserait le score
  - **Ordre** : ```QUESTION```, ```RESULT``` et ```TOP``` doivent arriver dans cet ordre
  - **Détection de déconnexion** : le serveur peut retirer le joueur. Une coupure réseau sans fermeture TCP n'est détectée que plus tard.
- **Connexion** : le client l'ouvre et elle dure toute la session. ➕ Le client parle en premier, sans message d'accueil du serveur.
- **Messages spontanés** : le serveur peut envoyer un message à tout moment, donc le client doit toujours écouter.
-  **Fermeture** : par QUIT ou coupure brutale, traitées de la même façon pour les autres joueurs.

### 3. Port et encodage

- **Port TCP 6655 par défaut**, modifiable avec --port.
-  **UTF-8** dans les deux sens, quel que soit l'OS. ➕ Sans BOM.
-  Octets UTF-8 invalides : ERROR 1 (#27).

### 4. Délimitation

- Un message = une ligne terminée par \n (0x0A). Un read TCP peut contenir une partie de message ou plusieurs messages.
-  Le récepteur accepte \r\n en retirant le \r final. L'émetteur envoie toujours \n.
-  Longueur maximale : 1024 octets, \n compris. Au-delà, ERROR 1 (#27), et le serveur ignore la suite jusqu'au prochain \n.
-  Ligne vide : ERROR 1.
-  Connexion fermée avant le \n : le message incomplet est ignoré.

### 5. Synthaxe

- **Forme** : `COMMANDE [arg1] [arg2]…`. Commandes en majuscules, sensibles à la casse.
- **Séparateur** : exactement un espace (`0x20`), aucun en début ou fin de ligne.
- **`QUESTION`** : les champs texte sont séparés par `|`, car ils contiennent des espaces. Forme : `QUESTION <numéro> <total> <texte>|<choix1>|<choix2>|<choix3>|<choix4>`.
- **Pseudos** : `^[A-Za-z0-9]{3,12}$`, donc sans espace, `|` ni accent. `Bob` et `bob` sont le même pseudo.
- **Questions et choix** : `|`, `\n` et `\r` interdits, ainsi que les autres caractères de contrôle et les champs vides. Pas d'échappement.

### Questions et choix

- Les caractères `|`, `\n` et `\r` sont interdits.
- Les autres caractères de contrôle (`U+0000` à `U+001F` et `U+007F`) sont interdits.
- Un champ ne peut pas être vide et ne commence ni ne se termine par un espace.
- Il n'existe pas de mécanisme d'échappement.