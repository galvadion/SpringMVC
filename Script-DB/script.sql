
drop database if exists "proyecto-desa";

CREATE DATABASE "proyecto-desa";

CREATE TABLE public.users
(
  dtype character varying(31) NOT NULL,
  id integer NOT NULL,
  active boolean,
  address character varying(255),
  email character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  phone character varying(255),
  birth_date date,
  license_expiration_date date,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
);


CREATE TABLE public.branch_offices
(
  id integer NOT NULL,
  address character varying(255),
  aperture_hour time without time zone,
  city character varying(255),
  closed boolean NOT NULL,
  closing_hour time without time zone,
  coordinatex character varying(255),
  coordinatey character varying(255),
  name character varying(255),
  latitude character varying(255),
  longitude character varying(255),
  CONSTRAINT branch_offices_pkey PRIMARY KEY (id)
);

CREATE TABLE public.rent
(
  id integer NOT NULL,
  delivery_date date,
  final_amount real,
  initial_amount real,
  return_date date,
  status_at_return character varying(255),
  transaction_date date,
  transaction_number character varying(255),
  client_id integer,
  CONSTRAINT rent_pkey PRIMARY KEY (id),
  CONSTRAINT fkb57gnrpgrpitywmxk3x9hujgq FOREIGN KEY (client_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE public.brand
(
  id integer NOT NULL,
  name character varying(255) NOT NULL,
  unavailable boolean NOT NULL,
  CONSTRAINT brand_pkey PRIMARY KEY (id),
  CONSTRAINT uk_b14lvebnt5suo43obgxmgwaim UNIQUE (name)
);


CREATE TABLE public.category
(
  id integer NOT NULL,
  base_price real,
  unavailable boolean NOT NULL,
  name character varying(255),
  CONSTRAINT category_pkey PRIMARY KEY (id),
  CONSTRAINT category_base_price_check CHECK (base_price >= 0::double precision)
);

CREATE TABLE public.fuel
(
  id integer NOT NULL,
  fuel_price real,
  fuel_type character varying(255),
  unavailable boolean NOT NULL,
  CONSTRAINT fuel_pkey PRIMARY KEY (id)
);

CREATE TABLE public.model
(
  id integer NOT NULL,
  airconditioner boolean NOT NULL,
  cylinders integer NOT NULL,
  full_tank real,
  insurance real,
  luggage integer NOT NULL,
  name character varying(255) NOT NULL,
  passangers integer NOT NULL,
  transmission character varying(255),
  unavailable boolean NOT NULL,
  year integer NOT NULL,
  brand_id integer NOT NULL,
  category_id integer,
  fuel_id integer,
  CONSTRAINT model_pkey PRIMARY KEY (id),
  CONSTRAINT fkbw7cbpep55csvnwxru7fkt8f8 FOREIGN KEY (fuel_id)
      REFERENCES public.fuel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkiu7vkhrkogpwhop1n641ldmbx FOREIGN KEY (category_id)
      REFERENCES public.category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fks1nbkhrrlf37dgo824p6vd8cq FOREIGN KEY (brand_id)
      REFERENCES public.brand (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT model_full_tank_check CHECK (full_tank >= 0::double precision),
  CONSTRAINT model_insurance_check CHECK (insurance >= 0::double precision),
  CONSTRAINT model_luggage_check CHECK (luggage >= 1),
  CONSTRAINT model_passangers_check CHECK (passangers >= 2)
);

CREATE TABLE public.vehicle
(
  id integer NOT NULL,
  chasis_number character varying(255),
  color character varying(255),
  description character varying(255),
  kilometers integer,
  license_plate character varying(255),
  license_plate_expiration_date date,
  motor_number character varying(255),
  observations character varying(255),
  state character varying(255),
  unavailable boolean NOT NULL,
  branch_office_id integer NOT NULL,
  fuel_id integer NOT NULL,
  model_id integer NOT NULL,
  CONSTRAINT vehicle_pkey PRIMARY KEY (id),
  CONSTRAINT fk2utprsuhc7wyhwyk4ppsdqjak FOREIGN KEY (model_id)
      REFERENCES public.model (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk5bvaogehqj8k2rei26nyoriy4 FOREIGN KEY (fuel_id)
      REFERENCES public.fuel (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkev0xdmjg9ri8r0efmtu4l2im0 FOREIGN KEY (branch_office_id)
      REFERENCES public.branch_offices (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
 

CREATE TABLE public.booked
(
  id integer NOT NULL,
  begin_booked_date date,
  canceled boolean NOT NULL,
  initial_amount real,
  last_booked_date date,
  transaction_date date,
  transaction_number character varying(255),
  with_full_tank boolean,
  with_gps boolean,
  with_insurance boolean,
  client_id integer NOT NULL,
  end_office_id integer NOT NULL,
  origin_office_id integer NOT NULL,
  rent_id integer,
  vehicle_id integer NOT NULL,
  CONSTRAINT booked_pkey PRIMARY KEY (id),
  CONSTRAINT fk1ogc09n8kd844j3x7psybqi9i FOREIGN KEY (client_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk515giy7xyh4w2f8nopi857vmk FOREIGN KEY (end_office_id)
      REFERENCES public.branch_offices (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkhotowsv79lh5cn9deqe0hd5am FOREIGN KEY (origin_office_id)
      REFERENCES public.branch_offices (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkjym0ow29ec5iwva6pwrtke491 FOREIGN KEY (rent_id)
      REFERENCES public.rent (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkpgx3j5gbtqcovftwyl1nnu5w0 FOREIGN KEY (vehicle_id)
      REFERENCES public.vehicle (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_qg687txlj3qpa6as9poh34346 UNIQUE (rent_id)
);






CREATE TABLE public.extras
(
  id integer NOT NULL,
  name character varying(255),
  price real,
  CONSTRAINT extras_pkey PRIMARY KEY (id)
);





CREATE TABLE public.image
(
  id integer NOT NULL,
  file_location character varying(255),
  model_id integer NOT NULL,
  CONSTRAINT image_pkey PRIMARY KEY (id),
  CONSTRAINT fkruhlfhk3n2cngefw4bh4c3wps FOREIGN KEY (model_id)
      REFERENCES public.model (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




CREATE TABLE public.images
(
  id integer NOT NULL,
  file_location character varying(255),
  model_id integer NOT NULL,
  CONSTRAINT images_pkey PRIMARY KEY (id),
  CONSTRAINT fk5nl8qe5ed481ukif0y6xd6o5 FOREIGN KEY (model_id)
      REFERENCES public.model (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);







CREATE TABLE public.rentfare
(
  id integer NOT NULL,
  gps real,
  CONSTRAINT rentfare_pkey PRIMARY KEY (id)
);


CREATE TABLE public.rentline
(
  rent_id integer NOT NULL,
  rent_line_id integer NOT NULL,
  amount real,
  detail character varying(255),
  CONSTRAINT rentline_pkey PRIMARY KEY (rent_id, rent_line_id),
  CONSTRAINT fkp0mt4x2xr4xynxmdxsqdw5c FOREIGN KEY (rent_id)
      REFERENCES public.rent (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE public.status_in_dates
(
  id integer NOT NULL,
  begin_date date,
  end_date date,
  status character varying(255),
  branch_office_id integer NOT NULL,
  vehicle_id integer NOT NULL,
  CONSTRAINT status_in_dates_pkey PRIMARY KEY (id),
  CONSTRAINT fkeretsflxa01re48peinpkyb25 FOREIGN KEY (vehicle_id)
      REFERENCES public.vehicle (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fklbrobpf67yjnfb8pequ8vekqf FOREIGN KEY (branch_office_id)
      REFERENCES public.branch_offices (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);






/*Insertar datos*/

/* branch_offices */

INSERT INTO public.branch_offices(
            id, address, aperture_hour, city, closed, closing_hour, coordinatex, 
            coordinatey, name, latitude, longitude)
    VALUES (1, '8 de octubre 2475', '09:00:00', 'Montevideo', 'FALSE', '21:00:00', null, 
            null, 'sucursal union', -34.877924, -56.145825);

INSERT INTO public.branch_offices(
            id, address, aperture_hour, city, closed, closing_hour, coordinatex, 
            coordinatey, name, latitude, longitude)
    VALUES (2, '18 de julio 1022', '09:00:00', 'Montevideo', 'FALSE', '21:00:00', null, 
            null, 'sucursal centro', -34.906203, -56.194136);

INSERT INTO public.branch_offices(
            id, address, aperture_hour, city, closed, closing_hour, coordinatex, 
            coordinatey, name, latitude, longitude)
    VALUES (3, 'Miguel Barreiro 574', '09:00:00', 'Melo', 'FALSE', '18:00:00', null, 
            null, 'sucursal Melo', -32.369220, -54.163635);

/* Brand */

INSERT INTO public.brand(
            id, name, unavailable)
    VALUES (1, 'Chevrolet', 'FALSE');

INSERT INTO public.brand(
            id, name, unavailable)
    VALUES (2, 'Fiat', 'FALSE');

INSERT INTO public.brand(
            id, name, unavailable)
    VALUES (3, 'Ford', 'FALSE');


/* Category */

INSERT INTO public.category(
            id, base_price, unavailable, name)
    VALUES (1, 5000, 'FALSE', 'S');

INSERT INTO public.category(
            id, base_price, unavailable, name)
    VALUES (2, 3000, 'FALSE', 'A');

INSERT INTO public.category(
            id, base_price, unavailable, name)
    VALUES (3, 2000, 'FALSE', 'B');

INSERT INTO public.category(
            id, base_price, unavailable, name)
    VALUES (4, 1000, 'FALSE', 'C');

/* Fuel */

INSERT INTO public.fuel(
            id, fuel_price, fuel_type, unavailable)
    VALUES (1, 50, 'Nafta', 'FALSE');

INSERT INTO public.fuel(
            id, fuel_price, fuel_type, unavailable)
    VALUES (2, 30, 'Gasoil', 'FALSE');

/* Model */

INSERT INTO public.model(
            id, airconditioner, cylinders, full_tank, insurance, luggage, 
            name, passangers, transmission, unavailable, year, brand_id, 
            category_id, fuel_id)
    VALUES (1, 'TRUE', 1000, 2500, 2000, 280, 
            'Onix', 5, 'M', 'FALSE', 2015, 1, 2, 1);

INSERT INTO public.model(
            id, airconditioner, cylinders, full_tank, insurance, luggage, 
            name, passangers, transmission, unavailable, year, brand_id, 
            category_id, fuel_id)
    VALUES (2, 'TRUE', 1400, 2500, 3000, 290, 
            'Palio', 5, 'A', 'FALSE', 2014, 2, 3, 1);

INSERT INTO public.model(
            id, airconditioner, cylinders, full_tank, insurance, luggage, 
            name, passangers, transmission, unavailable, year, brand_id, 
            category_id, fuel_id)
    VALUES (3, 'TRUE',1600, 2500, 3000, 281, 
            'Fiesta', 4, 'M', 'FALSE', 2016, 3, 1, 1);



/* Rent */

/* Rent Fare */

/* Vehicle */

INSERT INTO public.vehicle(
            id, chasis_number, color, description, kilometers, license_plate, 
            license_plate_expiration_date, motor_number, observations, state, 
            unavailable, branch_office_id, fuel_id, model_id)
    VALUES (1, 'wdb38011314796192', 'Gris', 'Todo el diseño y la conectividad, en un auto que sale de lo común El nuevo Chevrolet Onix sale de lo común. Por fuera, un diseño innovador, agresivo y juvenil.', 0, 'SBB 8042', 
            '1/10/2017', 'AR1315 057224', 'Buen Estado', 'Bien', 
            'FALSE', 1, 1, 1);

INSERT INTO public.vehicle(
            id, chasis_number, color, description, kilometers, license_plate, 
            license_plate_expiration_date, motor_number, observations, state, 
            unavailable, branch_office_id, fuel_id, model_id)
    VALUES (2, 'wdb51697513648547', 'Rojo', 'Se destaca por su contenido consumo de combustible, niveles de emisiones acotados, bajos índices de ruidos y vibraciones y alta confiabilidad.', 0, 'SBB 8022', '1/5/2017', 'AR4215 052024', 'Buen Estado', 'Bien', 
            'FALSE', 2, 1, 2);

INSERT INTO public.vehicle(
            id, chasis_number, color, description, kilometers, license_plate, 
            license_plate_expiration_date, motor_number, observations, state, 
            unavailable, branch_office_id, fuel_id, model_id)
    VALUES (3, 'wdb54907513123547', 'Negro', 'Dinámicamente el Ford Fiesta muestra todas sus virtudes en ciudad, con una muy buena visibilidad y unas dimensiones perfectas.', 0, 'SBB 8101', '1/12/2017', 'AR1315 047724', 'Buen Estado', 'Bien', 
            'FALSE', 3, 1, 3);


INSERT INTO public.status_in_dates(
            id, begin_date, end_date, status, branch_office_id, vehicle_id)
    VALUES (1, '1/11/2016', '31/12/2016', 'Available', 1, 1);

INSERT INTO public.status_in_dates(
            id, begin_date, end_date, status, branch_office_id, vehicle_id)
    VALUES (2, '1/11/2016', '31/12/2016', 'Available', 2, 2);

INSERT INTO public.status_in_dates(
            id, begin_date, end_date, status, branch_office_id, vehicle_id)
    VALUES (3, '1/11/2016', '31/12/2016', 'Available', 3, 3);


/* User */
INSERT INTO public.users(
            dtype, id, active, address, email, last_name, name, password, 
            phone, birth_date, license_expiration_date)
VALUES ('Admin', 1, 'TRUE', 'Asilo', 'dbecco@gmail.com', 'Becoo', 'Diego', '123456', 
            '123456789', '1/1/2000', '1/07/2018');
