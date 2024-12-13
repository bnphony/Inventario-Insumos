<div align="center">
  
  # Inventario Insumos
</div>

<div align="center">
  <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/general.PNG" width="80%" alt="Main Screen">
  <br/><br/>
  
  ![GitHub](https://img.shields.io/github/last-commit/bnphony/Inventario-Insumos)
  [![Java](https://img.shields.io/badge/Code-Java-3a75b0)](https://www.java.com/es/)
  [![NetBeans](https://img.shields.io/badge/IDE-NetBeans-add439)](https://netbeans.apache.org/front/main/index.html)
  [![MySQL](https://img.shields.io/badge/DB-MySQL-3e6e93)](https://www.mysql.com/)
  [![Hibernate](https://img.shields.io/badge/ORM-Hibernate-59666c)](https://hibernate.org/orm/releases/4.3/)
  [![Jandex](https://img.shields.io/badge/Library-Jandex-43b3d7)](https://smallrye.io/jandex/jandex/3.1.3/index.html)


</div>

## Indice

- [Sistema de Inventario](#sistema-de-inventario)
  - [Descripción](#descripción)
     - [Tecnologías](#tecnologías)
  - [Dominio](#dominio)
     - [Usuario](#usuario)
     - [Categoría](#categoría)
     - [Producto](#producto)
     - [Cliente](#cliente)
     - [Venta](#venta)
     - [Descripción de la Venta](#descripción-de-la-venta)
  - [Funciones](#funciones)
  - [Autor](#autor)
     - [Contacto](#contacto)
  - [Licencia de Uso](#licencia-de-uso)
 
## Descripción
Sistema de Gestión de Inventarios de Insumos(nombre, descripción, precio, cantidad, unidad de medida, imagen) para medianas/pequeñas empresas.
- Gestionar Usuarios.
- Gestionar Insumos.
- Gestionar Documentación.
- Gestionar Proveedores.
- Gestionar Notificaciones.
- Gestionar insumos y proveedores eliminados.
- Visualizar las acciones realizadas con los insumos.
- Generar Reportes.
- Visualizar los reportes utilizando gráficos de barras.
- Exportar los reportes en formato PDF.
   
### Tecnologías

- Lenguaje de Programación: [Java](https://www.java.com/es/) - Utilizado para desarrollar una aplicación de escritorio para sistema operativo Windows.
- Entorno de Desarrollo: [NetBeans](https://netbeans.apache.org/front/main/index.html) - Facilita la creación de aplicaciones de escritorio utilizando Java.
- Gestor de Bases de Datos: [MySQL](https://www.mysql.com/) - Permitir gestionar las tablas donde se almacena toda la información que genera el sistema.
- Mapeo Objeto-Relacional(ORM): [Hibernate](https://hibernate.org/orm/releases/4.3/) - Mejora la forma de mapear los atributos de una base de datos tradicional.
- Escalar imágenes: RSScaleLabel - Permite que las imágenes escalen para mejorar su presentación en cualquier tamaño.
- Manejar PDFs: [itext-pdf4](https://itextpdf.com/) - Facilitar la gestión y manipulación de archivos pdf.
- Generar Reportes: [JarperReport](https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports/6.0.0) - Colocar contenido dinámico en archivos pdf.
- Notificación: JCarrierPigeon - Mostrar notificaciones en el escritorio en forma de popup.
  
## Dominio

Gestionar usuarios, insumos, documentación, acciones, notificaciones, proveedores.
- Solo un usuario con privilegios de administrador puede crear nuevos usuarios, desde dentro del sistema.
- Los usuarios pueden iniciar sesión, restablecer su contraseña, editar su perfil.
- Un usuario puede crear, actualizar, listar, eliminar insumos.
- Un usuario puede crear, actualizar, listar, eliminar proveedores.
- Un proveedor puede tener documentos.
- Un usuario puede crear, actualizar, listar, eliminar documentos.
- Un usuario puede crear, actualizar, listar, eliminar, notificaciones.
- Un usuario puede recuperar insumos/proveedores eliminados.
- Todas las acciones relacionadas con los insumos, se registran.
- Un usuario puede ver todas las acciones realizadas.

### Usuario

| Campo    | Tipo    | Descripción            |
|----------|---------|------------------------|
| id       | UUID    | Identificador único      |
| nombre   | Varchar | Nombre del Usuario     |
| apellido | Varchar | Apellido del Usuario   |
| password | Varchar | Contraseña del Usuario |
| correo   | Varchar | Email del Usuario      |
| tipo     | Varchar | Tipo de Usuario        |
| estado   | Bit     | Estado del Usuario     |

### Insumo

| Campo           | Tipo     | Descripción                |
|-----------------|----------|----------------------------|
| id              | UUID     | Identificador único          |
| nombre          | Varchar  | Nombre del Insumo          |
| descripcion     | Varchar  | Descripción del Insumo     |
| cantidad        | Double   | Cantidad del Insumo        |
| imagen          | Longblob | Imagen del Insumo          |
| precio_unitario | Double   | Precio Unitario del Insumo |
| tipo            | Varchar  | Tipo del Insumo            |
| estado          | Bit      | Estado del Insumo          |

### Notificación

| Campo      | Tipo    | Descripción                          |
|------------|---------|--------------------------------------|
| id         | UUID    | Identificador único                    |
| cantidad   | Double  | Cantidad de la Notificación          |
| f_creacion | Varchar | Fecha de Creación de la Notificación |
| fecha      | Varchar | Fecha de la Notificación             |
| estado     | Bit     | Estado de la Notificación            |
| fk_insumo  | Insumo  | Insumo de la Notificación            |

### Proveedor

| Campo     | Tipo    | Descripción             |
|-----------|---------|-------------------------|
| id        | UUID    | Identificador único       |
| cedula    | Varchar | Cédula del Proveedor    |
| nombres   | Varchar | Nombres del Proveedor   |
| apellidos | Varchar | Apellidos del Proveedor |
| empresa   | Varchar | Empresa del Proveedor   |
| email     | Varchar | Email del Proveedor     |
| direccion | Varchar | Dirección del Proveedor |
| telefono  | Varchar | Teléfono del Proveedor  |
| estado    | Bit     | Estado del Proveedor    |

### Documentación

| Campo        | Tipo      | Descripción             |
|--------------|-----------|-------------------------|
| id           | UUID      | Identificador único       |
| nombre       | Varchar   | Nombre del Documento    |
| fecha        | Varchar   | Fecha del Documento     |
| estado       | Bit       | Estado del Documento    |
| fk_proveedor | Proveedor | Proveedor del Documento |

### Acciones

| Campo           | Tipo      | Descripción                  |
|-----------------|-----------|------------------------------|
| id              | UUID      | Identificador único            |
| cantidad        | Double    | Cantidad de la Acción        |
| producto        | Varchar   | Producto de la Acción        |
| descripcion     | Varchar   | Descripción de la Acción     |
| fecha           | Timestamp | Fecha de la Acción           |
| usuario         | Varchar   | Usuario de la Acción         |
| ultima_cantidad | Double    | Ultima Cantidad de la Acción |
| ultimo_precio   | Double    | Ultimo Precio de la Acción   |
| factura         | Varchar   | Factura de la Acción         |

## Funciones
<table>
  
  <tr>
      <td>
      <h3 align="center">Iniciar Sesión</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/login.PNG" width="80%" alt="Login Screen">
        <p>
          - Un Usuario que tenga una cuenta registrada en la base de datos puede iniciar sesión para acceder al sistema.
        </p>
      </div>
    </td>
  
  <tr>
    <td>
      <h3 align="center">Menú Principal</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/menu.PNG" width="80%" alt="Principal Menu">
        <p>
          - 1) Control de Insumos: Todas las funciones relacionadas con gestionar los insumos. <br/>
          - 2) Documentación de Proveedores: Gestionar proveedores y su documentación relacionada, ademas de documentación en general. <br/>
          - 3) Configuraciones: Gestionar usuarios (crear, listar, editar, eliminar o recuperar).
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td>
      <h3 align="center">Lista/Edición de Insumos</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/edit_insumo.PNG" width="80%" alt="List/Edit Insumo">
        <p>
          - Listar todos los insumos registrados en la base de datos y que esten activos. <br/>
          - Buscar por el nombre o filtrar por el tipo de candidad. <br/>
          - Editar o Eliminar la información del Insumo. <br/>
          - Actualizar los ingresos/retiros del Insumo.
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td>
      <h3 align="center">Crear Nuevo Insumo</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/create_insumo.PNG" width="80%" alt="Create Insumo">
        <p>
          - Registrar un nuevo insumo en la base de datos. Se registrar el nombre, la cantidad, el tipo de cantidad, el precio  unitario, el inventario mínimo(esto tiene relación con la notificación), la descripción e imagen del insumo. <br/>
          - Opción para calcular el precio unitario, porque la mayoría no comprar por unidades un producto. <br/>
          - Cuando se crea un insumo automáticamente se crea una notificación relacionada con el mismo.
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td>
      <h3 align="center">Gestionar Notificaciones</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/notifications.PNG" width="80%" alt="Notifications Screen">
        <p>
          - Ver la lista de todas las notificaciones, buscar por el nombre del insumo, editar la cantidad mínima permitida del insumo. <br/>
          - Código de Colores: 1) Verde, cantidad por igual o mayor que lo optimo; 2) Amarillo, la cantidad se esta acercando a la cantidad mínima; 3) Rojo, la cantidad es igual o menor que la cantidad mínima y es necesario ingresar existencias a ese insumo.
          </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td>
      <h3 align="center">Gestionar Reportes</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/report_1.PNG" width="80%" alt="Report Screen">
        <p>
          - Generar reportes relacionados con los insumos. Ordenar por distintos factores. Filtrar por fechas, productos. <br/>
          - Mostrar los datos de cada insumo en un tabla, además de los totales de acuerdo a los filtros establecidos.
        </p>
        <br/>
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/report_pdf.PNG" width="80%" alt="Report PDF">
        <p>
          - El sistema permite exportar el reporte generado en formato PDF.
       </p>
        <br/>
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/report_graphic.PNG" width="80%" alt="Report Graphic">
        <p>
          - El sistema permite mostrar el reporte de insumos de dos formas: 1) Una tabla, 2) Gráficos de Barras. <br/>
          - El gráfico de barras tiene cuatro opciones de interes: 1) Ver los ingresos, 2) Ver los retiros, 3) Ver por el precio unitario, 4) Ver por el costo total(el dinero total invertido en ese insumo).
       </p>
        <br/>
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/report_actions.PNG" width="80%" alt="Report Actions">
        <p>
          - Ver todas las acciones realizadas con los insumos(crear, editar, eliminar, ingresos, retiros. <br/>
          - Filtrar por tipo de acción y fecha.
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td>
      <h3 align="center">Gestionar Proveedores</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/proveedores.PNG" width="80%" alt="Suppliers">
        <p>
          - Listar todos los proveedores o buscar por el nombre. Crear nuevos proveedores. Editar o Eliminar proveedores haciendo click en la lista de proveedores y luego confirmar el cambio. <br/>
          - Boton de PDF para ver todos los documentos relacionados a ese proveedor.
        </p>
        <br/>
         <img src="https://raw.githubusercontent.com/bnphony/Inventario-Insumos/master/Instalador/img/proveedores_pdf.PNG" width="80%" alt="Suppliers PDF">
        <p>
          - Listar, Crear, Actualizar, Eliminar documentos. <br/>
          - Buscar por el título del documento, filtrar por el proveedor o listar los documentos que no esten relacionados a un proveedor en específico. <br/>
          - Abrir el documento PDF.
        </p>
      </div>
    </td>
  </tr>
  
</table>


## Autor
Codificado por [Bryan Jhoel Tarco Taipe](https://github.com/bnphony), basado en el tutorial [Curso de Django3](https://youtu.be/XclfcvFjN48?si=cKXKIUnwvoaoEQpc) por [William Jair Dávila Vargas](https://algorisoft.com/)

### Contacto
<a href="https://www.linkedin.com/in/bryan-tarco01" rel="noopener noreferrer" target="_blank">
  <img align="center" src="https://github.com/bnphony/Portafolio/blob/deployed/static/img/linkedin_icon.png" alt="LinkedIn" height="40" width="40" />
</a>
<a href="https://github.com/bnphony" rel="noopener noreferrer" target="blank">
  <img align="center" src="https://github.com/bnphony/Portafolio/blob/deployed/static/img/github_icon.png" alt="GitHub" height="40" width="40" />
</a>
<a href="mailto: bryan.tarco01@gmail.com" target="_blank">
  <img align="center" src="https://github.com/bnphony/Portafolio/blob/deployed/static/img/email_icon.png" alt="Email" height="40" width="40" />
</a>



## Licencia de Uso
Este repositorio y todo su contenido está licenciado bajo licencia **Creative Commons**. Por favor si compartes, usas o modificas este proyecto cita a sus
autores, y usa las mismas condiciones para su uso docente, formativo o educativo y no comercial.
