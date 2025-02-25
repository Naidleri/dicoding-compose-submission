##---------------Begin: Proguard Configuration for SQLCipher  ----------
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

## Keep AndroidX classes
-keep class androidx.** { *; }
-dontwarn androidx.**

## Keep Paging Library
-keep class androidx.paging.** { *; }
-dontwarn androidx.paging.**

## Keep Room Database and SQLite
-keep class androidx.room.** { *; }
-keep class androidx.sqlite.** { *; }
-dontwarn androidx.room.**
-dontwarn androidx.sqlite.**

##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}

##---------------Begin: proguard configuration for Retrofit ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
@retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

-dontwarn kotlinx.**


## Keep Koin (Dependency Injection)
-keep class org.koin.** { *; }
-dontwarn org.koin.**

## Keep Coroutines
-dontwarn kotlinx.coroutines.**
-keep class kotlinx.coroutines.** { *; }

## Keep LeakCanary (Debugging Tool)
-if class leakcanary.**
-keep class leakcanary.** { *; }
-dontwarn leakcanary.**

## Keep Material Components
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

## Keep Navigation Components
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

## Keep Annotations
-keepattributes *Annotation*
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.Metadata


# Jangan lakukan optimasi pada string concatenation
-dontoptimize
-keep class java.lang.invoke.** { *; }

# Core Library Desugaring untuk Java 9+ features
-dontwarn jdk.internal.**
-dontwarn sun.misc.Unsafe

# Jaga semua class di package domain dan UI
-keep class com.ned.core.domain.model.** { *; }
-keep class com.ned.core.domain.repository.** { *; }
-keep class com.ned.core.domain.usecase.** { *; }
-keep class com.ned.ui.common.** { *; }

# Jaga class untuk Koin DI
-keep class com.ned.core.injection.CoreModuleKt { *; }

# Keep all domain model classes
-keep class com.ned.core.domain.model.** { *; }

# Keep repository interfaces
-keep class com.ned.core.domain.repository.** { *; }

# Keep all usecase classes
-keep class com.ned.core.domain.usecase.** { *; }

# Keep Koin module definitions
-keep class com.ned.core.injection.** { *; }