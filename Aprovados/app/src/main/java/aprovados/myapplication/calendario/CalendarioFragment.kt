package aprovados.myapplication.calendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import aprovados.myapplication.App
import aprovados.myapplication.databinding.FragmentCalendarioBinding
import aprovados.myapplication.viewmodel.TarefaViewModel
import aprovados.myapplication.viewmodel.TarefaViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CalendarioFragment : Fragment() {

    private var _binding: FragmentCalendarioBinding? = null
    private val binding get() = _binding!!

    private val tarefaViewModel: TarefaViewModel by viewModels {
        TarefaViewModelFactory((requireActivity().application as App).tarefaRepository)
    }

    private lateinit var diaAdapter: DiaAdapter
    private lateinit var tarefaAdapter: TarefaAdapter
    private var mesAtual = Calendar.getInstance()
    private var dataSelecionada: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)

        tarefaAdapter = TarefaAdapter()
        binding.listaTarefas.layoutManager = LinearLayoutManager(requireContext())
        binding.listaTarefas.adapter = tarefaAdapter

        lifecycleScope.launch {
            tarefaViewModel.tarefas.collectLatest { lista ->
                tarefaAdapter.submitList(lista)
            }
        }

        atualizarTituloMes()
        configurarGridDias()

        binding.btnNovaTarefa.setOnClickListener {
            val data = dataSelecionada ?: SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(mesAtual.time)
            TarefaDialog(requireContext(), data = data) {
                tarefaViewModel.inserir(it)
                tarefaViewModel.listarPorData(data)
            }.show()
        }

        return binding.root
    }

    private fun atualizarTituloMes() {
        val formato = SimpleDateFormat("MMMM yyyy", Locale("pt", "BR"))
        binding.txtTituloMes.text = formato.format(mesAtual.time).replaceFirstChar { it.uppercase() }
    }

    private fun configurarGridDias() {
        val calendar = mesAtual.clone() as Calendar
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val dias = mutableListOf<Calendar>()
        val totalDiasMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        repeat(totalDiasMes) {
            val clone = calendar.clone() as Calendar
            dias.add(clone)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        diaAdapter = DiaAdapter(dias) { diaSelecionado ->
            val data = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(diaSelecionado.time)
            dataSelecionada = data
            tarefaViewModel.listarPorData(data)
        }

        binding.gridCalendario.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.gridCalendario.adapter = diaAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
