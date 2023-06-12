package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Fragments.FavoriteFragment
import com.example.myapplication.Fragments.HomeFragment
import com.example.myapplication.Fragments.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var rv_list_tour: RecyclerView
    private var list = ArrayList<item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val homeFragment = HomeFragment()
        val favoriteFragment = FavoriteFragment()
        val settingFragment = SettingFragment()

        makeCurrentFragment(homeFragment)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ic_home ->makeCurrentFragment(homeFragment)
                R.id.ic_favorite ->makeCurrentFragment(favoriteFragment)
                R.id.ic_setting ->makeCurrentFragment(settingFragment)
            }
            true
        }

        rv_list_tour = findViewById(R.id.recyclerview_list)
        rv_list_tour.setHasFixedSize(true)

        list.addAll(listTours)
        showRecylerview()
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> Toast.makeText(this, "Delete selected", Toast.LENGTH_SHORT). show()
            R.id.nav_favorite -> Toast.makeText(this, "Favorite selected", Toast.LENGTH_SHORT). show()
            R.id.nav_third_item -> Toast.makeText(this, "Third item selected", Toast.LENGTH_SHORT). show()
        }
        return super.onOptionsItemSelected(item)
    }

    private val listTours: ArrayList<item>
        get() {
            val dataTitle = resources.getStringArray(R.array.data_name)
            val datadesk = resources.getStringArray(R.array.data_description)
            val dataimage = resources.obtainTypedArray(R.array.data_photo)
            val datalist = ArrayList<item>()

            for (i in dataTitle.indices){
                val tour = item(
                    dataTitle[i],
                    datadesk[i],
                    dataimage.getResourceId(i, -1)
                )
                datalist.add(tour)
            }
            return datalist
        }
    fun showRecylerview(){
        rv_list_tour.layoutManager = LinearLayoutManager(this)
        rv_list_tour.adapter=itemadapter(list)
    }

    fun touch(view: View) {
        findViewById<ImageView>(R.id.iv_image).animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.withEndAction {

            findViewById<ImageView>(R.id.iv_image).animate().apply {
                duration = 1000
                rotationYBy(3600f)

            }.start()

        }
    }

}