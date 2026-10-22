## Présentation du projet

# Jeu de stratégie au tour par tour – Génie Logiciel L3

Ce projet est réalisé dans le cadre des travaux pratiques de **Génie Logiciel – Licence 3 Informatique**.

Il consiste à concevoir et à développer une application de jeu de combat stratégique au tour par tour. Le jeu oppose entre **2 et n joueurs** sur une grille en deux dimensions dont la taille est paramétrable.

Chaque joueur contrôle un combattant disposant initialement d'une certaine quantité d'énergie et de plusieurs armes de différents types. Les armes possèdent des munitions en quantité limitée, ce qui oblige les joueurs à gérer leurs ressources et à adapter leur stratégie au cours de la partie.

Le projet vise à appliquer les principes de conception logicielle, la modélisation UML et les méthodes de développement orienté objet afin de proposer une architecture claire, cohérente et évolutive.

## Objectifs du projet

Les principaux objectifs sont les suivants :

- Concevoir un jeu de combat stratégique au tour par tour.
- Permettre à plusieurs joueurs de s'affronter sur une grille en deux dimensions.
- Rendre la taille de la grille paramétrable.
- Modéliser les combattants, leurs caractéristiques, leurs armes et leurs munitions.
- Représenter les éléments du jeu à l'aide de diagrammes UML.
- Définir une architecture logicielle adaptée, notamment selon le modèle MVC si celui-ci est retenu dans la conception.
- Appliquer les principes de la programmation orientée objet.
- Utiliser Git pour gérer les versions du projet et faciliter le travail collaboratif.

## Description générale du jeu

### Terrain de jeu

Le jeu se déroule sur une grille en deux dimensions dont les dimensions peuvent être définies selon les paramètres de la partie.

Cette grille constitue l'espace dans lequel évoluent les combattants. Elle permet de représenter leurs positions et de structurer les déplacements au cours du jeu.

### Joueurs et combattants

Une partie oppose au minimum deux joueurs, avec la possibilité d'en accueillir davantage.

Chaque joueur contrôle un combattant possédant notamment :

- Une quantité initiale d'énergie.
- Une position sur la grille.
- Des armes de différents types.
- Une réserve de munitions limitée pour ses armes.

### Armes et munitions

Les combattants disposent de plusieurs types d'armes. Chaque arme possède ses propres caractéristiques, selon les règles qui seront définies pour le jeu.

Les munitions étant limitées, les joueurs doivent choisir leurs actions avec attention afin de gérer efficacement leurs ressources.

### Déroulement des tours

Le jeu repose sur un système de tours successifs. Chaque joueur intervient à son tour pour effectuer les actions autorisées par les règles du jeu.

Les conditions précises de déplacement, d'attaque, de consommation d'énergie et de victoire seront définies conformément à l'énoncé complet du projet.

## Conception et modélisation UML

La conception du logiciel s'appuie sur la modélisation UML afin de représenter les principaux éléments du jeu et leurs interactions.

Les diagrammes permettent notamment d'étudier :

- Les classes représentant le jeu, la grille, les joueurs, les combattants et les armes.
- Les attributs et les méthodes des différentes classes.
- Les relations entre les objets du jeu.
- Le déroulement des actions et des tours.
- L'organisation de l'architecture logicielle.

PlantUML est utilisé pour décrire les diagrammes à partir de fichiers texte et faciliter leur modification.

## Contenu du dépôt

Les principaux fichiers du projet sont :

| Fichier ou répertoire           | Description                                                                                 |
| ------------------------------- | ------------------------------------------------------------------------------------------- |
| `README.md`                     | Présentation et documentation du projet.                                                    |
| `DigrammeClassePartie1.png`     | Image du diagramme de classes de la première partie.                                        |
| `DigrammeClassePartie2.png`     | A COMPLETER....                                                                             |
| `LienDigrammeClasse`            | Fichier contenant les liens associés aux diagrammes de classes.                             |
| `livraison/`                    | Répertoire destiné aux livrables du projet.                                                 |

*Remarque : cette liste doit être adaptée aux fichiers réellement présents dans le dépôt.*

## Outils utilisés

- **Java**, si retenu pour l'implémentation : développement de l'application.
- **UML** : modélisation et conception du logiciel.
- **PlantUML** : création des diagrammes UML.
- **Git** : gestion des versions et suivi des modifications.
- **GitHub** : hébergement du dépôt du projet.
- **Redmine** : hébergement du dépôt universitaire et suivi du projet.

## Organisation des livrables

Le répertoire `livraison/` doit contenir les éléments nécessaires à la remise du projet.

```text
livraison/
├── src/
│   └── Code source commenté
├── doc/
│   └── Documentation Javadoc
├── dist/
│   ├── application.jar
│   └── Ressources nécessaires à l'exécution
└── rapport/
    └── rapport.pdf
```



## Gestion des versions

Le projet utilise Git pour suivre les modifications et conserver l'historique du développement.

Les modifications sont enregistrées sous forme de commits, puis envoyées vers les dépôts distants concernés.

- **GitHub** : dépôt du projet.
- **Redmine** : dépôt associé au projet universitaire.

Il est recommandé de vérifier régulièrement l'état du dépôt avec `git status` et de rédiger des messages de commit décrivant clairement les modifications effectuées.

## Auteurs

Projet réalisé dans le cadre des travaux pratiques de Génie Logiciel – L3 Informatique.

- **Mussa Abdallah Tayib** — Groupe 1A, encadré par Monsieur Yann Mathet.
- **Adam Yassine** — Groupe 4B, encadré par Madame Amal Mahboubi.
- **Abdell-Rahmmen Benkrama** — Groupe 4B, encadré par Madame Amal Mahboubi.

## Conclusion

Ce projet permet de mettre en pratique les principes de la conception logicielle et de la programmation orientée objet à travers la réalisation d'un jeu de stratégie au tour par tour.

A COMPLETER...
