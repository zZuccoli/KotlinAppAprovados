package aprovados.myapplication.calendario

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import aprovados.myapplication.data.entities.Tarefa
import aprovados.myapplication.databinding.ItemTarefaBinding

class TarefaAdapter : ListAdapter<Tarefa, TarefaAdapter.TarefaViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tarefa>() {
            override fun areItemsTheSame(oldItem: Tarefa, newItem: Tarefa): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tarefa, newItem: Tarefa): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TarefaViewHolder(private val binding: ItemTarefaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tarefa: Tarefa) {
            binding.txtTitulo.text = tarefa.titulo
            binding.txtMateria.text = tarefa.materia
            binding.txtHorario.text = tarefa.horario
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTarefaBinding.inflate(inflater, parent, false)
        return TarefaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}