package com.example.hackio

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hackio.databinding.FragmentOngoingBinding
import com.example.hackio.databinding.FragmentUpcomingBinding
import kotlinx.android.synthetic.main.fragment_ongoing.*


class OngoingFragment : Fragment(),Onlisten {

    private val sharedViewModel: ContestViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentOngoingBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.dataon =sharedViewModel
        binding.recyclerViewon.adapter = Onadapter(this)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OngoingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onclicked(hit: ContestsItem) {
        val bundle = Bundle().apply {
            putSerializable("contest", hit)
        }
        val intent = Intent(activity, Wbview::class.java)
        intent.putExtra("website", hit.url)
        startActivity(intent)
    }

    override fun timerStart(foodPhoto: ContestsItem) {
        Toast.makeText(activity as AppCompatActivity, "Please set valid time", Toast.LENGTH_SHORT).show()
    }
}