# Keep domain models, repositories, and use cases
-keep class com.ned.core.domain.model.** { *; }
-keep class com.ned.core.domain.repository.** { *; }
-keep class com.ned.core.domain.usecase.** { *; }

# Keep Koin module
-keep class com.ned.core.injection.CoreModuleKt { *; }
-keep class com.ned.disneycharacter.injection.AppModuleKt { *; }