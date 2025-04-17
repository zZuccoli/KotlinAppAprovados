package aprovados.myapplication.data

import android.app.Application
import aprovados.myapplication.data.repository.FlashcardRepository
import aprovados.myapplication.data.repository.TarefaRepository

class App : Application() {

    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }

    val tarefaRepository: TarefaRepository by lazy {
        TarefaRepository(database.tarefaDao())
    }

    val flashcardRepository: FlashcardRepository by lazy {
        FlashcardRepository(database.flashcardDao())
    }
}
