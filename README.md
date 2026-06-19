# Examen 3 - API Pokémon 🔮

Este proyecto es una API REST desarrollada con **Java** y **Spring Boot** para gestionar entrenadores y capturas de Pokémon en el sistema. Además, incluye una interfaz web interactiva (Frontend SPA) incorporada directamente en el servidor para facilitar las pruebas del proyecto.

---

## 🛠️ Tecnologías y Requisitos
* **Lenguaje:** Java 17 o superior.
* **Framework:** Spring Boot 3.4.1.
* **Base de Datos:** H2 Database (en memoria, no requiere instalación externa).
* **Gestor de Dependencias:** Maven.
* **Documentación:** Swagger / OpenAPI 3.

---

## 🚀 Cómo Ejecutar el Proyecto Localmente

### Opción 1: Ejecución directa con el archivo JAR (Recomendada para compatibilidad de Java)
Si tu equipo tiene una versión muy reciente de Java (como Java 21 o Java 25), ejecuta la aplicación empaquetada para evitar conflictos con los plugins de Maven:
```bash
# Navegar a la carpeta del proyecto
cd pokemon

# Compilar y empaquetar el proyecto
mvn clean package -DskipTests

# Ejecutar el archivo compilado
java -jar target/pokemon-0.0.1-SNAPSHOT.jar
```

### Opción 2: Ejecución a través del Plugin de Maven
Si cuentas con Java 17 o compatible:
```bash
cd pokemon
mvn spring-boot:run
```

---

## 💻 Herramientas y Acceso
Una vez levantado el servidor, puedes acceder a los siguientes enlaces en tu navegador:
* **Frontend Interactivo de Pruebas:** [http://localhost:8080/](http://localhost:8080/)
* **Documentación Interactiva de la API (Swagger UI):** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **Consola de Base de Datos H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  * **JDBC URL:** `jdbc:h2:mem:pokemondb`
  * **User Name:** `sa`
  * **Password:** *(dejar vacío)*

### 🌐 Acceso desde otros dispositivos (Misma red local)
Si el profesor u otro usuario desea abrir la interfaz o Swagger desde otra computadora o dispositivo conectado a la misma red:
1. Obtén tu dirección IP local ejecutando el comando `ipconfig` en la terminal (busca la **Dirección IPv4**, por ejemplo: `192.168.1.15`).
2. En el navegador del otro dispositivo, reemplaza `localhost` por tu IP local:
   * **Frontend:** `http://192.168.1.15:8080/`
   * **Swagger:** `http://192.168.1.15:8080/swagger-ui.html`
3. *Nota:* Si la conexión falla en el otro dispositivo, asegúrate de desactivar temporalmente el Firewall de Windows en la computadora que ejecuta el servidor para permitir tráfico en el puerto `8080`.

---

## 📋 Datos de Prueba Precargados (Base de Datos Inicial)
La base de datos se inicializa automáticamente al arrancar con los siguientes datos:

### 1. Entrenadores
| Nombre | Apellido | Correo Electrónico | UUID (Identificador Único) |
| :--- | :--- | :--- | :--- |
| **Ash** | Ketchum | `ash@gmail.com` | `f3262c24-473d-437d-a5cf-e87676637954` |
| **Misty** | Williams | `misty@gmail.com` | `eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee` |

### 2. Tipos de Pokémon
| Tipo | UUID (Identificador Único) |
| :--- | :--- |
| **Planta** | `11111111-1111-1111-1111-111111111111` |
| **Agua** | `22222222-2222-2222-2222-222222222222` |
| **Fuego** | `33333333-3333-3333-3333-333333333333` |
| **Eléctrico** | `44444444-4444-4444-4444-444444444444` |

### 3. Pokémon Registrados
| Nombre | Descripción | Generación | UUID (Identificador Único) | Tipo |
| :--- | :--- | :--- | :--- | :--- |
| **Bulbasaur** | Pokémon semilla | 1 | `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` | Planta |
| **Squirtle** | Pokémon tortuga | 1 | `bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb` | Agua |
| **Charmander** | Pokémon lagarto | 1 | `cccccccc-cccc-cccc-cccc-cccccccccccc` | Fuego |
| **Pikachu** | Pokémon ratón | 1 | `dddddddd-dddd-dddd-dddd-dddddddddddd` | Eléctrico |

---

## 📡 Endpoints de la API y Guía de Peticiones

### 1. Login del Entrenador
* **Método:** `POST`
* **Ruta:** `/entrenador/login`
* **Cuerpo de la Petición (JSON):**
  ```json
  {
    "email": "ash@gmail.com"
  }
  ```
* **Respuesta Esperada (200 OK):**
  ```json
  {
    "uid": "f3262c24-473d-437d-a5cf-e87676637954"
  }
  ```

### 2. Listar Pokémon por Tipo
* **Método:** `GET`
* **Ruta:** `/pokemons/{tipo}`
* **Ejemplo (Tipo Fuego):** `GET http://localhost:8080/pokemons/33333333-3333-3333-3333-333333333333`
* **Respuesta Esperada (200 OK):**
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

### 3. Registrar un Pokémon en el Sistema
* **Método:** `POST`
* **Ruta:** `/pokemons`
* **Cuerpo de la Petición (JSON):**
  ```json
  {
    "nombre": "Chikorita",
    "descripcion": "Pokémon tipo hoja",
    "fechaDescubrimiento": "1999-11-21",
    "generacion": 2,
    "tipoPokemon": "11111111-1111-1111-1111-111111111111"
  }
  ```

### 4. Listar Pokémon Asociados a un Entrenador (Mochila)
* **Método:** `GET`
* **Ruta:** `/entrenador/{uuid}/pokemons`
* **Ejemplo:** `GET http://localhost:8080/entrenador/f3262c24-473d-437d-a5cf-e87676637954/pokemons`

### 5. Capturar Pokémon (Asociar a un Entrenador)
* **Método:** `POST`
* **Ruta:** `/entrenador/{uuid}/pokemons/{uuidPokemon}`
* **Ejemplo:** `POST http://localhost:8080/entrenador/f3262c24-473d-437d-a5cf-e87676637954/pokemons/aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa`
* **Respuesta Esperada (200 OK):**
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
* **Respuesta si el Pokémon ya está capturado (400 Bad Request):**
  ```json
  {
    "error": true,
    "message": "pokemon ya esta registrado al entrenador"
  }
  ```
