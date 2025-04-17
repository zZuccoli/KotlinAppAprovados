package aprovados.myapplication.flashcards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aprovados.myapplication.data.entities.Flashcard
import aprovados.myapplication.databinding.ItemFlashcardBinding

class FlashcardAdapter : RecyclerView.Adapter<FlashcardAdapter.ViewHolder>() {

    private val lista = mutableListOf<Flashcard>()

    fun submeterLista(nova: List<Flashcard>) {
        lista.clear()
        lista.addAll(nova)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemFlashcardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flashcard: Flashcard) {
            binding.txtPergunta.text = flashcard.pergunta
            binding.txtResposta.text = flashcard.resposta
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFlashcardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lista[position])
    }
}
