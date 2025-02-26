
# Jaga class UIState dan turunannya
-keep class com.ned.ui.common.UiState { *; }
-keep class com.ned.ui.common.UiState$** { *; }

# Jaga class untuk Koin
-keepclassmembers class * implements org.koin.core.module.Module { *; }
-keep class org.koin.core.parameter.ParametersHolder { *; }

# Jaga Composable functions (jika pakai Jetpack Compose)
-keep @androidx.compose.runtime.Composable class * { *; }

# Keep all domain model classes
-keep class com.ned.core.domain.model.** { *; }

# Keep repository interfaces
-keep class com.ned.core.domain.repository.** { *; }

# Keep all usecase classes
-keep class com.ned.core.domain.usecase.** { *; }

# Keep Koin module definitions
-keep class com.ned.core.injection.** { *; }