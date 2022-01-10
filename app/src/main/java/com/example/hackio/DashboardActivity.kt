package com.example.hackio

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

//import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    // declare the GoogleSignInClient
    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        setSupportActionBar(findViewById(R.id.toolbar))

        navigation_view.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "nav home me ho", Toast.LENGTH_SHORT).show()
                    val alterboxbuilder = AlertDialog.Builder(this)
                    alterboxbuilder.setTitle("Logout")
                    alterboxbuilder.setMessage("Do you want to Logout?")
                    alterboxbuilder.setIcon(R.drawable.ic_logout)
                    alterboxbuilder.setPositiveButton("YES",){dialogInterface, which ->
                        loggout()
                    }
                    alterboxbuilder.setNeutralButton("Cancel"){dialogInterface , which ->
                        Toast.makeText(applicationContext,"Canceled",Toast.LENGTH_LONG).show()
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    val alertd : AlertDialog = alterboxbuilder.create()
                    alertd.show()

                }
                R.id.akshay -> {

                    val viewIntent = Intent(
                        "android.intent.action.VIEW",
                        Uri.parse("https://www.linkedin.com/in/-akshay-kumar-/")
                    )
                    startActivity(viewIntent)
                }
                R.id.kanp -> {

                    val viewIntent = Intent(
                        "android.intent.action.VIEW",
                        Uri.parse("https://www.linkedin.com/in/kanupriya--jain/")
                    )
                    startActivity(viewIntent)
                }
                R.id.vbh -> {

                    val viewIntent = Intent(
                        "android.intent.action.VIEW",
                        Uri.parse("https://www.linkedin.com/in/vaibhav-gupta-451825200")
                    )
                    startActivity(viewIntent)
                }


            }
            true
        }

        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        logout.setOnClickListener {
//            loggout()
//        }

        setUpTabs()
    }

    private fun setUpTabs() {

        val tab_toolbar = findViewById<Toolbar>(R.id.toolbar)
        val tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        val tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)

        setSupportActionBar(tab_toolbar)
        setUpViewPager(tab_viewpager)

        tab_tablayout.setupWithViewPager(tab_viewpager)

    }

    private fun setUpViewPager(viewPager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(OngoingFragment(), "Ongoing")
        adapter.addFragment(UpcomingFragment(), "Upcoming")

        viewPager.setAdapter(adapter)
    }

    class ViewPagerAdapter: FragmentPagerAdapter {
        private final var fragmentList1: ArrayList<Fragment> = ArrayList()
        private final var fragmentTitleList1: ArrayList<String> = ArrayList()

        public constructor(supportFragmentManager: FragmentManager) : super(supportFragmentManager)

        override fun getItem(position: Int): Fragment {
            return fragmentList1.get(position)
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1.get(position)
        }

        override fun getCount(): Int {
            return fragmentList1.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }

    private fun loggout() {
        // call requestIdToken as follows
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient.signOut().addOnCompleteListener {
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
