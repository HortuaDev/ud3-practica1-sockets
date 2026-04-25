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
