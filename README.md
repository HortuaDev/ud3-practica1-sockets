# Juego de Dados Multijugador

## Escenario

Aplicación cliente-servidor donde varios jugadores se conectan
a un servidor central para jugar una partida de dados tipo Generala.
El servidor gestiona los turnos, lanza los dados y calcula puntuaciones.
Los clientes se conectan desde distintas máquinas a través de la red.

Este escenario justifica la comunicación en red porque:

- Los jugadores están en máquinas distintas
- El estado del juego debe compartirse en tiempo real
- El servidor actúa como árbitro central

## Roles

### Servidor

- Escucha conexiones en el puerto 5000
- Crea un hilo por cada cliente que se conecta
- Gestiona turnos, rondas y puntuaciones
- Notifica a todos los jugadores de cada evento

### Cliente

- Se conecta al servidor por IP y puerto
- Envía su nombre para registrarse
- Espera su turno y manda LANZAR al pulsar ENTER
- Recibe y muestra todos los mensajes del servidor
