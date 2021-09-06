package com.trenton.marvel.ui.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.trenton.marvel.ui.ComicFragment
import javax.inject.Inject

class ComicFragmentFactory
@Inject
constructor(
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ComicFragment::class.java.name -> {
                ComicFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }

}