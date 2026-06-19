# API Pokémon 🔮

API REST construida con **Java** y **Spring Boot** para gestionar entrenadores y capturas de Pokémon. Incluye una interfaz web interactiva incorporada en el servidor para facilitar las pruebas sin necesidad de herramientas externas.

---

## 🛠️ Tecnologías Utilizadas
| Tecnología | Descripción |
| :--- | :--- |
| **Java 17+** | Lenguaje principal del proyecto |
| **Spring Boot 3.4.1** | Framework para la API REST |
| **H2 Database** | Base de datos en memoria (no requiere instalación) |
| **Maven** | Gestor de dependencias y construcción |
| **Swagger / OpenAPI 3** | Documentación interactiva de la API |

---

## 🚀 Cómo Ejecutar el Proyecto

### Opción 1: JAR directo (Recomendado — compatible con todas las versiones de Java)
```bash
# Desde la carpeta raíz del proyecto (donde está el pom.xml)
mvn clean package -DskipTests

# Ejecutar
java -jar target/pokemon-0.0.1-SNAPSHOT.jar
```

### Opción 2: Plugin de Maven (Java 17 compatible)
```bash
mvn spring-boot:run
```

> **Nota:** Si tienes Java 21 o superior instalado, usa la **Opción 1** para evitar conflictos de compatibilidad con el plugin de Maven.

---

## 💻 Acceso a la Aplicación (Local)
Una vez que el servidor esté corriendo, abre tu navegador e ingresa a los siguientes enlaces:

| Herramienta | URL |
| :--- | :--- |
| **Interfaz Web de Pruebas** | http://localhost:8080/ |
| **Swagger UI (Documentación interactiva)** | http://localhost:8080/swagger-ui.html |
| **Consola Base de Datos H2** | http://localhost:8080/h2-console |

#### Credenciales para la Consola H2:
* **JDBC URL:** `jdbc:h2:mem:pokemondb`
* **User Name:** `sa`
* **Password:** *(dejar vacío)*

---

## 🌐 Acceso desde otro Dispositivo (Misma red Wi-Fi o LAN)
Para acceder a la aplicación desde otra computadora, celular o tablet conectado a la misma red:

1. En la computadora que ejecuta el servidor, abre la terminal y ejecuta:
   ```cmd
   ipconfig
   ```
2. Localiza tu **Dirección IPv4** (ejemplo: `192.168.1.15`).
3. Desde el otro dispositivo, reemplaza `localhost` por esa IP en el navegador:
   * **Interfaz Web:** `http://192.168.1.15:8080/`
   * **Swagger UI:** `http://192.168.1.15:8080/swagger-ui.html`

> **Importante:** Si la conexión no responde, verifica que el Firewall de Windows en el equipo host permita el tráfico en el puerto `8080`. Puedes desactivarlo temporalmente para pruebas.

---

## 📋 Datos Iniciales de la Base de Datos
La base de datos se inicializa automáticamente al arrancar la aplicación con los siguientes registros:

### Entrenadores
| Nombre | Apellido | Correo | UUID |
| :--- | :--- | :--- | :--- |
| **Ash** | Ketchum | `ash@gmail.com` | `f3262c24-473d-437d-a5cf-e87676637954` |
| **Misty** | Williams | `misty@gmail.com` | `eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee` |

### Tipos de Pokémon
| Tipo | UUID |
| :--- | :--- |
| **Planta** | `11111111-1111-1111-1111-111111111111` |
| **Agua** | `22222222-2222-2222-2222-222222222222` |
| **Fuego** | `33333333-3333-3333-3333-333333333333` |
| **Eléctrico** | `44444444-4444-4444-4444-444444444444` |

### Pokémon Registrados
| Nombre | Descripción | Gen. | UUID | Tipo |
| :--- | :--- | :---: | :--- | :--- |
| **Bulbasaur** | Pokémon semilla | 1 | `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` | Planta |
| **Squirtle** | Pokémon tortuga | 1 | `bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb` | Agua |
| **Charmander** | Pokémon lagarto | 1 | `cccccccc-cccc-cccc-cccc-cccccccccccc` | Fuego |
| **Pikachu** | Pokémon ratón | 1 | `dddddddd-dddd-dddd-dddd-dddddddddddd` | Eléctrico |

---

## 📡 Endpoints de la API

### 1. `POST /entrenador/login` — Login del Entrenador
Recibe el correo electrónico de un entrenador y retorna su UUID de identificación.

**Cuerpo de la petición:**
```json
{
  "email": "ash@gmail.com"
}
```
**Respuesta exitosa (200 OK):**
```json
{
  "uid": "f3262c24-473d-437d-a5cf-e87676637954"
}
```

---

### 2. `GET /pokemons/{tipo}` — Listar Pokémon por Tipo
Retorna todos los Pokémon del sistema que pertenecen al tipo indicado. El parámetro `{tipo}` es el UUID del tipo.

**Ejemplo de petición (Tipo Fuego):**
```
GET http://localhost:8080/pokemons/33333333-3333-3333-3333-333333333333
```
**Respuesta exitosa (200 OK):**
```json
[
  {
    "id": 3,
    "nombre": "Charmander",
    "descripcion": "Pokemon lagarto",
    "fechaDescubrimiento": "1996-02-27",
    "generacion": 1,
    "uuid": "cccccccc-cccc-cccc-cccc-cccccccccccc",
    "tipoPokemon": {
      "id": 3,
      "descripcion": "Fuego",
      "uuid": "33333333-3333-3333-3333-333333333333"
    }
  }
]
```

---

### 3. `POST /pokemons` — Registrar un Nuevo Pokémon
Crea y guarda un nuevo Pokémon en el sistema.

**Cuerpo de la petición:**
```json
{
  "nombre": "Chikorita",
  "descripcion": "Pokémon tipo hoja",
  "fechaDescubrimiento": "1999-11-21",
  "generacion": 2,
  "tipoPokemon": "11111111-1111-1111-1111-111111111111"
}
```

---

### 4. `GET /entrenador/{uuid}/pokemons` — Ver Pokémon de un Entrenador
Retorna la lista de Pokémon que tiene capturados el entrenador indicado por su UUID.

**Ejemplo de petición:**
```
GET http://localhost:8080/entrenador/f3262c24-473d-437d-a5cf-e87676637954/pokemons
```

---

### 5. `POST /entrenador/{uuid}/pokemons/{uuidPokemon}` — Capturar Pokémon
Asocia un Pokémon existente al entrenador indicado. Si el Pokémon ya fue capturado anteriormente, retorna un mensaje de error.

**Ejemplo de petición:**
```
POST http://localhost:8080/entrenador/f3262c24-473d-437d-a5cf-e87676637954/pokemons/aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa
```
**Respuesta exitosa (200 OK):**
```json
{
  "error": false,
  "pokemones": [
    {
      "id": 1,
      "nombre": "Bulbasaur",
      "descripcion": "Pokemon semilla",
      "fechaDescubrimiento": "1996-02-27",
      "generacion": 1,
      "uuid": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
      "tipoPokemon": {
        "id": 1,
        "descripcion": "Planta",
        "uuid": "11111111-1111-1111-1111-111111111111"
      }
    }
  ]
}
```
**Si el Pokémon ya está registrado (400 Bad Request):**
```json
{
  "error": true,
  "message": "pokemon ya esta registrado al entrenador"
}
```
