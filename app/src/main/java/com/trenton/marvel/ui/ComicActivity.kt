package com.trenton.marvel.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.trenton.marvel.R
import com.trenton.marvel.ui.factory.ComicFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ComicActivity : AppCompatActivity(R.layout.activity_comic) {

    @Inject
    lateinit var comicFragmentFactory: ComicFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.actionbar_title)

        startComicFragment()
    }

    private fun startComicFragment() {
        supportFragmentManager.fragmentFactory = comicFragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.comic_fragment_container, ComicFragment::class.java, null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.comic_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.comic_refresh) {
            startComicFragment()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}