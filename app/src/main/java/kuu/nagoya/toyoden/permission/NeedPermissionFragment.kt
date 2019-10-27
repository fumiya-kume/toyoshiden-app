package kuu.nagoya.toyoden.permission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kuu.nagoya.toyoden.databinding.FragmentNeedPermissionBinding

internal class NeedPermissionFragment : Fragment() {

    companion object {
        fun create(): NeedPermissionFragment = NeedPermissionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentNeedPermissionBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        return binding.root
    }
}