package  kk.example.todoapp.data.db

import androidx.room.*
import kk.example.todoapp.data.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
interface DaoApp {

    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<Task>>

    fun getAllTasksDistinctUntilChanged() = getAllTasks().distinctUntilChanged()
}