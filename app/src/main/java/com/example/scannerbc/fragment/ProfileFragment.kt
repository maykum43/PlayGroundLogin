package com.example.scannerbc.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scannerbc.R
import com.example.scannerbc.activity.ActivityLogin
import com.example.scannerbc.databinding.FragemntProfileBinding
import com.example.scannerbc.helper.SharePreference

class ProfileFragment: Fragment() {
    private var _binding: FragemntProfileBinding? = null
    private val binding get() = _binding!!

    lateinit var btn_logout : Button
    lateinit var sph : SharePreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragemntProfileBinding.inflate(inflater, container, false)
        init(binding.root)

        sph = SharePreference(requireActivity())

        button()

        return binding.root
    }

    private fun button() {
        btn_logout.setOnClickListener {
            sph.setStatusLogin(false)
            val inData = Intent(activity, ActivityLogin::class.java)
            inData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            inData.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Toast.makeText(requireActivity(),"Anda Logout dari akun anda",Toast.LENGTH_SHORT).show();
            startActivity(inData)
        }
    }

    private fun init(view: View) {
        btn_logout = view.findViewById(R.id.btn_logout)
    }
}