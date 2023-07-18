package com.time.timeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.time.timeto.databinding.FragmentFirstBinding
import java.time.LocalDateTime

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eventDate = LocalDateTime.of(2023,8,8,13,0)
        val createdDate = LocalDateTime.of(2023,7,18,22,19)
        val plnTrip = TimeToEvent("Poland Trip", "HH", eventDate, createdDate)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        val titleTextView = view.findViewById<TextView>(R.id.textview_title)
        titleTextView.text = "${plnTrip.name} : ${plnTrip.eventDate}"
        val hoursCount = view.findViewById<TextView>(R.id.textView_hours)
        hoursCount.text = "${plnTrip.timeUntil()} hours"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}