package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainFragment : Fragment(), View.OnClickListener {
    //    private var _binding: FragmentMainBinding? = null
//    private val binding get() = _binding!!
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        _binding = FragmentMainBinding().inflate(inflater, container, false)
//        val view = binding.root
//        return view
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.buttonForEmptyFragment1).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonForEmptyFragment2).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonForEmptyFragment3).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonForEmptyFragment1 -> navController?.navigate(R.id.action_mainFragment_to_emptyFragmentFirst)
            R.id.buttonForEmptyFragment2 -> navController?.navigate(R.id.action_mainFragment_to_emptyFragmentSecond)
            R.id.buttonForEmptyFragment3 -> navController?.navigate(R.id.action_mainFragment_to_emptyFragmentThird)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }
}
