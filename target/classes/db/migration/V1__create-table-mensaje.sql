CREATE TABLE mensajes (
    id SERIAL PRIMARY KEY,
    canal TEXT not null,
    texto TEXT not null,
    fecha_envio TIMESTAMP,  
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);