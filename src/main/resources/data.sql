-- ================================================================
-- DATOS INICIALES DE PRUEBA
-- Estos datos se cargan automaticamente al iniciar la aplicacion.
-- ================================================================

-- Tipos de Pokemon
INSERT INTO POKEMON_TIPO_POKEMON (descripcion, uuid) VALUES ('Planta', '11111111-1111-1111-1111-111111111111');
INSERT INTO POKEMON_TIPO_POKEMON (descripcion, uuid) VALUES ('Agua', '22222222-2222-2222-2222-222222222222');
INSERT INTO POKEMON_TIPO_POKEMON (descripcion, uuid) VALUES ('Fuego', '33333333-3333-3333-3333-333333333333');
INSERT INTO POKEMON_TIPO_POKEMON (descripcion, uuid) VALUES ('Electrico', '44444444-4444-4444-4444-444444444444');

-- Pokemones
INSERT INTO POKEMON (nombre, descripcion, generacion, fecha_descubrimiento, uuid, tipo_pokemon_id)
  VALUES ('Bulbasaur', 'Pokemon semilla', 1, '1996-02-27', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 1);
INSERT INTO POKEMON (nombre, descripcion, generacion, fecha_descubrimiento, uuid, tipo_pokemon_id)
  VALUES ('Squirtle', 'Pokemon tortuga', 1, '1996-02-27', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 2);
INSERT INTO POKEMON (nombre, descripcion, generacion, fecha_descubrimiento, uuid, tipo_pokemon_id)
  VALUES ('Charmander', 'Pokemon lagarto', 1, '1996-02-27', 'cccccccc-cccc-cccc-cccc-cccccccccccc', 3);
INSERT INTO POKEMON (nombre, descripcion, generacion, fecha_descubrimiento, uuid, tipo_pokemon_id)
  VALUES ('Pikachu', 'Pokemon raton', 1, '1996-02-27', 'dddddddd-dddd-dddd-dddd-dddddddddddd', 4);

-- Entrenadores (con email para el login)
INSERT INTO ENTRENADOR (nombre, apellido, fecha_nacimiento, fecha_vinculacion, uuid, email)
  VALUES ('Ash', 'Ketchum', '1986-05-22', '2023-01-01', 'f3262c24-473d-437d-a5cf-e87676637954', 'ash@gmail.com');
INSERT INTO ENTRENADOR (nombre, apellido, fecha_nacimiento, fecha_vinculacion, uuid, email)
  VALUES ('Misty', 'Williams', '1988-10-10', '2023-01-01', 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'misty@gmail.com');
