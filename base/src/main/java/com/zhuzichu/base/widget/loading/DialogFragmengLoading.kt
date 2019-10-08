package com.zhuzichu.base.widget.loading

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmengLoading : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DialogLoading(requireContext())
    }
}