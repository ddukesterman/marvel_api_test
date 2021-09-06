package com.trenton.marvel.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import com.trenton.marvel.R
import com.trenton.marvel.models.CreatorList
import com.trenton.marvel.models.Image
import com.trenton.marvel.viewmodel.ComicViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import kotlin.random.Random

@AndroidEntryPoint
class ComicFragment constructor(
) : Fragment(R.layout.fragment_comic) {

    private val viewModel: ComicViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.observableComic.observe(
            viewLifecycleOwner, { comic ->
                loadImage(context, view, comic.images)

                view.findViewById<TextView>(R.id.comic_title).text = comic.title

                val descriptionText = view.findViewById<TextView>(R.id.comic_description)

                comic.description?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        descriptionText.text =
                            Html.fromHtml(comic.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    } else {
                        descriptionText.text =
                            Html.fromHtml(comic.description)
                    }
                } ?: run {
                    descriptionText.visibility = View.INVISIBLE
                }

                populateCreators(context, view, comic.creators)

            }
        )

        viewModel.getComicData(getRandomComicId())
    }

    private fun getRandomComicId(): Int {
        val id = (0..6).random(Random(System.nanoTime()))
        return when (id) {
            0 -> 1158
            1 -> 291
            2 -> 1689
            3 -> 1749
            4 -> 15878
            5 -> 3627
            else -> 1308
        }
    }

    private fun populateCreators(context: Context?, rootView: View, creators: CreatorList) {
        val cap = if (creators.returned > 3) 3 else creators.returned
        context?.let { context ->
            val parentLayout = rootView.findViewById<ConstraintLayout>(R.id.main_constraint)
            var viewToAnchor = rootView.findViewById<TextView>(R.id.comic_title)
            for (i in 0 until cap) {
                val creator = creators.items[i]
                val textView = AppCompatTextView(context)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textView.setTextAppearance(R.style.CreatorText)
                } else {
                    textView.setTextAppearance(context, R.style.CreatorText)
                }

                val role = creator.role.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }

                val sb = SpannableString(getString(R.string.creator_title, role, creator.name))
                sb.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    role.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                textView.setText(sb, TextView.BufferType.SPANNABLE)

                val set = ConstraintSet()

                textView.id = View.generateViewId()
                parentLayout.addView(textView, 0)

                set.clone(parentLayout)

                set.connect(
                    textView.id,
                    ConstraintSet.TOP,
                    viewToAnchor.id,
                    ConstraintSet.BOTTOM,
                    resources.getDimensionPixelOffset(R.dimen.creator_spacing)
                )
                set.connect(textView.id, ConstraintSet.LEFT, viewToAnchor.id, ConstraintSet.LEFT)

                set.applyTo(parentLayout)

                viewToAnchor = textView
            }
        }

    }

    private fun loadImage(context: Context?, rootView: View, images: List<Image>) {
        val image = images.getOrNull(0)
        image?.let { image ->
            context?.let {
                Picasso.get()
                    .load("${image.path}.${image.extension}")
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(rootView.findViewById<ImageView>(R.id.comic_image))
            }
        }
    }
}