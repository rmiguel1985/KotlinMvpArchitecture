package com.adictosalainformatica.kotlinclean.base

import android.app.Application

/**
 * Base Application Abstract Class.
 *
 *
 * Define Logging, Dagger and security initializer
 */
abstract class BaseApplication : Application() {
    protected abstract fun initializeLogging()
    protected abstract fun initializeDiagnosticTools()
    protected abstract fun initializeHelpers()
    protected abstract fun initializeRoom()
}