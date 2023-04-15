package com.example.qmartapp.orderPage

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.example.qmartapp.databinding.FragmentCityBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CityBottomSheetFragment : BottomSheetDialogFragment() {

    private var selectedIndex = -1
    private var onCitySelectedListener: (Pair<Int, String>) -> Unit = {}
    private var countries: List<String> = emptyList()

    lateinit var binding: FragmentCityBottomSheetBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        binding = FragmentCityBottomSheetBinding.inflate(layoutInflater)
        val view = binding.root
        bottomSheet.setContentView(view)

        setBottomSheet()
        setUI()

        return bottomSheet
    }

    @SuppressLint("InternalInsetResource")
    private fun setBottomSheet() {
        val view = binding.root
        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }

        /*
        val params = view.layoutParams
        val tv = TypedValue()
        var actionBarHeight = 0
        if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }
        var statusBarHeight: Int = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        }
        params.height = Resources.getSystem().displayMetrics.heightPixels - actionBarHeight - statusBarHeight
        view.layoutParams = params
         */
    }

    private fun setUI() = with(binding) {
        val countriesAdapter = CityAdapter()

        recyclerView.apply {
            adapter = countriesAdapter
        }

        chooseButton.setOnClickListener {
            onCitySelectedListener.invoke(
                Pair(
                    countriesAdapter.getSelectedIndex(),
                    countriesAdapter.getSelectedName()
                )
            )
            dialog?.dismiss()
        }

        countriesAdapter.apply {
            setSelectedIndex(selectedIndex)
            submitList(countries)
        }
    }

    fun setCities(index: Int, list: List<String>) {
        selectedIndex = index
        countries = list
    }

    fun setCitySelectedListener(onCitySelectedListener: (Pair<Int, String>) -> Unit) {
        this.onCitySelectedListener = onCitySelectedListener
    }
}