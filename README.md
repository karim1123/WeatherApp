<h1 align="center">Weather</h1>

<p align="center">  
Weather is weather app using open-meteo API.
</p>
</br>

<table style="width: 100%;">
  <tr>
    <td><img src="https://user-images.githubusercontent.com/40930427/230304880-826b2ec7-8b05-4b34-88e4-aac5c43e5b64.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230304936-8d23e44a-727f-40cf-8798-64a0957f78f6.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230311034-65937349-8101-42da-9631-9e041efedb49.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230311076-9b7cbb91-e9ef-4fa7-ac31-13bf0ed0da9e.png"/></td>
  </tr>
</table>

<p align="center">
<video src="https://user-images.githubusercontent.com/40930427/230323393-c728624d-76c9-47d5-b4d4-f492a836f6e0.mp4" controls></video>
</p>

## Download
Go to the https://drive.google.com/file/d/1nYcJwjgvswy7eWBaVPw-LmSWFCnsQ1s_/view?usp=sharing to download the APK.

<img src="/previews/preview.gif" align="right" width="320"/>

## Tech stack & Open-source libraries
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Compose: Android’s modern toolkit for building native UI.
  - ViewModel: UI related data holder and lifecycle aware.
  - Navigation: For navigating screens and [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for injecting dependencies.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): Dependency Injection.
- Architecture
  - MVI Architecture
  - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich): Construct a lightweight and modern response interface to handle network payload for Android.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.
- [Material-Components](https://github.com/material-components/material-components-android): Material design components for building ripple animation, and CardView.
- [Glide](https://github.com/bumptech/glide)
- Build
  - TOML
  - Gradle Convention Plugins
- Lint
  - Ktlint
  - Detekt
- Test
  - Junit
  - Mockk
  - LeakCanary

## Open API

<img src="https://user-images.githubusercontent.com/40930427/230330965-bb7cc021-5582-4940-a5fc-2dfcb5f95b4f.png" align="right" width="21%"/>

Weather using the [OpenMeteo](https://open-meteo.com/) for API.<br>
Open-Meteo is an open-source weather API with free access for non-commercial use. No API key is required. You can use it immediately!

 
