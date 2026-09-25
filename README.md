# Inscryption — Java

Adaptation console du jeu de cartes [Inscryption](https://www.inscryption.com/) de Daniel Mullins Games, réalisée en Java dans le cadre de la SAÉ 2.01 (BUT Informatique, Université de Strasbourg).

## Le jeu

Le joueur affronte une IA dans des duels de cartes en best of 5 (premier à 3 victoires). Chaque manche se joue sur un plateau de 4 colonnes : le joueur place ses créatures en bas, l'ennemi en haut. À la fin de chaque tour, les cartes attaquent la case en face d'elles. Si la case est vide, les dégâts vont directement au score. Le premier camp à atteindre 5 points de différence gagne la manche.

Pour poser des cartes, il faut des ressources :
- **Sang** — obtenu en sacrifiant une créature déjà en jeu
- **Os** — récupéré quand une de vos créatures meurt

À la 3ème manche, le joueur choisit une nouvelle carte à ajouter à sa pioche et peut utiliser la **pierre de sacrifice** pour transférer le pouvoir d'une carte vers une autre.

### Aperçu en jeu

```
*-----------*   *************   *-----------*   *************
| Hermine   |   |           |   | Louveteau |   |           |
|-----------|   |     A2    |   |-----------|   |     A4    |
| PV : 3    |   |           |   | PV : 1    |   |           |
| Att: 1    |   |           |   | Att: 1    |   |           |
|           |   |           |   | Croissance|   |           |
*-----------*   *************   *-----------*   *************
                           Score: 0
*-----------*   *************   *-----------*   *************
| Ecureuil  |   |           |   | Rocher    |   |           |
|-----------|   |     B2    |   |-----------|   |     B4    |
| PV : 1    |   |           |   | PV : 5    |   |           |
| Att: 0    |   |           |   |           |   |           |
*-----------*   *************   *-----------*   *************
```

## Cartes

### Animaux

| Nom | Att | PV | Sang | Os | Volant | Pouvoir |
|-----|:---:|:--:|:----:|:--:|:------:|---------|
| Écureuil | 0 | 1 | 0 | 0 | | |
| Hermine | 1 | 3 | 1 | 0 | | |
| Chat | 0 | 1 | 1 | 0 | | Nombreuses Vies |
| Louveteau | 1 | 1 | 1 | 0 | | Croissance |
| Loup | 3 | 2 | 2 | 0 | | |
| Punaise | 1 | 2 | 0 | 2 | | Puant |
| Moineau | 1 | 2 | 1 | 0 | oui | |
| Corbeau | 2 | 3 | 2 | 0 | oui | |
| Coyote | 2 | 1 | 0 | 4 | | |
| Grizzly | 4 | 6 | 3 | 0 | | |
| Élan | 2 | 4 | 2 | 0 | | Coureur |
| Vipère | 1 | 1 | 2 | 0 | | Contact Mortel |
| Porc-épic | 1 | 2 | 1 | 0 | | Piques Pointues |

### Obstacles

| Nom | PV |
|-----|:--:|
| Rocher | 5 |
| Sapin | 3 |

### Pouvoirs

- **Nombreuses Vies** — la créature survit quand elle est sacrifiée (une seule fois)
- **Croissance** — le Louveteau se transforme en Loup au bout d'un tour sur le plateau
- **Puant** — réduit de 1 l'attaque de la carte en face
- **Coureur** — se décale d'une case à droite après son attaque (à gauche si bloqué)
- **Contact Mortel** — tue instantanément toute créature touchée
- **Piques Pointues** — inflige 1 dégât à la carte qui l'attaque

Les pouvoirs sont transférables via la pierre de sacrifice à la 3ème manche.

## Structure du projet

```
├── src/
│   ├── Main.java
│   ├── core/              # Moteur de jeu, boucle principale
│   ├── Cards/
│   │   ├── Animals/       # Cartes animales + pouvoirs
│   │   └── Obstacles/     # Rocher, Sapin
│   ├── gameplay/           # Plateau, combat, pioche, score
│   └── Display/            # Affichage console
├── tests/                  # Tests JUnit 4
├── uml/                    # Diagrammes de classes PlantUML
└── deps/                   # JUnit + Hamcrest
```

## Conception

Le projet repose sur une hiérarchie de classes avec `Card` comme classe abstraite, étendue par `AnimalsCard` et `ObstaclesCard`. Les cartes volantes (Moineau, Corbeau) redéfinissent la méthode `attack()` pour contourner le défenseur — du polymorphisme classique.

Les pouvoirs utilisent un pattern Strategy : chaque pouvoir est une sous-classe de `Power` avec des hooks (`onEnemyAttackCalculation`, `onDamageDealt`, `onTurnEnd`, `onAttacked`, etc.) qui sont appelés au bon moment par le moteur de jeu. Ça permet d'ajouter un nouveau pouvoir sans toucher au code existant, et de les combiner librement entre eux via la pierre de sacrifice.

La logique de jeu (`Game`) est séparée de l'affichage (`Display`, `GameDisplay`), ce qui fait que le moteur est testable indépendamment de l'interface console.

Les retours d'actions (placement, pioche, sacrifice) passent par des enums de statut (`PlaceStatus`, `DrawStatus`, `SacrificeStatus`) plutôt que des booléens, pour avoir des messages d'erreur précis côté affichage.

## Tests

6 suites de tests JUnit couvrent le combat, le plateau, les pouvoirs, le score, le joueur et le moteur de jeu. Chacun des 6 pouvoirs a ses propres tests dédiés.

## Compilation et exécution

```bash
# Compiler
find src -name "*.java" | xargs javac -d out/

# Lancer
java -cp out/ Main

# Compiler avec les tests
find src tests -name "*.java" | xargs javac -cp deps/junit-4.13.2.jar:deps/hamcrest-core-1.3.jar -d out/

# Lancer un test
java -cp out/:deps/junit-4.13.2.jar:deps/hamcrest-core-1.3.jar org.junit.runner.JUnitCore Cards.PowerTest
```

---

Projet réalisé en BUT Informatique, Université de Strasbourg (2025). Inspiré du jeu [Inscryption](https://www.inscryption.com/) par Daniel Mullins Games.
