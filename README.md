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
- Generar Reportes.
- Visualizar los reportes utilizando gráficos de barras.
- Exportar los reportes en formato PDF.
   
### Tecnologías

- Lenguaje de Programación: [Java](https://www.java.com/es/) - Utilizado para desarrollar una aplicación de escritorio para sistema operativo Windows.
- Entorno de Desarrollo: [NetBeans](https://netbeans.apache.org/front/main/index.html) - Facilita la creación de aplicaciones de escritorio utilizando Java.
- Gestor de Bases de Datos: [MySQL](https://www.mysql.com/) - Permitir gestionar las tablas donde se almacena toda la información sobre el sistema.
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
- Un usuario puede recuperar insumos eliminados.
- Todas las acciones relacionadas con los insumos, se registran.
- Un usuario puede ver todas las acciones realizadas.

### Usuario

| Campo    | Tipo    | Descripción            |
|----------|---------|------------------------|
| id       | UUID    | Identificar único      |
| nombre   | Varchar | Nombre del Usuario     |
| apellido | Varchar | Apellido del Usuario   |
| password | Varchar | Contraseña del Usuario |
| correo   | Varchar | Email del Usuario      |
| tipo     | Varchar | Tipo de Usuario        |
| estado   | Bit     | Estado del Usuario     |


### Insumo

| Campo           | Tipo     | Descripción                |
|-----------------|----------|----------------------------|
| id              | UUID     | Identificar único          |
| nombre          | Varchar  | Nombre del Insumo          |
| descripcion     | Varchar  | Descripción del Insumo     |
| cantidad        | Double   | Cantidad del Insumo        |
| imagen          | Longblob | Imagen del Insumo          |
| precio_unitorio | Double   | Precio Unitario del Insumo |
| tipo            | Varchar  | Tipo del Insumo            |
| estado          | Bit      | Estado del Insumo          |

### Notificación

| Campo      | Tipo    | Descripción                          |
|------------|---------|--------------------------------------|
| id         | UUID    | Identificar único                    |
| cantidad   | Double  | Cantidad de la Notificación          |
| f_creacion | Varchar | Fecha de Creación de la Notificación |
| fecha      | Varchar | Fecha de la Notificación             |
| estado     | Bit     | Estado de la Notificación            |
| fk_insumo  | Insumo  | Insumo de la Notificación            |

### Proveedor

| Campo     | Tipo    | Descripción             |
|-----------|---------|-------------------------|
| id        | UUID    | Identificar único       |
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
| id           | UUID      | Identificar único       |
| nombre       | Varchar   | Nombre del Documento    |
| fecha        | Varchar   | Fecha del Documento     |
| estado       | Bit       | Estado del Documento    |
| fk_proveedor | Proveedor | Proveedor del Documento |

### Acciones

| Campo           | Tipo      | Descripción                  |
|-----------------|-----------|------------------------------|
| id              | UUID      | Identificar único            |
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
    <td width="50%">
      <h3 align="center">Iniciar Sesión</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/login.PNG" width="80%" alt="Login">
        <p>
          - Un Usuario puede iniciar sesión con su nombre de usuario y contraseña.
        </p>
      </div>
    </td>
    <td width="50%">
      <h3 align="center">Restablecer Contraseña</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/reset_password.PNG" width="80%" alt="Reset Password">
        <p>
          - Un Usuario puede cambiar su contraseña utilizando su nombre nombre de usuario, con esto se le envía un link a su email con el procedimiento correspondiente.
        </p>
      </div>
    </td>
  
  <tr>
    <td witdh="100%" colspan="2">
      <h3 align="center">Gestionar Categorías</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/categorias.PNG" width="80%" alt="Categories">
        <p>
          - Crear, actualizar, listar, eliminar categorías.
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td width="100%" colspan="2">
      <h3 align="center">Gestionar Productos</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/productos.PNG" width="80%" alt="Products">
        <p>
          - Crear, actualizar, listar, eliminar productos.
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td width="100%" colspan="2">
      <h3 align="center">Gestionar Clientes</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/clientes.PNG" width="80%" alt="Clients">
        <p>
          - Crear, actualizar, listar, eliminar clientes.
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td width="100%" colspan="2">
      <h3 align="center">Gestionar Ventas</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/ventas.PNG" width="80%" alt="Sales">
        <p>
          - Crear, actualizar, listar, eliminar ventas.          
        </p>
        <br/>
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/detalle_venta.PNG" width="80%" alt="Sales">
        <p>
          - Descripción de la venta: productos vendidos, cliente, fecha de la venta, subtotal, IVA y precio total.   
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td witdh="100%" colspan="2">
      <h3 align="center">Generación de Reportes de Ventas</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/date_picker.PNG" width="40%" alt="Date Range">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/reporte.PNG" width="40%" alt="Report">
        <p>
          - El usuario puede escoger el rango de fecha de los reportes.
          - Opciones para descargar el reporte en formato excel o pdf.
        </p>
      </div>
    </td>
  </tr>
  
  <tr>
    <td width="100%" colspan="2">
      <h3 align="center">Gestionar Usuarios</h3>
      <div align="center">
        <img src="https://raw.githubusercontent.com/bnphony/Sistema-Inventario/master/proyecto1/static/img/usuarios.PNG" width="80%" alt="Users">
        <p>
          - Crear, actualizar, listar, eliminar usuarios. <br/>
          - Solo los usuarios de tipo administrador pueden acceder a esta opción.
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
