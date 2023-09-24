# Game2048

Game2048 is a Java implementation of the popular puzzle game 2048. It includes a graphical user interface (GUI) and a terminal-based version.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
- [Game Rules](#game-rules)
- [Gameplay](#gameplay)
- [Screenshots](#screenshots)
- [Author](#author)

## Introduction

2048 is a single-player sliding block puzzle game where the objective is to combine tiles with the same number to reach the tile with the number 2048. The game is played on a 4x4 grid.

## Features

- GUI-based version of the game with a graphical interface.
- Terminal-based version for playing in the command line.
- Move tiles in four directions: Up, Down, Left, and Right.
- New tiles with values 2 or 4 spawn after each move.
- Game over when there are no more valid moves.
- Reach the tile with the value 2048 to win.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- Java IDE (e.g., Eclipse, IntelliJ IDEA) for GUI version (optional).

### Running the Game

#### GUI Version

1. Open a Java IDE (e.g., Eclipse, IntelliJ IDEA).
2. Import the project.
3. Run the `Game2048` class.
4. Type "G".

#### Terminal Version

1. Open your terminal or command prompt.
2. Navigate to the project directory.
3. Compile the `Game2048.java` file: `javac Game2048.java`
4. Run the compiled program: `java Game2048`

## Game Rules

- The game is played on a 4x4 grid.
- Tiles with the same number can be combined when they collide by moving in one of the four directions: Up, Down, Left, and Right.
- When two tiles with the same number collide, they merge into a tile with a value equal to their sum.
- After each move, a new tile with a value of either 2 or 4 will appear on the board.
- The game continues until you reach the tile with the value 2048 or there are no more valid moves.

## Gameplay

- Use the arrow keys (Up, Down, Left, Right) to move the tiles.
- Combine tiles with the same number to create higher-value tiles.
- Try to reach the tile with the value 2048 to win.
- The game ends when there are no more valid moves left.

## Screenshots

<img width="596" alt="Game Initiated" src="https://github.com/Maria-shn/2048/assets/105124040/400f7298-d8f7-4ea7-8ae5-01128e64311a">

<img width="598" alt="After several moves" src="https://github.com/Maria-shn/2048/assets/105124040/10a4aa45-3247-43c7-9dc6-09ffa466a6b1">

<img width="596" alt="Game Over" src="https://github.com/Maria-shn/2048/assets/105124040/08320211-0a33-414c-9214-8cce59de1c7f">

## Author

- Maria Shaposhnikova
