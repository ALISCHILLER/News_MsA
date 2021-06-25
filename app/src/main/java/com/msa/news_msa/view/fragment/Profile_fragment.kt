package com.msa.news_msa.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.msa.news_msa.R
import com.msa.news_msa.databinding.ProfileFragmentBinding
import com.msa.news_msa.view.adapter.MyBottomSheet
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [Profile_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Profile_fragment : Fragment() {
    private var _vBinding: ProfileFragmentBinding? = null
    private val vBinding get() = _vBinding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vBinding = ProfileFragmentBinding.inflate(inflater, container, false)
        return vBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.btnLink.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://gitlab.com/solimaniali90"))
            startActivity(i)
        }


        vBinding.btnAbout.setOnClickListener {
            val modalSheetView = layoutInflater.inflate(R.layout.bottom_sheet,null)
            val dialog = BottomSheetDialog(requireActivity())
            dialog.setContentView(modalSheetView)
            dialog.show()

        }



    }
}