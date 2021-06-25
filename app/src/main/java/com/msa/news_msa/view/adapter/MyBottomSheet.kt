package com.msa.news_msa.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.msa.news_msa.R
import com.msa.news_msa.databinding.BottomSheetBinding
import com.msa.news_msa.databinding.ProfileFragmentBinding

class MyBottomSheet : BottomSheetDialogFragment() {

    private var _vBinding:BottomSheetBinding?=null
    private val vBinding get() = _vBinding!!
    companion object {
        fun newInstance(): MyBottomSheet =
            MyBottomSheet().apply {
                /*
                arguments = Bundle().apply {
                    putInt("VALUE", someValue)
                }
                 */
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {
        _vBinding = BottomSheetBinding.inflate(inflater, container, false)
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}