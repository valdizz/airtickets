package com.valdizz.airtickets

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.valdizz.airtickets.entity.Flight
import com.valdizz.airtickets.entity.FlightShedule
import com.valdizz.airtickets.entity.FlightType
import kotlinx.android.synthetic.main.activity_airticket.*

/**
 * Main class with container for two fragments.
 * The activity layout contains two buttons which responsible for navigation between fragments.
 *
 * @author Vlad Kornev
 */
class AirTicketActivity : AppCompatActivity(), ChangeDayNightModeListener {

    private val flightData = createData()
    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airticket)
        preferences = getSharedPreferences(AIR_TICKET_PREFERENCES, MODE_PRIVATE)
        if (preferences?.contains(DAY_NIGHT_MODE) == true) {
            val mode = preferences?.getInt(DAY_NIGHT_MODE, AppCompatDelegate.MODE_NIGHT_NO)
            AppCompatDelegate.setDefaultNightMode(mode ?: AppCompatDelegate.MODE_NIGHT_NO)
        }
        btn_fragment_constraint.setOnClickListener { replaceFragmentToConstraint() }
        btn_fragment_any.setOnClickListener { replaceFragmentToAny() }
        if (savedInstanceState == null) {
            replaceFragmentToConstraint()
        }
    }

    private fun replaceFragmentToAny() {
        supportFragmentManager.inTransaction { replace(R.id.fragment_container, AnyLayoutAirTicketFragment.newInstance(flightData)) }
    }

    private fun replaceFragmentToConstraint() {
        supportFragmentManager.inTransaction { replace(R.id.fragment_container, ConstraintAirTicketFragment.newInstance(flightData), CONSTRAINT_TAG) }
    }

    private fun createData(): FlightShedule {
        val departFlight = Flight(FlightType.DEPART, "16 SEP 2019", "435 BYN", "Free seats: 3", "MSQ", "FLO", "00:20", "09:20", "9:00")
        val returnFlight = Flight(FlightType.RETURN, "17 SEP 2019", "488 BYN", "Free seats: 5", "FLO", "MSQ", "05:10", "09:20", "4:10")
        return FlightShedule(departFlight, returnFlight)
    }

    override fun onChangeDayNightMode() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Toast.makeText(this, getString(R.string.msg_day_theme), Toast.LENGTH_SHORT).show()
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Toast.makeText(this, getString(R.string.msg_night_theme), Toast.LENGTH_SHORT).show()
        }
        preferences?.edit()
            ?.putInt(DAY_NIGHT_MODE, AppCompatDelegate.getDefaultNightMode())
            ?.apply()
        recreate()
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    companion object{
        const val FLIGHT_AGRS_KEY = "FlightArguments"
        private const val CONSTRAINT_TAG = "ConstraintFragmentTag"
        private const val AIR_TICKET_PREFERENCES = "Settings"
        private const val DAY_NIGHT_MODE = "DayNightMode"
    }
}
