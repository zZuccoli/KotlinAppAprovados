package aprovados.myapplication.calendario

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aprovados.myapplication.databinding.ItemDiaBinding
import java.text.SimpleDateFormat
import java.util.*

class DiaAdapter(
    private val dias: List<Calendar>,
    private val onClick: (Calendar) -> Unit
) : RecyclerView.Adapter<DiaAdapter.DiaViewHolder>() {

    private val hoje = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    inner class DiaViewHolder(private val binding: ItemDiaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Calendar) {
            val dia = data.get(Calendar.DAY_OF_MONTH).toString()
            binding.txtDia.text = dia

            val dataFormatada = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(data.time)

            // Estilização simples para destacar o dia de hoje
            if (dataFormatada == hoje) {
                binding.card.setCardBackgroundColor(Color.parseColor("#00BCD4"))
                binding.txtDia.setTextColor(Color.BLACK)
            } else {
                binding.card.setCardBackgroundColor(Color.DKGRAY)
                binding.txtDia.setTextColor(Color.WHITE)
            }

            binding.root.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaViewHolder {
        val binding = ItemDiaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaViewHolder, position: Int) {
        holder.bind(dias[position])
    }

    override fun getItemCount(): Int = dias.size
}
