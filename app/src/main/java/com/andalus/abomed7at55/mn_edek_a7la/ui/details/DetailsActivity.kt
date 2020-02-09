package com.andalus.abomed7at55.mn_edek_a7la.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.item_tag.view.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tags = listOf("مخبوزات")

        tags.forEach { title ->
            val inflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val tag = inflater.inflate(R.layout.item_tag, null)
            tag.tvTagName.text = title
            tag.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java).apply { putExtra("tag", title) })
            }
            tagsHolder.addView(tag)
        }

    }
}
