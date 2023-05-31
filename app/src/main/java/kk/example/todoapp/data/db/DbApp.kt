package kk.example.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kk.example.todoapp.data.Task


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class DbApp : RoomDatabase() {
    abstract fun getDaoApp(): DaoApp
}