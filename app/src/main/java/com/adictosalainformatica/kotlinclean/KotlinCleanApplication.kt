package com.adictosalainformatica.kotlinclean

import android.os.StrictMode
import androidx.room.Room
import com.adictosalainformatica.kotlinclean.base.BaseApplication
import com.adictosalainformatica.kotlinclean.base.koin.MainModule
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema.AppDatabase
import com.adictosalainformatica.kotlinclean.features.avengerslist.koin.avengersListMainModule
import com.adictosalainformatica.kotlinclean.utils.ConnectivityHelper
import com.adictosalainformatica.kotlinclean.utils.Constants
import com.adictosalainformatica.kotlinclean.utils.PreferenceHelper
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import timber.log.Timber

class KotlinCleanApplication: BaseApplication() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        initializeLogging()
        initializeHelpers()
        initializeRoom()

        var koinModules: MutableList<Module> = mutableListOf(MainModule)
        koinModules.addAll(avengersListMainModule)


        startKoin(this,
                koinModules)
    }

    override fun initializeRoom() {
        KotlinCleanApplication.database = Room
                .databaseBuilder(applicationContext,
                        AppDatabase::class.java, Constants.DATABASE_NAME)
                .build()
    }

    override fun initializeLogging() {
        if (isDebugBuild()) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun initializeHelpers() {
        ConnectivityHelper.setContext(applicationContext)
        PreferenceHelper.setContext(applicationContext)
    }

    /**
     * Check build type
     *
     * @return true if is a debug build, otherwise false
     */
    private fun isDebugBuild(): Boolean {
        return BuildConfig.DEBUG
    }

    /**
     * Initialize diagnostic tools
     *
     */
    override fun initializeDiagnosticTools() {
        if (isDebugBuild()) {
            val strictModeThreadPolicy = StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyDeath()
                    .build()
            StrictMode.setThreadPolicy(strictModeThreadPolicy)

            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return
            }
            LeakCanary.install(this);
        }
    }
}