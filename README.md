# Java  Cartes & Blackjack  : Architecture logicielle & Design Patterns
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Build](https://img.shields.io/badge/Build-Apache_Ant-A81C07?style=for-the-badge&logo=apache-ant&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Finished-brightgreen?style=for-the-badge)

> **Projet : Méthodes de Conception (L3 Informatique)** > *Université de Caen Normandie — 2025-2026*

Ce projet consiste en une implémentation modulaire  d'un jeu de Blackjack, articulée autour d'une **librairie de gestion de cartes générique**. L'objectif central était d'appliquer les principes fondamentaux de l'ingénierie logicielle pour créer une application robuste, maintenable et découplée.

---

## Architecture et Paradigmes de Conception 

Le projet repose sur  l'architecture **MVC (Modèle-Vue-Contrôleur)** pour séparer strictement la logique métier de l'interface graphique (**Java Swing**).

### Design Patterns Implémentés
L'intégration de **6 patrons de conception** majeurs assure la flexibilité du système :

| Pattern | Rôle et Bénéfices |
| :--- | :--- |
| **Command** | Encapsulation des actions de jeu (Hit, Stand, Split). Permet une gestion modulaire et un historique d'actions. |
| **Decorator** | Ajout dynamique de fonctionnalités aux mains des joueurs sans modifier les classes de base (ex: gestion des mises). |
| **Strategy** | Algorithmes interchangeables pour l'IA des robots. Permet de changer de comportement à la volée. |
| **Observer** | Synchronisation automatique de l'interface utilisateur dès que l'état du modèle (paquet, main) change. |
| **Factory** | Centralisation de la création des jeux (52 ou 32 cartes) garantissant la cohérence des objets. |
| **Adapter** | Pont technique pour lier les objets métier aux composants complexes `JTable` de Swing. |

---

## Installation & Exécution

Le projet utilise **Apache Ant**. Les deux modules sont interdépendants.

###  1. Compiler la librairie "Cartes":
Le module Blackjack a besoin du JAR de la librairie pour fonctionner.
  ```
  cd cartes
  ant dist     
  ```
    
###  2. Lancer le BlackJack 
Une fois la librairie compilée ,vous pourrez lancer l'application comme suit :
  ```
  cd ../blackjack
  ant compile      # Compilation du code source
  ant run          # Lancement de l'application
  ```
---

### 3. Tests & Maintenance

Afin de garantir la stabilité et la propreté du projet, les commandes suivantes sont disponibles :

* **`ant test`** : Exécute la suite de tests unitaires **JUnit**.
* **`ant clean`** : Supprime les dossiers `build/` et les fichiers exécutables temporaires.

---
### 4.Organisation et Arborescence :

        ```
        .
        ├── cartes/              # Librairie générique (Modèles de cartes, paquets...)
        │   ├── src/             # Code source Java
        │   └── build.xml        # Script de build Ant
        ├── blackjack/           # Application métier (Logique, IA, Swing)
        │   ├── src/             # Code source Java
        │   ├── lib/             # Dépendances (reçoit le JAR de 'cartes')
        │   └── build.xml        # Script de build Ant
        └── Rapport/             # Contient le rapport technique détaillé
        ```
### 5.Équipe de Développement
    Lena REZGUI
    Aris BENSADI
    Mohamed CHAIB SETTI
    Mohamed Yassine LAMAIRI
Projet réalisé sous la supervision de : Y. Mathet, C. Charrier et G. Letellier.



