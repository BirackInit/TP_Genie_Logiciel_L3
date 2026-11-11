# Jeu de stratégie au tour par tour

Projet de Génie Logiciel, Licence 3 Informatique (SINFL5A1), Université de Caen Normandie.

## Présentation

Le projet consiste à concevoir et développer un jeu de combat stratégique au tour par tour. Le jeu oppose entre 2 et n combattants sur une grille en deux dimensions dont la taille est paramétrable.

Chaque combattant dispose d'une énergie initiale et d'armes en nombre de munitions limité. Les joueurs doivent gérer leurs ressources et adapter leur stratégie au fil de la partie.

Le projet met en pratique la conception logicielle orientée objet, la modélisation UML et une architecture MVC.

## Fonctionnalités

- Grille paramétrable, avec des murs infranchissables et des pastilles d'énergie.
- Déplacement sur quatre directions, avec un coût en énergie.
- Mines, visibles par tous ou seulement par leur propriétaire, qui explosent quand un combattant marche dessus.
- Bombes à compte à rebours, qui touchent les huit cases voisines.
- Tirs horizontaux et verticaux à portée limitée, arrêtés par les murs.
- Bouclier qui protège des tirs et explosions pendant le tour suivant.
- Ordre de jeu fixe ou aléatoire à chaque tour.
- Deux stratégies de robots : une aléatoire, une d'attaque plus fine.
- Une vue complète de la partie et une vue par joueur, qui ne montre que les armes visibles pour ce joueur.
- Paramétrage des règles par un fichier `parametres.properties`, sans recompilation.

## Architecture

Le modèle est indépendant de l'interface graphique : il peut être utilisé en ligne de commande ou en réseau.

| Package | Rôle |
|---|---|
| `genie_logiciel.observer` | Notification du modèle vers ses vues |
| `genie_logiciel.model` | Règles et données du jeu (grille, combattants, armes, moteur) |
| `genie_logiciel.strategie` | Actions possibles et stratégies des robots |
| `genie_logiciel.vue` | Affichage : grille, tableau des combattants, fenêtres |
| `genie_logiciel.controleur` | Traitement du bouton « Suivant » |

## Patterns de conception

- **Observer** : `AbstractModeleEcoutable` prévient les vues après chaque changement.
- **Proxy** : `ProxyGrille` masque les armes invisibles pour un joueur, sans dupliquer la grille.
- **Command** : `Action` encapsule une action exécutable par le moteur.
- **Strategy** : `StrategieRobot` définit le comportement d'un robot. Deux implémentations sont fournies.
- **Singleton** : `Parametres` charge les valeurs du jeu une seule fois.

## Paramétrage

Les règles du jeu se modifient dans `parametres.properties`, sans recompiler :

| Clé | Signification | Valeur par défaut |
|---|---|---|
| `energie.initiale` | Énergie de départ d'un combattant | 100 |
| `cout.deplacement` | Énergie perdue à chaque déplacement | 1 |
| `cout.bouclier` | Énergie perdue à l'activation du bouclier | 5 |
| `degats.tir` | Dégâts d'un tir | 10 |
| `degats.explosion` | Dégâts d'une explosion | 30 |
| `portee.tir` | Distance maximale d'un tir | 5 |
| `delai.bombe` | Nombre de tours avant l'explosion d'une bombe | 3 |
| `taille.grille` | Taille de la grille (carrée) | 10 |
| `munitions.tir` | Munitions de tir de départ | 5 |
| `munitions.mine` | Mines de départ | 3 |
| `munitions.bombe` | Bombes de départ | 2 |

Le fichier est lu d'abord dans le dossier courant (par exemple `dist/`), puis dans le jar. Si une clé manque, la valeur par défaut est utilisée.

## Prérequis

- JDK compatible avec la version configurée dans `pom.xml` (release 26).
- Maven, ou NetBeans avec Maven intégré.

## Compilation et lancement

Compiler le projet :

```bash
mvn clean package
```

Lancer l'interface graphique depuis le dossier `dist/` de la livraison :

```bash
cd livraison/dist
java -jar application.jar
```

Lancer la vérification en console (sans interface) depuis NetBeans : clic droit sur `genie_logiciel/Main.java`, puis **Run File**.

## Ce que l'on voit à l'écran

- **Fenêtre principale** : la grille complète, toutes les armes comprises, un tableau avec l'énergie, les munitions et le bouclier de chaque robot, et le bouton « Suivant ».
- **Une fenêtre par joueur** : la grille telle que ce joueur la voit, avec son énergie et le numéro du tour.

## Structure du dépôt

```
TP_Genie_Logiciel_L3/
├── README.md
├── pom.xml
├── src/
│   └── main/
│       ├── java/genie_logiciel/
│       │   ├── Lanceur.java
│       │   ├── Main.java
│       │   ├── model/
│       │   ├── observer/
│       │   ├── strategie/
│       │   ├── vue/
│       │   └── controleur/
│       └── resources/
│           └── parametres.properties
└── livraison/
    ├── src/       code source commenté
    ├── doc/       Javadoc
    ├── dist/      application.jar et parametres.properties
    └── rapport/   rapport.pdf
```

## Gestion des versions

Le projet utilise Git pour suivre les modifications et conserver l'historique du développement. Les modifications sont enregistrées sous forme de commits, puis envoyées vers les dépôts distants.

- **GitHub** : dépôt du projet.
- **Redmine** : dépôt associé au projet universitaire.

Il est recommandé de vérifier régulièrement l'état du dépôt avec `git status`, et de rédiger des messages de commit qui décrivent clairement les changements.

## Outils utilisés

- **Java** : développement de l'application.
- **Maven** : compilation, tests et génération du jar.
- **UML et PlantUML** : modélisation et diagrammes de classes.
- **Git** : gestion des versions.
- **GitHub** et **Redmine** : hébergement des dépôts.

## Auteurs

Projet réalisé dans le cadre des travaux pratiques de Génie Logiciel, L3 Informatique.

- Mussa Abdallah Tayib, groupe 1A, encadré par Monsieur Yann Mathet.
- Adam Yassine, groupe 4B, encadré par Madame Amal Mahboubi.
- Abdell-Rahmmen Benkrama, groupe 4B, encadré par Madame Amal Mahboubi.

## Limites connues

- Seuls des robots jouent : il n'y a pas encore d'interface de jeu pour un joueur humain.
- Les murs sont fixes, il n'y a pas de briques destructibles.
- Les explosions ne sont pas animées.
- Le jeu en réseau n'est pas implémenté, mais le modèle est indépendant de la vue et permettrait cette extension.

