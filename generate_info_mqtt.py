import os, random
from time import gmtime, strftime

name_sensor = random.choice(["sensor01H", "sensor.exe", "Sensor comunitario", "Sensor lolero", "Sensor sosero"])
type_sensor = random.choice(["Humedad", "Luz", "Temperatura", "Viento", "Presion"])
value_sensor = str(random.randint(0,100)) + "." + str(random.randint(0,99))
ubication_sensor = random.choice(["Temuco", "Santiago", "Padre las casas", "Valdivia"])
datetime_sensor = strftime("%Y-%m-%d %H:%M", gmtime())
extra_sensor = random.choice(["texto de ejemplo", "datos captado en algún lugar del mundo"])

data =  "'Name' : '{}', 'Type':'{}', 'Value': '{}', 'Ubication': '{}', 'DateTime':'{}','Extra': '{}'".format(name_sensor, type_sensor, value_sensor, ubication_sensor, datetime_sensor, extra_sensor)

command_format = 'mosquitto_pub -h test.mosquitto.org -t "sensores" -m "{' + data + '}" -q 2'

print("Ejecutando...\n")
print(command_format)
os.system(command_format)
print("\nComando ejecutado!")