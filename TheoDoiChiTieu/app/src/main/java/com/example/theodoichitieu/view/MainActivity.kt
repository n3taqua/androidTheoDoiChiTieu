package com.example.theodoichitieu.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.theodoichitieu.R
import com.example.theodoichitieu.model.Catogories
import com.example.theodoichitieu.util.yearToLongYear
import com.example.theodoichitieu.view.income.IncomeFragment
import com.example.theodoichitieu.view.intro.IntroFragment
import com.example.theodoichitieu.view.pay.PayFragment
import com.example.theodoichitieu.view.report.ReportFragment
import com.example.theodoichitieu.viewmodel.CatogoriesViewModel
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_add_account_line.*

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var myCatagoriesViewModel: CatogoriesViewModel
    var FRAGMENT_STATUS="Intro"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       setSupportActionBar(toolbar)

        setContentWidget()
        setEvent()
        seederData()
        Stetho.initializeWithDefaults(this);
    }

    private fun seederData() {
        myCatagoriesViewModel.getAllCatogories().observe(this, androidx.lifecycle.Observer {
            if (it.size==0){
                val cataSeedder1=Catogories("Muc Chung","Muc Chung Luu Tru Thong Tin")
                myCatagoriesViewModel.insert(cataSeedder1)
            }
        })

    }

    private fun setEvent() {
        fab.setOnClickListener {
            intent= Intent(this,AddAccountLineActivity::class.java)
            startActivity(intent)
        }
    }



    private fun setContentWidget() {
     //   setSupportActionBar(findViewById(R.id.toolbar))
        setBottomNavListenner()
        getVieModel()
    }

    private fun getVieModel() {
        myCatagoriesViewModel= ViewModelProvider(this).get(CatogoriesViewModel::class.java)
    }

    private fun setBottomNavListenner() {
        val currentDay:Calendar= Calendar.getInstance();
        val today:Long= yearToLongYear(currentDay)
        var selectedFragment : Fragment =IntroFragment(today)
        setFragmentPreviousOpen(selectedFragment,today)
        bottom_nav_app.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_income -> {
                    selectedFragment=
                       IncomeFragment(today)
                    FRAGMENT_STATUS="Fragment_income"

                }
                R.id.nav_pay -> {
                    selectedFragment=
                        PayFragment(today)
                    FRAGMENT_STATUS="Fragment_pay"

                }
                R.id.nav_info-> {
                   selectedFragment=
                       ReportFragment(today)
                    FRAGMENT_STATUS="Fragment_info"
                }
                R.id.nav_intro->{
                    selectedFragment=
                        IntroFragment(today)
                    FRAGMENT_STATUS="Fragment_intro"
                }
            }
            startFragment(selectedFragment)
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun startFragment(selectedFragment:Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fg_main, selectedFragment)
            .addToBackStack(null)
            .commit()
    }
    private fun setFragmentPreviousOpen(selectedFragment:Fragment,today:Long) {
        var selectedFragment=selectedFragment
        val sharedPreferences = getSharedPreferences("PREFERCENCE_PRAGMENT", MODE_PRIVATE)
        FRAGMENT_STATUS= sharedPreferences.getString("PREFERCENCE_PRAGMENT_STATUS","Fragment_intro").toString()
        when(FRAGMENT_STATUS){
            "Fragment_income"->{
                selectedFragment=
                    IncomeFragment(today)
            }
            "Fragment_pay"->{
                selectedFragment=
                    PayFragment(today)
            }
            "Fragment_info"->{
                selectedFragment=
                    ReportFragment(today)
            }
            "Fragment_intro"->{
                selectedFragment=
                    IntroFragment(today)
            }
        }
        startFragment(selectedFragment)
    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = getSharedPreferences("PREFERCENCE_PRAGMENT", MODE_PRIVATE)
        sharedPreferences.edit().putString("PREFERCENCE_PRAGMENT_STATUS",FRAGMENT_STATUS).apply()
    }

}
