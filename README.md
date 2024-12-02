# **Aplicación de Configuración de Emergencia**

## 📖 Descripción del Proyecto

**U-Safe** es una aplicación móvil desarrollada en Android que permite a los usuarios gestionar configuraciones de emergencia en sus dispositivos. Proporciona herramientas clave para proteger la privacidad y responder rápidamente en situaciones críticas, como:

- **Borrar datos sensibles**: Elimina archivos, fotos, contactos y mensajes seleccionados por el usuario.
- **Desinstalar aplicaciones específicas**: Selecciona y elimina aplicaciones instaladas rápidamente en caso de emergencia.
- **Activar modo oculto**: Un modo especial que protege la privacidad del usuario.
- **Interfaz intuitiva**: Diseño amigable y simple para facilitar la configuración y el uso.

## ✨ Características Principales

1. **Gestión de Datos Sensibles**:
   - Selección de tipos de datos a eliminar: archivos, fotos, contactos y mensajes.
   - Borrado inmediato de los datos seleccionados al presionar un botón de pánico.

2. **Gestión de Aplicaciones**:
   - Lista de aplicaciones instaladas en el dispositivo.
   - Posibilidad de seleccionar aplicaciones para desinstalarlas rápidamente.

3. **Modo Oculto**:
   - Interruptor para activar o desactivar un modo de privacidad.

4. **Navegación Intuitiva**:
   - Estructura clara con múltiples vistas para la configuración y selección de opciones.

## 📂 Estructura del Proyecto

### **Carpetas principales**:

- **`View`**: Contiene las interfaces de usuario.
  - **ConfigCodeView**: Configuración principal y manejo del botón de pánico.
  - **BorraDataView**: Interfaz para seleccionar los datos sensibles que se eliminarán.
  - **AppsConfiguracionView**: Interfaz para seleccionar las aplicaciones a desinstalar.

- **`Controlador`**: Controla la lógica entre vistas y modelos.
  - **ConfigCodeControlador**: Manejo de las acciones del botón de pánico y configuraciones.
  - **BorraDataControlador**: Lógica para la eliminación de los datos seleccionados.
  - **AppsConfiguracionControlador**: Gestión de las aplicaciones seleccionadas.

- **`Modelo`**: Contiene la lógica de negocio y los datos del proyecto.
  - **BorraDataModelo**: Operaciones para eliminar archivos, fotos, contactos y mensajes.
  - **AppsConfiguracionModelo**: Obtiene y almacena la lista de aplicaciones instaladas.

- Otros:
  - **AppItem**: Modelo de datos para representar las aplicaciones instaladas.

### **Flujo principal**:
1. **ConfigCodeView**:
   - Botón de pánico que borra los datos seleccionados y desinstala aplicaciones.
   - Activación o desactivación del modo oculto mediante un interruptor.
2. **BorraDataView**:
   - Permite seleccionar qué tipos de datos eliminar: archivos, fotos, contactos o mensajes.
3. **AppsConfiguracionView**:
   - Muestra una lista de aplicaciones instaladas y permite seleccionar cuáles desinstalar.

## 🚀 Instalación y Ejecución

### **Requisitos previos**:
- **Android Studio** instalado.
- Un dispositivo Android (físico o emulador) con versión mínima de Android 5.0 (Lollipop).

### **Pasos**:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/poofinal.git
2. Abre el proyecto en Android Studio
3. Sincroniza las dependencias del Gradle
4. Conecta un dispositivo Android o configura un emulador
5. Ejecuta la aplicacion: `Run > Run 'app'`
6. Listo! Explora la funcionaliad de la aplicacion.

## 🛠️ Dependencias y Librerías Utilizadas
- **Gradle:** Manejo de dependencias.
- **AndroidX:** Compatibilidad con nuevas versiones de Android.
- **RecyclerView:** Componente para mostrar listas de aplicaciones.
- **Toast:** Mostrar mensajes al usuario.


Este README puede servir tanto para documentación como para publicar el proyecto en plataformas como GitHub. Si necesitas personalizar algo más, ¡házmelo saber! 🚀
