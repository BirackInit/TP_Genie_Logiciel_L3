# Jeu de combat au tour par tour

Projet de Génie Logiciel, Licence 3 Informatique (SINFL5A1), Université de Caen Normandie.

## Présentation

Le projet est un jeu de combat stratégique au tour par tour. Entre 2 et *n* combattants s'affrontent sur une grille en deux dimensions dont la taille est paramétrable.

Chaque combattant dispose d'une énergie initiale et d'armes dont le nombre de munitions est limité. Les joueurs gèrent leurs ressources et adaptent leur stratégie au fil de la partie. La partie se termine lorsqu'un seul combattant reste en vie.

Le projet met en pratique la conception orientée objet, la modélisation UML et une architecture MVC (modèle, vue, contrôleur).

## Règles et fonctionnalités

- **Grille paramétrable**, avec des murs infranchissables et des pastilles d'énergie à récupérer.
- **Déplacement** sur quatre directions, avec un coût en énergie.
- **Mines**, qui explosent quand un combattant marche dessus.
- **Bombes**, avec un compte à rebours en tours, qui touchent les huit cases voisines.
- **Tirs** horizontaux et verticaux, à portée limitée, arrêtés par les murs.
- **Bouclier**, qui protège des tirs et des explosions pendant le tour suivant.
- **Visibilité** des armes : visibles par tous, ou seulement par leur propriétaire.
- **Ordre de jeu** fixe ou aléatoire à chaque tour.
- **Deux stratégies de robots** : une aléatoire, une d'attaque plus élaborée.
- **Deux types de vues** : une vue complète, et une vue par joueur qui ne montre que les armes visibles pour lui.

## Architecture

Le modèle est indépendant de l'interface graphique : il peut être utilisé en ligne de commande ou en réseau.

| Package | Rôle |
|---|---|
| `tp_genie_logiciel_l3.observer` | Notification du modèle vers ses vues |
| `tp_genie_logiciel_l3.model` | Règles et données du jeu, contrats `Action` et `StrategieRobot` |
| `tp_genie_logiciel_l3.strategie` | Stratégies concrètes des robots |
| `tp_genie_logiciel_l3.vue` | Affichage : grille, tableau des combattants, fenêtres |
| `tp_genie_logiciel_l3.controleur` | Traitement du bouton « Suivant » |
| `tp_genie_logiciel_l3` | Point d'entrée (`Lanceur`) et programme de tests (`Main`) |

Les dépendances vont dans un seul sens : `vue`, `controleur` et `strategie` dépendent de `model`, et `model` ne dépend d'aucun de ces packages. Il n'existe donc aucun cycle entre packages.

## Patrons de conception

- **Observer** : `AbstractModeleEcoutable` prévient les vues après chaque changement (`fireChangement()`).
- **Proxy** : `ProxyGrille` masque les armes invisibles pour un joueur, sans dupliquer la grille.
- **Command** : `Action` encapsule une action, construite par des méthodes statiques (`deplacer`, `tir`, `poserMine`, `poserBombe`, `bouclier`, `rienFaire`).
- **Strategy** : `StrategieRobot` définit le comportement d'un robot. Deux implémentations sont fournies.
- **Factory Method** : `FactoryParametres` déclare la création des paramètres, et `FactoryParametresFile` la réalise en lisant le fichier de configuration.

## Paramétrage

Les règles se modifient dans `parametres.properties`, sans recompiler :

| Clé | Signification | Valeur par défaut |
|---|---|---|
| `energie.initiale` | Énergie de départ d'un combattant | 100 |
| `cout.deplacement` | Énergie perdue à chaque déplacement | 1 |
| `cout.bouclier` | Énergie perdue à l'activation du bouclier | 5 |
| `degats.tir` | Dégâts d'un tir | 10 |
| `degats.explosion` | Dégâts d'une explosion | 30 |
| `portee.tir` | Distance maximale d'un tir | 5 |
| `delai.bombe` | Tours avant l'explosion d'une bombe | 3 |
| `taille.grille` | Taille de la grille (carrée) | 10 |
| `munitions.tir` | Munitions de tir de départ | 5 |
| `munitions.mine` | Mines de départ | 3 |
| `munitions.bombe` | Bombes de départ | 2 |

Le fichier est lu dans le dossier courant (par exemple `dist/`). Si une clé manque, la valeur par défaut est utilisée.

## Prérequis

- JDK 26 (configuré dans `pom.xml` : `maven.compiler.release`).
- Maven, ou NetBeans avec Maven intégré.

## Compilation

```bash
mvn clean package
```

Le jar exécutable est produit dans `target/application.jar`.

## Lancement

Interface graphique, depuis le dossier qui contient le jar et `parametres.properties` :

```bash
java -jar application.jar
```

Vérification en console, sans interface : dans NetBeans, configure l'argument du test (`1` à `5`, ou aucun argument pour tous les tests), puis lance `Main`.

| Argument | Test |
|---|---|
| `1` | Paramètres et positions |
| `2` | Visibilité des armes |
| `3` | Combattant : munitions, bouclier, dégâts |
| `4` | Grille et proxy |
| `5` | Partie complète et Observer |

## Écran

- **Fenêtre principale** : la grille complète, toutes les armes comprises, un tableau avec l'énergie, les munitions et le bouclier de chaque robot, et le bouton « Suivant ».
- **Une fenêtre par joueur** : la grille telle que ce joueur la voit, avec son énergie et le numéro du tour.

## Structure du dépôt

```
TP_Genie_Logiciel_L3/
├── pom.xml
├── src/
│   └── main/
│       ├── java/tp_genie_logiciel_l3/
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
    ├── src/      code source commenté
    ├── doc/      Javadoc
    ├── dist/     application.jar et parametres.properties
    └── rapport/  rapport.pdf
```

## Gestion des versions

Le projet utilise Git. Chaque étape validée fait l'objet d'un commit, avec un message qui décrit la modification. Le dépôt est hébergé sur la forge de l'université, comme sous-projet du projet « TP Génie Logiciel L3 ».

## Outils utilisés

- **Java 26** et **Maven** : développement, compilation et génération du jar.
- **NetBeans** : environnement de développement.
- **UML et PlantUML** : diagrammes de classes, avec **Visual Paradigm** pour la modélisation.
- **Git** : gestion des versions.

## Auteurs

Projet réalisé dans le cadre des travaux pratiques de Génie Logiciel, L3 Informatique.

- Mussa Abdallah Tayib, groupe 1A, encadré par M. Yann Mathet.
- Adam Yassine, groupe 4B, encadré par Mme Amal Mahboubi.
- Abdell-Rahmmen Benkrama, groupe 4B, encadré par Mme Amal Mahboubi.

## Limites connues

- Seuls des robots jouent : il n'y a pas encore d'interface pour un joueur humain.
- Les murs sont fixes, il n'y a pas de briques destructibles.
- Les explosions ne sont pas animées.
- Le jeu en réseau n'est pas implémenté, mais le modèle est indépendant de la vue et permet cette extension.
