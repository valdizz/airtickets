package com.valdizz.airtickets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.valdizz.airtickets.entity.Flight
import com.valdizz.airtickets.entity.FlightShedule
import com.valdizz.airtickets.entity.FlightType
import kotlinx.android.synthetic.main.activity_airticket.*

class AirTicketActivity : AppCompatActivity() {

    private val flightData = createData()
    private val isConstraintFragment
        get() = supportFragmentManager.findFragmentByTag(CONSTRAINT_TAG) != null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airticket)
        btn_fragment_constraint.setOnClickListener { replaceFragmentToConstraint() }
        btn_fragment_any.setOnClickListener { replaceFragmentToAny() }
        val isConstraint = savedInstanceState?.getBoolean(CONSTRAINT_KEY) ?: true
        if (isConstraint) {
            replaceFragmentToConstraint()
        }
        else {
            replaceFragmentToAny()
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
        val flightShedule = FlightShedule(departFlight, returnFlight)
        return flightShedule
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(CONSTRAINT_KEY, isConstraintFragment)
        super.onSaveInstanceState(outState)
    }

    companion object{
        const val FLIGHT_AGRS_KEY = "FlightArguments"
        private const val CONSTRAINT_KEY = "ConstraintKey"
        private const val CONSTRAINT_TAG = "ConstraintFragmentTag"
    }
}
