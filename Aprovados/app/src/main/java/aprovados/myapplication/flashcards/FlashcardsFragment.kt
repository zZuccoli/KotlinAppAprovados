package aprovados.myapplication.flashcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import aprovados.myapplication.App
import aprovados.myapplication.databinding.FragmentFlashcardsBinding
import aprovados.myapplication.viewmodel.FlashcardViewModel
import aprovados.myapplication.viewmodel.FlashcardViewModelFactory
import kotlinx.coroutines.launch

class FlashcardsFragment : Fragment() {

    private var _binding: FragmentFlashcardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FlashcardViewModel by viewModels {
        FlashcardViewModelFactory((requireActivity().application as App).flashcardRepository)
    }

    private lateinit var adapter: FlashcardAdapter
    private var tarefaId: Int = 1 // Substituir conforme necessário

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlashcardsBinding.inflate(inflater, container, false)

        adapter = FlashcardAdapter()
        binding.listaFlashcards.layoutManager = LinearLayoutManager(requireContext())
        binding.listaFlashcards.adapter = adapter

        viewModel.listarPorTarefa(tarefaId)

        lifecycleScope.launch {
            viewModel.flashcards.collect {
                adapter.submeterLista(it)
            }
        }

        binding.fabAddFlashcard.setOnClickListener {
            FlashcardDialog(requireContext(), tarefaId = tarefaId) {
                viewModel.adicionar(it)
            }.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
