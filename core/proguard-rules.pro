
# Jaga class UIState dan turunannya
-keep class com.ned.ui.common.UiState { *; }
-keep class com.ned.ui.common.UiState$** { *; }

# Jaga class untuk Koin
-keepclassmembers class * implements org.koin.core.module.Module { *; }
-keep class org.koin.core.parameter.ParametersHolder { *; }

# Jaga Composable functions (jika pakai Jetpack Compose)
-keep @androidx.compose.runtime.Composable class * { *; }