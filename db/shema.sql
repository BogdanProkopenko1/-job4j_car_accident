CREATE TABLE IF NOT EXISTS type(
    id serial primary key,
    name varchar(512) unique not null
);

CREATE TABLE IF NOT EXISTS rule(
    id serial primary key,
    name text unique not null
);

CREATE TABLE IF NOT EXISTS accident(
    id serial primary key,
    name varchar(512) not null,
    description text not null,
    address varchar (512) not null,
    accident_type_id int references type(id) not null
);

CREATE TABLE IF NOT EXISTS accident_rule(
    accident_id int references accident(id) not null,
    rule_id int references rule(id) not null
);