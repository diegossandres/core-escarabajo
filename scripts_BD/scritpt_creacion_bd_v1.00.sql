-- Database: escarabajo

-- DROP DATABASE escarabajo;

CREATE DATABASE escarabajo
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Colombia.1252'
       LC_CTYPE = 'Spanish_Colombia.1252'
       CONNECTION LIMIT = -1;



//------------------------tabla recorrido-----------------------------//
-- DROP TABLE recorrido;

CREATE TABLE recorrido
(
  id_recorrido serial NOT NULL,
  tipo integer NOT NULL,
  nombre character(250),
  hora_salida character(20),
  dias_recorrido character(20),
  lugar_salida character(250),
  lugar_llegada character(250),
  CONSTRAINT recorrido_pkey PRIMARY KEY (id_recorrido)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE recorrido
  OWNER TO postgres;
//------------------------------------------------------------------//


//------------------------tabla metrica-----------------------------//
-- Table: metrica

-- DROP TABLE metrica;

CREATE TABLE metrica
(
  id_metrica integer NOT NULL,
  nombre_metrica character(250) NOT NULL,
  unidad_medida numeric(18,2) NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE metrica
  OWNER TO postgres;
COMMENT ON TABLE metrica
  IS 'Tabla para almacenar los tipos de métricas que se miden en los recorridos';
  //------------------------------------------------------------------//