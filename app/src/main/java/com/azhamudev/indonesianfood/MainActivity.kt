package com.azhamudev.indonesianfood

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.azhamudev.indonesianfood.R.array.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    private fun initData() {
        val name = resources.getStringArray(food_name)
        val image = resources.obtainTypedArray(food_image)
        val description = resources.getStringArray(food_description)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i],
                    image.getResourceId(i, 0), description[i]))
        }

        image.recycle()
    }

    private fun itemClicked(items: Item) {
        startActivity<DescriptionActivity>(DescriptionActivity.Title to items.name,
                DescriptionActivity.Image to items.image,
                DescriptionActivity.Description to items.description)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        food_list.layoutManager = LinearLayoutManager(this)
        food_list.adapter = RecyclerViewAdapter(this, items) {
            itemClicked(it)

        }
    }
}
