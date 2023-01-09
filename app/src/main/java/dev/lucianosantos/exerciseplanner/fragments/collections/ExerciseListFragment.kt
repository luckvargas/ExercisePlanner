package dev.lucianosantos.exerciseplanner.fragments.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import dev.lucianosantos.exerciseplanner.R
import dev.lucianosantos.exerciseplanner.adapters.ExerciseListAdapter
import dev.lucianosantos.exerciseplanner.databinding.FragmentExerciseListBinding
import dev.lucianosantos.exerciseplanner.viewmodels.RoutinesViewModel

/**
 * A fragment representing a list of Items.
 */
class ExerciseListFragment : Fragment() {

    private var _binding: FragmentExerciseListBinding? = null
    private val binding get() = _binding!!

    private val arguments by navArgs<ExerciseListFragmentArgs>()
    private lateinit var adapter: ExerciseListAdapter

    private val routinesViewModel: RoutinesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ExerciseListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        addDividerDecoration()

        binding.saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_pop_to_routineListFragment)
        }

        binding.addExerciseFloatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_exerciseListFragment_to_exerciseFormFragment)
        }

        // Fetch exercises
        routinesViewModel.getExercises().observe(viewLifecycleOwner) {
            adapter.updateExercises(it)
        }
    }

    private fun addDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.exerciseRecyclerView.addItemDecoration(divider)
    }
}