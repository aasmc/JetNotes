package ru.aasmc.jetnotes

import android.app.Application
import ru.aasmc.jetnotes.di.DependencyInjector

class JetNotesApplication : Application() {
    lateinit var dependencyInjector: DependencyInjector

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}