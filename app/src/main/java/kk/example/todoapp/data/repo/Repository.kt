package kk.example.todoapp.data.repo

import kk.example.todoapp.data.Task
import kk.example.todoapp.data.db.DaoApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val daoApp: DaoApp
) {
    suspend fun addTask(task: Task) = daoApp.addTask(task)

    fun getAllTasks(): Flow<List<Task>> = flow {
        daoApp.getAllTasksDistinctUntilChanged().collect { emit(it) }
    }

    suspend fun updateTask(task: Task) = daoApp.updateTask(task)
}