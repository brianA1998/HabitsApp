# Habit Tracker App
Este proyecto es una aplicación de seguimiento de hábitos escrita en Kotlin para Android. La aplicación permite a los usuarios realizar un seguimiento de sus hábitos diarios y recibir recordatorios para ayudarles a mantenerse en el buen camino. Está diseñada siguiendo los principios de Clean Architecture y SOLID, y utiliza varias tecnologías y bibliotecas importantes para proporcionar una experiencia robusta y escalable.


## Imagenes

### Login
[Login](https://github-production-user-asset-6210df.s3.amazonaws.com/16141845/240980065-11e3d710-d2f3-4209-941c-7c59b0c27deb.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240821%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240821T235219Z&X-Amz-Expires=300&X-Amz-Signature=2a131c28e1da4a105050108661b03d33affd869482b960fa4646fc19c79992c8&X-Amz-SignedHeaders=host&actor_id=43861894&key_id=0&repo_id=615835755)

### Registration
[Registration](https://github-production-user-asset-6210df.s3.amazonaws.com/16141845/240980107-fc72f8ff-badd-48f4-99c6-23ba076b626f.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240821%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240821T235303Z&X-Amz-Expires=300&X-Amz-Signature=243ddb55e4ae852be3aec0106a22decca4c1168d7dfb346b7c89abefe91e414f&X-Amz-SignedHeaders=host&actor_id=43861894&key_id=0&repo_id=615835755)

### Home
[Home](https://github-production-user-asset-6210df.s3.amazonaws.com/16141845/240980185-84c02624-3043-4fd1-a889-ff82c0f62455.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240821%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240821T235320Z&X-Amz-Expires=300&X-Amz-Signature=36749e2cebdcb92de1763241fa42de66b068a88d85649c76d6273d772bc5fe4e&X-Amz-SignedHeaders=host&actor_id=43861894&key_id=0&repo_id=615835755)

### Detail
[Detail](https://github-production-user-asset-6210df.s3.amazonaws.com/16141845/240980217-a712bb7e-df63-4947-86e8-20057dd0f5d6.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240821%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240821T235335Z&X-Amz-Expires=300&X-Amz-Signature=2fd3c3a910381b9f01e294fad3b83c85ca5cf08ea10d13264f514a1678d97dc8&X-Amz-SignedHeaders=host&actor_id=43861894&key_id=0&repo_id=615835755)

### Características principales

* Clean Architecture: El proyecto está estructurado siguiendo el patrón de Clean Architecture, lo que facilita la separación de responsabilidades y mejora la mantenibilidad del código.
* SOLID: Los principios SOLID (Single Responsibility, Open-Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) se aplican en el diseño de la aplicación para promover un código limpio, modular y extensible.
* Inyección de Dependencias - Dagger-Hilt: Se utiliza Dagger-Hilt para realizar la inyección de dependencias, lo que simplifica la gestión de las dependencias y permite una mejor escalabilidad y prueba unitaria del código.
* Jetpack Compose: La interfaz de usuario se desarrolla utilizando Jetpack Compose, el moderno toolkit de UI de Android que facilita la creación de interfaces de usuario flexibles y dinámicas.
* Firebase Authentication: Se integra Firebase Authentication para proporcionar un sistema de autenticación seguro y confiable para los usuarios de la aplicación.
* Room: Se utiliza Room, la biblioteca de persistencia de Android, para almacenar los datos de los hábitos en una base de datos local y permitir un acceso rápido y eficiente a ellos.
* Retrofit: Se utiliza Retrofit para realizar las llamadas a una API remota y obtener datos relacionados con los hábitos, lo que permite una sincronización eficiente y actualizada de la información.
* Notificaciones: La aplicación permite configurar recordatorios personalizados para cada hábito y muestra notificaciones en los días y horas especificados para ayudar a los usuarios a seguir sus rutinas diarias.
* AlarmManager: El componente AlarmManager de Android se utiliza para programar las notificaciones y garantizar que se muestren en los momentos adecuados.
* WorkManager: Se utiliza WorkManager para gestionar las tareas en segundo plano, lo que permite que los hábitos creados en modo avión se guarden automáticamente en la nube una vez que el dispositivo tenga conexión a Internet.
* Unit Test: Se incluyen pruebas unitarias para verificar el correcto funcionamiento de los componentes clave de la aplicación y garantizar la calidad del código.
* UI Test: Se proporcionan pruebas de interfaz de usuario para verificar que la aplicación se comporte correctamente y proporcione una experiencia de usuario fluida.
* Offline-First: La aplicación está diseñada siguiendo el enfoque "Offline-First", lo que significa que la funcionalidad principal está disponible incluso cuando el dispositivo está sin conexión a Internet. Los datos se sincronizan automáticamente una vez que la conexión está disponible utilizando WorkManager y la API remota.
* Broadcast Receivers: Se utilizan Broadcast Receivers para gestionar el agregado de alarmas cuando el usuario reinicia el dispositivo, y cuando suena una alarma, para configurar la siguiente.