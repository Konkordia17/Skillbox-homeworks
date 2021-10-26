package com.example.homework18

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationServices
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.random.Random

class ListFragment : Fragment(R.layout.fragment_location_list) {
    lateinit var positionInfo: String
    lateinit var locationList: RecyclerView
    lateinit var addLocationButton: Button
    lateinit var notLocation: TextView
    private val pictures = listOf(
        "https://img5.goodfon.ru/wallpaper/nbig/f/22/kosmos-prostranstvo-planety-tumannost-art.jpg",
        "https://w-dog.ru/wallpapers/1/85/426066645470644/art-kosmos-3d.jpg",
        "https://phonoteka.org/uploads/posts/2021-05/1622224417_13-phonoteka_org-p-kosmos-art-fantastika-krasivo-14.jpg",
        "https://phonoteka.org/uploads/posts/2021-05/1622466996_10-phonoteka_org-p-vselennaya-kosmos-art-krasivo-11.jpg",
        "https://img5.goodfon.ru/wallpaper/nbig/d/23/josef-barton-by-josef-barton-of-stars-and-spaces.jpg",
        "https://phonoteka.org/uploads/posts/2021-05/1622224385_28-phonoteka_org-p-kosmos-art-fantastika-krasivo-30.jpg",
        "https://phonoteka.org/uploads/posts/2021-06/1622503986_8-phonoteka_org-p-kosmos-art-galaktika-krasivo-8.jpg"
    )

    private var locations: List<Location> = emptyList()

    private var listAdapter: LocationAdapter? = null
    private var selectedInstant: Instant? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        locationList = requireView().findViewById(R.id.locationList)
        addLocationButton = requireView().findViewById(R.id.addLocationButton)
        notLocation = requireView().findViewById(R.id.notLocation)
        initList()
        addLocationButton.setOnClickListener {
            getLocation()
        }
        listAdapter?.updateLocations(locations)
        listAdapter?.notifyDataSetChanged()
    }

    private fun initList() {

        listAdapter = LocationAdapter { position -> clickedItem(position) }
        with(locationList) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun clickedItem(position: Int) {
        val currentDateTime = LocalDateTime.now()
        DatePickerDialog(
            requireContext(), { _, year, month, dayIfMonth ->
                TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        val zonedDateTime =
                            LocalDateTime.of(year, month + 1, dayIfMonth, hourOfDay, minute)
                                .atZone(ZoneId.systemDefault())
                        selectedInstant = zonedDateTime.toInstant()
                        locations[position].time = (selectedInstant)!!
                        listAdapter?.notifyItemChanged(position)
                    }, currentDateTime.hour,
                    currentDateTime.minute,
                    true
                ).show()
            },
            currentDateTime.year,
            currentDateTime.month.value - 1,
            currentDateTime.dayOfMonth
        ).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        listAdapter = null

    }


    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        LocationServices.getFusedLocationProviderClient(requireContext())
            .lastLocation
            .addOnSuccessListener {
                it?.let {
                    positionInfo = """
                        Lat = ${it.latitude}
                        Lng = ${it.longitude}
                        Alt = ${it.altitude}
                        Speed = ${it.speed}
                        Accuracy = ${it.accuracy}
                    """.trimIndent()
                    addLocation()

                } ?: toast("Локация отсутствует")
            }
            .addOnCanceledListener {
                toast("Запрос локации был отменен")
            }
            .addOnFailureListener {
                toast("Не удалось получить локацию")
            }
    }

    private fun addLocation() {
        notLocation.isVisible = false
        val newLocation =
            Location(
                Random.nextLong(),
                positionInfo,
                Instant.now(),
                pictures.random()
            )
        locations = listOf(newLocation) + locations
        listAdapter?.updateLocations(locations)
        listAdapter?.notifyItemInserted(0)
        locationList.scrollToPosition(0)
        selectedInstant = null
    }


    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}