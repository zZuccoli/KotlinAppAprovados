package aprovados.myapplication

import android.app.Application
import aprovados.myapplication.data.AppDatabase
import aprovados.myapplication.data.repository.FlashcardRepository
import aprovados.myapplication.data.repository.TarefaRepository

class App : Application() {
    // Lazy initialization of database and repositories
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
    val flashcardRepository: FlashcardRepository by lazy {
        FlashcardRepository(database.flashcardDao())
    }
    val tarefaRepository: TarefaRepository by lazy {
        TarefaRepository(database.tarefaDao())
    }
}
