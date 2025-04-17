package aprovados.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import aprovados.myapplication.data.dao.FlashcardDao
import aprovados.myapplication.data.dao.TarefaDao
import aprovados.myapplication.data.entities.Flashcard
import aprovados.myapplication.data.entities.Tarefa

@Database(entities = [Tarefa::class, Flashcard::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tarefaDao(): TarefaDao
    abstract fun flashcardDao(): FlashcardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "aprovados_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
