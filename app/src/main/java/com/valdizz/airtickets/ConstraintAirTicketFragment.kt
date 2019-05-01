package com.valdizz.airtickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valdizz.airtickets.entity.FlightShedule
import kotlinx.android.synthetic.main.fragment_anylayout_airticket.*
import kotlinx.android.synthetic.main.fragment_constraint_airticket.view.*

/**
 * Fragment class. The fragment layout uses only ConstraintLayout.
 * Floating action button can change day/night theme.
 *
 * @author Vlad Kornev
 */
class ConstraintAirTicketFragment : Fragment(){

    private var changeDayNightModeListener: ChangeDayNightModeListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ChangeDayNightModeListener) {
            changeDayNightModeListener = context
        }
        else {
            throw ClassCastException(context.toString() + " must implement ChangeDayNightModeListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_constraint_airticket, container, false)
        arguments?.get(AirTicketActivity.FLIGHT_AGRS_KEY)?.let { setViewData(it as FlightShedule, view) }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener { changeDayNightModeListener?.onChangeDayNightMode() }
    }

    private fun setViewData(flightShedule: FlightShedule, view: View) {
        view.tv_depart_title.text = flightShedule.departFlight.flightType.toString()
        view.tv_depart_date.text = flightShedule.departFlight.date
        view.tv_depart_free_seats.text = flightShedule.departFlight.freeSeats
        view.tv_depart_from.text = flightShedule.departFlight.fromAirport
        view.tv_depart_to.text = flightShedule.departFlight.toAirport
        view.tv_depart_price.text = flightShedule.departFlight.price
        view.tv_depart_time_takeoff.text = flightShedule.departFlight.timeTakeoff
        view.tv_depart_time_land.text = flightShedule.departFlight.timeLand
        view.tv_depart_flight_time.text = flightShedule.departFlight.timeFlight

        view.tv_return_title.text = flightShedule.returnFlight.flightType.toString()
        view.tv_return_date.text = flightShedule.returnFlight.date
        view.tv_return_free_seats.text = flightShedule.returnFlight.freeSeats
        view.tv_return_from.text = flightShedule.returnFlight.fromAirport
        view.tv_return_to.text = flightShedule.returnFlight.toAirport
        view.tv_return_price.text = flightShedule.returnFlight.price
        view.tv_return_time_takeoff.text = flightShedule.returnFlight.timeTakeoff
        view.tv_return_time_land.text = flightShedule.returnFlight.timeLand
        view.tv_return_flight_time.text = flightShedule.returnFlight.timeFlight
    }

    companion object {
        fun newInstance(flightShedule: FlightShedule) : ConstraintAirTicketFragment {
            val args = Bundle().apply { putParcelable(AirTicketActivity.FLIGHT_AGRS_KEY, flightShedule) }
            val fragment = ConstraintAirTicketFragment().apply { arguments = args }
            return fragment
        }

    }
}