package kk.example.todoapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kk.example.todoapp.data.db.DaoApp
import kk.example.todoapp.data.db.DbApp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun getDbApp(application: Application): DbApp = Room.databaseBuilder(
        application, DbApp::class.java, "My_Db"
    ).build()

    @Singleton
    @Provides
    fun getDao(dbApp: DbApp): DaoApp = dbApp.getDaoApp()
}