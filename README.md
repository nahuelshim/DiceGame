                                                       Dice Game with JWT + SQL

Este es tu proyecto final, una API 100% diseñada para ti donde aplicarás todo lo que sabes hasta ahora para crear una aplicación completa, desde la base de datos hasta la seguridad. Aplica todo lo que sabes hasta lo que no se pide.

Nivel 1
El juego de dados se juega con dos dados. Si el resultado de la suma de ambos dados es 7, se ganará la partida, si no se pierde. Un jugador puede ver una lista de todas las tiradas que ha hecho y el porcentaje de éxito.

Para jugar el juego y hacer un giro, un usuario debe registrarse con un nombre que no se repite. Cuando se crea, se le asigna un identificador numérico único y una fecha de registro. Si el usuario así lo desea, no podrá añadir ningún nombre y se denominará "ANÓNIMO". Puede haber más de un jugador “ANÓNIMO”. Cada jugador puede ver una lista de todas las tiradas que ha realizado, con el valor de cada dato y si ha ganado o no la partida. Además, podrás conocer tu tasa de éxito de todas las tiradas que hayas realizado.

No puede eliminar un juego específico, pero puede eliminar la lista completa de giros de un jugador.

El software debe permitir enumerar todos los jugadores del sistema, la tasa de éxito de cada jugador y la tasa de éxito promedio de todos los jugadores del sistema.

El software debe respetar los principales patrones de diseño. LOS GRADOS

Hay que tener en cuenta los siguientes detalles constructivos: -

URL:

POST: /jugadores: crea un jugador.

PUT /jugadores: modifica el nombre del jugador.

POST /players/{id}/games/ : un jugador específico tira los dados.

DELETE /players/{id}/games: elimina las tiradas del jugador.

GET /players/: devuelve la lista de todos los jugadores del sistema con su porcentaje medio de aciertos.

GET /players/{id}/games: devuelve la lista de juegos jugados por un jugador.

GET /players/ranking: devuelve la clasificación promedio de todos los jugadores en el sistema. Es decir, el porcentaje medio de logros.

GET /players/ranking/loser: devuelve al jugador con la peor tasa de éxito.

GET /players/ranking/winner: Devuelve el jugador con la peor tasa de éxito.

Swagger:

http://localhost:8080/swagger-ui/index.html#/ springdoc.api-docs.path = /dicegame-V-sql-openapi

Fase 1 Persistencia: utiliza MySQL como base de datos.

Fase 2 Cambie todo lo que necesite y use MongoDB para conservar los datos.

Fase 3 Añadir seguridad: incluir autenticación JWT en todos los accesos a las URL's del microservicio.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

This is your final project, an API 100% designed for you where you will apply everything you know so far to create a complete application, from the database to security. Apply everything you know even what is not asked for.

Level 1
The dice game is played with two dice. If the result of the sum of both dice is 7, the game will be won, if not lost. A player can see a list of all rolls you have done and the percentage of success.

In order to play the game and make a spin, a user must register with a non-repeating name. When created, it is assigned a unique numeric identifier and a registration date. If the user so wishes, you can not add any name and it will be called "ANONYMOUS". There may be more than one “ANONYMOUS” player. Each player can see a list of all the rolls they have made, with the value of each data and whether they have won or not the match. In addition, you can know your success rate for all the rolls you have made.

You cannot delete a specific game, but you can delete the entire list of spins by a player.

The software must allow listing all the players in the system, the success rate of each player and the average success rate of all the players in the system.

The software must respect the main design patterns. GRADES

You have to take into account the following construction details: -

URL's:

POST: /players: create a player.

PUT /players: modifies the name of the player.

POST /players/{id}/games/ : a specific player rolls the dice.

DELETE /players/{id}/games – Deletes the player's rolls.

GET /players/: returns the list of all the players in the system with their average percentage of successes.

GET /players/{id}/games: returns the list of games played by a player.

GET /players/ranking: returns the average ranking of all players in the system. That is, the average percentage of achievements.

GET /players/ranking/loser: returns the player with the worst success rate.

GET /players/ranking/winner: Returns the player with the worst success rate.

Swagger:

http://localhost:8080/swagger-ui/index.html#/ springdoc.api-docs.path = /dicegame-V-sql-openapi

Phase 1 Persistence: uses MySQL as database.

Phase 2 Change everything you need and use MongoDB to persist the data.

Phase 3 Add security: include JWT authentication in all accesses to the URL's of the microservice.
