use trabajo;
show tables;

desc insumo;
alter table insumo
add column PRECIO_UNITARIO double(5,2) default 0.0;

alter table insumo
drop column precio_unitario;
select * from insumo
where id_insumo = 1;

desc notificacion;
desc insumo;

select * from insumo;


alter table insumo
drop column tipo;
alter table insumo
add column TIPO varchar(50) default 'Tipo Cantidad';

create table if not exists Acciones(
	id_accion int(30) primary key auto_increment,
    cantidad_accion double (10,2),
    producto_accion varchar(100),
    descripcion_accion varchar(50),
    fecha_accion timestamp default current_timestamp,
    usuario_accion varchar(100)
);
select * from usuario;

insert into Acciones(cantidad_accion, producto_accion, descripcion_accion) values
(2.3, 'Manzana', 'Ingreso Nuevo Producto');
select * from Acciones;
select * from Acciones;
drop table Acciones;
DELIMITER //
create trigger controlar_acciones AFTER
INSERT ON INSUMO
FOR EACH ROW
BEGIN

END;
//
DELIMITER ;


select * from insumo
where tipo not in ('Unidades (PZA)', 'Kilogramos (Kg)','Gramos (g)','Miligramos (mg)','Litros (l)','Paquetes (PAQ)','Cajas','Botellas','Tipo Cantidad') and estado = 1;


select * from Insumo
group by tipo;
select * from Acciones where cantidad_accion < 0;
select * from Acciones where cantidad_accion > 0;
select * from Acciones;
select id_accion, cantidad_accion, producto_accion, descripcion_accion, fecha_accion, usuario_accion from Acciones;

select * from Acciones where fecha_accion between '2020-12-07' and '2020-12-08'; 
select * from Acciones where cast(fecha_accion as DATE) = '2020-12-8';
-- Consultar solo por anio
select * from Acciones where year(fecha_accion) = 2020;
-- Consultar solo por mes
select * from Acciones where month(fecha_accion) = 16;
-- Consultar solo por dia
select * from Acciones where day(fecha_accion) = 8;

select * from Acciones 
where year(fecha_accion) = 2020 and month(fecha_accion) = 11 and day(fecha_accion) = 7;

select * from Acciones
WHERE year(fecha_accion) = 2020 and month(fecha_accion) = 2 and day(fecha_accion) = 3 and descripcion_accion = 'Nuevos Insumos';

select * from Acciones
where year(fecha_accion) = 2020 and month(fecha_accion) = 12 and day(fecha_accion) = 7 and descripcion_accion = 'Actualizacion de Insumo';

select * from insumo;
desc insumo;
desc Acciones;

alter table Acciones
add column ultima_cantidad double(10,2) default 0.0;

select * from Acciones;
desc Acciones;
select * from Acciones
where fecha_accion = (select max(fecha_accion) from Acciones);

alter table Acciones
add column fk_id_insumo int(11);
desc Acciones;
alter table Acciones
add foreign key(fk_id_insumo) references insumo(id_insumo);
SELECT * from Acciones;
select ultima_cantidad from Acciones
where fecha_accion = (select max(fecha_accion) from Acciones);

desc insumo;

select * from Insumo
order by precio_unitario desc;


select *, (cantidad * precio_unitario) as Calculo from Insumo
order by Calculo;

select * from Insumo
order by (cantidad * precio_unitario) desc;
desc insumo;

select insumo.nombre, insumo.precio_unitario, acciones.fecha_accion, acciones.cantidad_accion, sum(acciones.cantidad_accion) as SUMA from insumo
inner join acciones on insumo.id_insumo = acciones.fk_id_insumo
where year(acciones.fecha_accion) = 2020 and month(acciones.fecha_accion) = 12 and day(acciones.fecha_accion) = 12
-- group by insumo.nombre
order by acciones.cantidad_accion;


select insumo.nombre, insumo.precio_unitario, if(acciones.cantidad_accion < 0, acciones.cantidad_accion, 0) as Retiros, acciones.fecha_accion, acciones.cantidad_accion, sum(acciones.cantidad_accion) as SUMA from insumo
inner join acciones on insumo.id_insumo = acciones.fk_id_insumo
where year(acciones.fecha_accion) = 2020 and month(acciones.fecha_accion) = 12 and day(acciones.fecha_accion) = 12;


select * from Acciones
where year(fecha_accion) = 2020 and month(fecha_accion) = 12 and day(fecha_accion) = 12;

select i.id_insumo, i.nombre, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where year(a.fecha_accion) = 2020 and month(a.fecha_accion) = 12 and day(a.fecha_accion) = 12
group by i.id_insumo;

-- Cuando no hay ninguna condicion
select i.id_insumo, i.nombre, i.cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
group by i.id_insumo;

-- Cuando hay condiciones
select i.id_insumo, i.nombre, i.cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where year(a.fecha_accion) = 2020 and month(a.fecha_accion) = 12 and day(a.fecha_accion) = 12
group by i.id_insumo
order by Calculo desc;

select * from Acciones
where fk_id_insumo is not null;

select sum(cantidad_accion) from Acciones
where producto_accion = 'n453435';

Delete from Acciones
where fk_id_insumo is null;

select i.id_insumo, i.nombre, i.cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where i.id_insumo = 1
group by i.id_insumo;


select * from mysql.user;

-- Consulta para ver el reporte de un producto en especifico
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo, date(a.fecha_accion) as Fecha from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where  a.fk_id_insumo = 2
group by year(a.fecha_accion), month(a.fecha_accion), day(a.fecha_accion);

select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, a.fecha_accion from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where  a.fk_id_insumo = 2
group by year(a.fecha_accion), month(a.fecha_accion), day(a.fecha_accion);


select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where  year(a.fecha_accion) = 2021  and month(a.fecha_accion) = 1 and day(a.fecha_accion) = 2 and fk_id_insumo = 2
group by year(a.fecha_accion), month(a.fecha_accion), day(a.fecha_accion);

-- Funcion para ver el reporte de un dia en especifico de todos los productos
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where year(a.fecha_accion) = 2021 and month(a.fecha_accion) = 1 and day(a.fecha_accion) = 2
group by i.id_insumo
order by i.id_insumo asc;

-- Funcion para ver el reporte de todos los dias hasta el actual de todos los productos
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
group by i.id_insumo
order by i.id_insumo asc;



select * from acciones
where fk_id_insumo = 1;

select * from acciones
where fk_id_insumo = 2
group by year(fecha_accion), month(fecha_accion), day(fecha_accion);

select i.id_insumo, i.nombre, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where  a.fk_id_insumo = 2
group by year(a.fecha_accion), month(a.fecha_accion), day(a.fecha_accion);


select * from acciones
where fk_id_insumo = 2;
select producto_accion, max(fecha_accion) from acciones;
desc acciones;

alter table acciones
add column ultimo_precio double(10,2) default 0.0;

select producto_accion, last_value(ultima_cantidad) over (partition by fk_id_insumo
order by ultima_cantidad asc

) as ultima from acciones
where fk_id_insumo = 2
group by year(fecha_accion), month(fecha_accion), day(fecha_accion);

select * from acciones
where fk_id_insumo = 2;
select * from acciones
where year(fecha_accion) = 2021 and month(fecha_accion) = 1 and day(fecha_accion) = 2 and fk_id_insumo = 2;

select * from insumo
where estado = true;

-- Consulta de prueba con condiciones
select i.id_insumo, i.nombre, i.cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where year(a.fecha_accion) = 2021 and month(a.fecha_accion) = 1 and day(a.fecha_accion) = 1 and i.id_insumo = 30
group by i.id_insumo
order by Calculo desc;

select i.id_insumo, i.nombre, i.cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, round((i.precio_unitario * sum(abs(a.cantidad_accion))), 2) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where year(a.fecha_accion) = 2020 and month(a.fecha_accion) = 12 and day(a.fecha_accion) = 31 and a.fk_id_insumo = 37
group by a.descripcion_accion;





select * from acciones
where fk_id_insumo = 2;

select * from acciones;

select descripcion_accion from acciones
where descripcion_accion = 'Control Diario' and year(fecha_accion) = '2020' and month(fecha_accion) = '12' and day(fecha_accion) = '31';

-- Desactivar el modo seguro
SET SQL_SAFE_UPDATES = 0;

delete from acciones
where descripcion_accion = 'Control Diario' and year(fecha_accion) = '2020' and month(fecha_accion) = '12' and day(fecha_accion) = '31';

select * from insumo
where estado = 1;
select * from acciones;

desc acciones;
insert into Acciones(cantidad_accion, producto_accion, descripcion_accion) values
(2.3, 'Manzana', 'Ingreso Nuevo Producto');

insert into Acciones(cantidad_accion, producto_accion, descripcion_accion) values
(0, 'Variable', 'Control Diario');

select * from Acciones
where descripcion_accion = 'Control Diario';

select * from insumo
where estado = 1;
select * from acciones
where year(fecha_accion) = 2021 and month(fecha_accion) = 1 and day(fecha_accion) = 1;

select * from acciones
where descripcion_accion = 'Control Diario';

select * from proveedor;
select * from documentacion;

show tables;
desc documentacion;
desc proveedor;

insert into documentacion(nombre_doc, documento, fk_proveedor, estado_doc) values
('Documento Prueba', null, null, 1);

select * from documentacion;

select * from usuario;

select * from insumo
where tipo = 'Kilogramos (Kg)' and estado = 1;

select * from acciones
where year(fecha_accion) = 2021 and month(fecha_accion) = 1 and day(fecha_accion) = 12 and descripcion_accion = 'Control Diario';

select * from insumo
where tipo = 'Unidades (PZA)' and estado = 1;

select * from insumo
where nombre = 'insumo 1';

-- Funcion para ver el reporte de un dia en especifico de todos los productos
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where year(a.fecha_accion) = 2021 and month(a.fecha_accion) = 1 and day(a.fecha_accion) = 12 and i.estado = 1
group by i.id_insumo
order by i.id_insumo asc;

desc acciones;
select * from acciones;
-- Funcion para ver el reporte de todos los dias hasta el actual de todos los productos
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (a.ultimo_precio * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where i.estado = 1 and i.id_insumo = 75
group by i.id_insumo
order by i.id_insumo asc;

select * from acciones
where fk_id_insumo = 75;

select max(id_insumo) from insumo;

select i.id_insumo, i.nombre, sum(i.precio_unitario), sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (i.precio_unitario * sum(abs(a.cantidad_accion))) as Calculo, a.fecha_accion from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where  a.fk_id_insumo = 60
group by year(a.fecha_accion), month(a.fecha_accion), day(a.fecha_accion);



select * from acciones
where fk_id_insumo = 2
group by year(fecha_accion), month(fecha_accion), day(fecha_accion)
order by id_accion desc;

select * from acciones
where year(fecha_accion) = 2021 and month(fecha_accion) = 1 and day(fecha_accion) = 12 and fk_id_insumo = 2;

select * from proveedor
where estado_prov = 1 and empresa_prov = 'Distribuidora Sofia';

SET SQL_SAFE_UPDATES = 1;

delete from insumo
where id_insumo > 68;


show create table insumo;
show create table notificacion;
show create table acciones;
desc notificacion;
desc acciones;
desc insumo;
alter table notificacion drop foreign key notificacion_ibfk_1;
alter table notificacion add constraint FK_INSUMO_IBF foreign key(FK_INSUMO) references insumo(ID_INSUMO) on delete cascade;

alter table acciones drop foreign key FK_INSUMO_IBF1;
alter table acciones add constraint FK_INSUMO_I1 foreign key(fk_id_insumo) references insumo(ID_INSUMO) on delete cascade;


select * from acciones;

-- quitar la restriccion
set foreign_key_checks = off;
set foreign_key_checks = on;


select * from usuario;

desc insumo;

select * from insumo;



select * from notificacion;

alter table insumo
modify PRECIO_UNITARIO double default 0.0;


select * from acciones;
select * from insumo;

delete from insumo
where id_insumo = 68;

-- Valor de Inventario
-- Funcion para el valor de Inventario - Calcula, [Cantidad * Precio_Unidad]
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, avg(a.ultimo_precio) as PrecioUnitario, 
sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (a.ultima_cantidad + sum(a.cantidad_accion)) * avg(a.ultimo_precio) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where i.estado = 1 and date(a.fecha_accion) = '2021-01-14'
group by i.id_insumo
order by i.id_insumo asc;

select * from acciones
where fk_id_insumo = 62 and date(fecha_accion) = '2021-01-13';


select * from acciones
where fk_id_insumo = 63;



select * from acciones
where fk_id_insumo = 76;

desc acciones;


select * from insumo
where id_insumo = 67;



select * from acciones
where fk_id_insumo = 76;


select * from insumo
where id_insumo = 77;

update insumo set precio_unitario = (select precio_unitario from (select precio_unitario from insumo where id_insumo = 67) as tabla)
where id_insumo = 77;

-- Poner cualquier el ultimo_valor_unitario valido
update insumo set precio_unitario = (select ultimo_precio from (select ultimo_precio from acciones 
where fk_id_insumo = 77 and descripcion_accion in ('Ingreso Nuevo Producto', 'Ingreso') order by id_accion desc limit 1) as tabla)
where id_insumo = 77;


select * from insumo;
select * from acciones
where fk_id_insumo = 77;

select ultimo_precio from acciones
where fk_id_insumo = 77 and descripcion_accion in ('Ingreso Nuevo Producto', 'Ingreso') order by id_accion desc
limit 1;

desc acciones;
select * from insumo;

-- Producto mas adquirido
-- Calcular el valor de todos los ingresos
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, avg(a.ultimo_precio) as PrecioUnitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, sum(a.ultimo_precio * if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where i.estado = 1 and i.id_insumo = 65 and a.descripcion_accion in ('Ingreso Nuevo Producto','Ingreso')
group by i.id_insumo
order by i.id_insumo asc;


-- Producto mas utilizado
select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, i.precio_unitario, sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros,
sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, (a.ultimo_precio * sum(abs(a.cantidad_accion))) as Calculo from insumo as i
inner join acciones as a on i.id_insumo = a.fk_id_insumo
where i.estado = 1 and i.id_insumo = 75
group by i.id_insumo
order by i.id_insumo asc;


select * from acciones
where date(fecha_accion) = '2021-01-14';

SET SQL_SAFE_UPDATES = 1;

select * from acciones
where date(fecha_accion) = '2021-01-19';

desc notificacion;

alter table notificacion
drop column tipo;

select * from notificacion;

delete from insumo;

delete from insumo;

select * from usuario;

update usuario set tipo_usuario = 'Usuario'
where tipo_usuario != 'Administrador';

desc insumo;

select * from insumo
where estado = 1;

select i.nombre, n.fk_insumo from notificacion as n
inner join insumo as i on i.id_insumo = n.fk_insumo
where n.estado = 1 and i.estado = 1;

desc acciones;

alter table acciones
add column factura_accion varchar(300) default "Sin Documento";

select * from acciones;
desc proveedor;

select p.id_proveedor,d.fk_proveedor, d.id_doc, d.nombre_doc from proveedor as p
inner join documentacion as d on p.id_proveedor = d.fk_proveedor or d.fk_proveedor is null
where p.estado_prov = 1 and d.estado_doc = 1
order by d.fecha_doc desc;

select * from documentacion;
where fk_proveedor is null and estado_doc = 1;

update proveedor set estado_prov = 1
where id_proveedor = 3;


select * from documentacion
where estado_doc = 1;

select * from proveedor;


show tables;

desc documentacion;

select * from insumo
where nombre like '%aci%';

select * from acciones
order by id_accion desc;

select * from documentacion
where estado_doc = 1
order by fecha_doc desc;

select * from documentacion
order by fecha_doc desc;

desc proveedor;

select id_proveedor, cedula_prov, nombres_prov, apellidos_prov, empresa_prov, email_prov from proveedor;

select * from acciones
order by id_accion desc;


select * from documentacion
where estado_doc = 1;

desc usuario;
desc documentacion;
select * from usuario;

select * from documentacion
where estado_doc = 1 and fk_proveedor = 1;

SET SQL_SAFE_UPDATES = 1;

desc insumo;

select * from usuario; 

select * from documentacion;

select * from acciones
order by fecha_accion desc;

update acciones set factura_accion = 'Sin Documento'
where id_accion = 1196;

desc acciones;

select * from documentacion;
select * from notificacion;
select * from insumo;

select i.id_insumo, i.cantidad, i.descripcion, i.estado, i.imagen, i.nombre, i.precio_unitario, i.tipo, n.cantidad from insumo as i
inner join notificacion as n on i.id_insumo = n.fk_insumo
where i.estado = 1;

desc acciones;

select * from acciones
order by fecha_accion desc;