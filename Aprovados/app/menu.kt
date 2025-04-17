import android.widget.Button

// MenuFragment.kt
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_flashcards).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_flashcardsFragment)
        }

        view.findViewById<Button>(R.id.btn_calendario).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_calendarioFragment)
        }
    }
}
