# portero-unidad-rabbitMQ
proyecto creado para la creación de colas de mensajería de una unidad con exchange topic y fanout 

## Funcionamiento

* Enviar la peticion al exchange header-piso

Metodo: Get http://localhost:8081/broker/producer/apto/impares

Query

| clave         | Valor                                |
| ------------- | ------------------------------------ |
| exchangeName  | header-exchange                      |
| apto          | impar                                |
| messageData   | Correo para los apartamentos impares |  


* Enviar la peticion al exchange fanout-piso

Metodo: Get http://localhost:8081/broker/producer/pisos/

Query

| clave         | Valor                                |
| ------------- | ------------------------------------ |
| exchangeName  | fanout-exchange                      |
| messageData   | Correo para todo el piso             |
