package kk.example.todoapp.presentation.addTaskimport androidx.lifecycle.ViewModelimport androidx.lifecycle.viewModelScopeimport dagger.hilt.android.lifecycle.HiltViewModelimport kk.example.todoapp.data.Taskimport kk.example.todoapp.data.repo.Repositoryimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.flow.Flowimport kotlinx.coroutines.flow.flowimport kotlinx.coroutines.launchimport javax.inject.Inject@HiltViewModelclass MainViewModel @Inject constructor(    private val repository: Repository) : ViewModel(){    val listTasks : Flow<List<Task>> = flow {        repository.getAllTasks().collect {            emit(it)        }    }    fun updateTask(task: Task){        viewModelScope.launch(Dispatchers.IO){            repository.updateTask(task)        }    }}