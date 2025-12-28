# 🌦️ WeatherApiNando - Android Weather App

Una aplicación de clima moderna y robusta construida para demostrar mejores prácticas de desarrollo en Android. El proyecto implementa un flujo completo desde la búsqueda de ciudades en tiempo real hasta la visualización detallada del pronóstico, utilizando la API de [WeatherAPI](https://www.weatherapi.com/).

| Home Screen | Search & Weather |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/dc581e97-ffe9-4dcc-94f4-cbcb8f8f47b9" width="300" /> | <img src="https://github.com/user-attachments/assets/8fcb76fe-30e6-425c-9971-d5a914fdb389" width="300" /> |



## 🚀 Características Principales

- **Búsqueda Inteligente:** Sistema de sugerencias dinámico integrado en la Home que permite buscar ciudades mientras escribes sin cambiar de pantalla a través de una ventana flotante (dropdown).
- **Visualización Detallada:** Información en tiempo real sobre temperatura, viento, humedad y condiciones climáticas actuales para hoy, mañana y los próximos días.
- **Arquitectura de Capas:** Separación clara de responsabilidades siguiendo los principios de **Clean Architecture** (Domain, Data, UI).
- **Gestión de Estados Pro:** Manejo exhaustivo de estados (Loading, Success, Error) mediante `Sealed Classes` y `Flows`.
- **Mapeo de Errores Profesional:** Traducción de excepciones técnicas (`HttpException`, `IOException`) a mensajes amigables para el usuario final.
- **Internacionalización:** Soporte completo para **Español, Inglés y Portugués** mediante un sistema de `UiText` para evitar dependencias de Android en la capa de Dominio.
- **Animaciones Lottie:** Experiencia inmersiva desde el inicio con un Splash Screen animado y transiciones fluidas.

## 🛠️ Stack Tecnológico

- **UI:** Jetpack Compose con Material 3 (Uso de Stateless components para facilitar Previews).
- **Inyección de Dependencias:** Hilt (Dagger) con módulos organizados para Network y Repositorios.
- **Networking:** Retrofit 2 + OkHttp + Interceptor personalizado para el manejo de Headers y Timeouts.
- **Asincronía:** Kotlin Coroutines para procesos fuera del hilo principal.
- **Carga de Imágenes:** Coil (AsyncImage) para iconos climáticos dinámicos desde URL.
- **Navegación:** Compose Navigation con **Type Safety** mediante Kotlin Serialization.
- **Manejo de Errores:** Custom Wrapper `WeatherResultRepository<T>` para transporte de datos seguro entre capas.

## 🏗️ Estructura del Proyecto

El proyecto sigue una organización basada en Clean Architecture para maximizar la escalabilidad y testabilidad:



```text
com.example.weatherapinando
├── data/               # Implementaciones de Repositorios, DTOs (Data Transfer Objects) y API Service
├── domain/             # Modelos de negocio (UI-ready), Interfaces de Repositorios y Casos de Uso
└── ui/                 # Capa de presentación
    ├── screens/        # UI (Composables) y ViewModels por pantalla
    ├── navigation/     # Grafo de navegación y definiciones de rutas Type-Safe
    ├── theme/          # Configuración de Material 3 (Colores, Tipografía)
    └── common/         # Componentes reutilizables y utilidades de texto (UiText)

```



## ⚙️ Configuración y Ejecución

Sigue estos pasos para poner en marcha el proyecto en tu entorno local:

1. **Obtener API Key**: Regístrate en [WeatherAPI](https://www.weatherapi.com/signup.aspx) para obtener tu clave gratuita.
2. **Clonar el repositorio**:
   ```bash
   git clone [https://github.com/armandojosepinedaparedes/WeatherApiNando.git](https://github.com/armandojosepinedaparedes/WeatherApiNando.git)
   cd WeatherApiNando
3. Configurar Credenciales: Por seguridad y buenas prácticas, las llaves se manejan a través de BuildConfig. Abre tu archivo app/build.gradle.kts y localiza el bloque defaultConfig:
  
  ```kotlin
    android {
        defaultConfig {
            // Inserta tu clave aquí
            buildConfigField("String", "API_KEY", "\"TU_API_KEY_AQUI\"")
            buildConfigField("String", "BASE_URL", "\"[https://api.weatherapi.com/v1/](https://api.weatherapi.com/v1/)\"")
    
            // Nota: Se requiere API 21+ por compatibilidad con Type-Safe Navigation
            minSdkVersion(21)
        }
    }
  ```
4. Sincronizar: Haz clic en "Sync Project with Gradle Files" y ejecuta la aplicación.
   
Desarrollado por Armando José Pineda Paredes - Android Developer
