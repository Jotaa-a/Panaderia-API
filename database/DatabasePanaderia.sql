drop database if exists panaderia;
create database if not exists panaderia
	character set utf8mb4 collate utf8mb4_unicode_ci;
use panaderia;

create table rol (
	rol_id int primary key auto_increment,
	nombre varchar(255) not null unique
);

create table categoria (
	categoria_id int primary key auto_increment,
	nombre varchar(100) not null unique,
	activo boolean not null default true
);

create table ingrediente (
	ingrediente_id int primary key auto_increment,
	nombre varchar(255) not null unique,
	costo_unitario decimal(10,2) not null,
	stock_actual decimal (12,3) not null default 0,
	stock_minimo decimal (12,3) not null default 0,
	unidad varchar(10) not null,
	activo boolean not null default true
);

create table producto (
	producto_id int primary key auto_increment,
	nombre varchar(100) not null unique,
	descripción varchar(255),
	categoria_id int not null,
	precio decimal(10,2) not null check (precio >= 0),
	stock decimal (10,2) not null default 0 check (stock >= 0),
	imagen_url varchar(255),
	unidad enum('KG', 'UND', 'L', 'G') not null,
	activo boolean not null default true,
	foreign key (categoria_id) references categoria(categoria_id),
	index idx_producto_nombre (nombre)
);

create table usuario (
	usuario_id int primary key auto_increment,
	rol_id int not null,
	nombre varchar(100) unique not null,
	email varchar(120) not null unique,
	password varchar(255) not null,
	activo boolean not null default true,
	creado_en timestamp not null default current_timestamp,
	foreign key (rol_id) references rol(rol_id)
);

create table movimiento_inventario (
	movimiento_id int primary key auto_increment,
	ingrediente_id int not null,
	descripción  varchar(255) not null,
	cantidad decimal(12,3) not null,
	nota varchar(255),
	fecha timestamp default(current_timestamp) not null,
	usuario_id int not null,
	foreign key(usuario_id) references usuario(usuario_id),
	foreign key (ingrediente_id) references ingrediente(ingrediente_id)
);

create table venta (
	venta_id int primary key auto_increment,
	usuario_id int not null,
	fecha timestamp default(current_timestamp) not null,
	metodo_pago enum ('EFECTIVO', 'TRANSFERENCIA', 'TARJETA') not null,
	total decimal(10,2) not null,
	estado enum('COMPLETADA', 'ANULADA') not null default 'COMPLETADA',
	foreign key (usuario_id) references usuario(usuario_id),
	index idx_venta_fecha (fecha)
);

create table detalle_venta (
	detalle_id int primary key auto_increment,
	producto_id int not null,
	venta_id int not null,
	cantidad int not null check (cantidad >= 0),
	subtotal decimal(10,2) not null,
	foreign key (producto_id) references producto(producto_id),
	foreign key (venta_id) references venta(venta_id)
);

-- seed data
insert into rol (nombre) values ('ADMIN'), ('CAJERO'), ('PANADERO');
insert into categoria (nombre) values ('Pan'), ('Pasteleria'), ('Tortas'), ('Bebidas');

show tables;