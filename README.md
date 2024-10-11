# challenge-conversor-de-monedas
## Qué es
Consiste en un programa por consola el cual permite realizar conversiones entre 20 monedas distintas del mundo, además de guardar un registro de estas operaciones.

<div align= "center">
  <img src="https://github.com/user-attachments/assets/23516552-a795-4740-9d42-7c23244a9098" alt="Menu del conversor de monedas">
</div>

---

### 1. Convertir monedas
Esta opcion permite elegir entre 20 monedas entre las cuales realizar la conversión de la cantidad solicitada, siendo las siguiente monedas las pertenecientes al menu.

<div align= "center">
  <img src="https://github.com/user-attachments/assets/23516552-a795-4740-9d42-7c23244a9098](https://github.com/user-attachments/assets/e036660f-1219-4e5e-8151-56c9c1df98d5" alt="Lista de monedas disponibles">
</div>

`Convertir monedas` permite elegir una moneda de origen y una de destino a la que realizar la conversión para luego pedir la cantidad correspondiente a convertir. El proceso de conversión se realiza mediante el uso de la API [ExchangeRate](https://www.exchangerate-api.com/) y se almacenan los datos respectivos a la conversión en un archivo `.txt` dentro del mismo proyecto a forma de historial.

---

### Ver historial
Esta opcion permite ver el historial de transacciones almacenadas mediante el siguiente formato `Origen: ###, Destino: ###, Cantidad: #.##, Fecha: yyyy/MM/dd HH:mm:ss`.

<div align= "center">
  <img src="https://github.com/user-attachments/assets/263dc291-f92d-453d-a717-abc5cc18c985" alt="Ejemplo de historial">
</div>

---

### Salir
Esta opción permite cerrar el programa.

## Desarrollado por

<div align="center">
  
  <img src="https://github.com/user-attachments/assets/b678a2e8-5a8c-428b-819f-34bd88741a72" style="width: 300px; height: 300px;">
  
  *Juan Rodríguez*
</div>

Como parte del programa [ONE](https://www.oracle.com/co/education/oracle-next-education/).
