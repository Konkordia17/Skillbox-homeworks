package com.example.homework18

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices

class DangerousPermissionFragment : Fragment(R.layout.fragment_dangerous_permission) {
    private var rationaleDialog: AlertDialog? = null
    lateinit var positionTextView:TextView

    lateinit var getCurrentLocationButton: Button
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        positionTextView = requireView().findViewById(R.id.positionTextView)
        getCurrentLocationButton = requireView().findViewById(R.id.getCurrentLocationButton)
        getCurrentLocationButton.setOnClickListener {
            showCurrentLocationWithPermissionCheck()
        }

    }

    private fun showCurrentLocationWithPermissionCheck() {
        val isLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (isLocationPermissionGranted) {
            showLocationInfo()
        } else {
            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (needRationale) {
                showLocationRationaleDialog()

            } else {
                requestLocationPermission()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            showLocationInfo()
        } else {
            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (needRationale) {
                showLocationRationaleDialog()
            }
        }
    }


    private fun showLocationInfo() {
        LocationServices.getFusedLocationProviderClient(requireContext())
            .lastLocation
            .addOnSuccessListener {
                it?.let {
                    positionTextView.text  = """
                        Lat = ${it.latitude}
                        Lng = ${it.longitude}
                        Alt = ${it.altitude}
                        Speed = ${it.speed}
                        Accuracy = ${it.accuracy}
                    """.trimIndent()

                }?: Toast.makeText(context, "Локация отсутствует", Toast.LENGTH_SHORT).show()
            }
            .addOnCanceledListener {
                Toast.makeText(context, "Запрос локации был отменен", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Запрос локации завершился неудачно", Toast.LENGTH_SHORT)
                    .show()
            }

    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )
    }

    private fun showLocationRationaleDialog() {
        rationaleDialog = AlertDialog.Builder(requireContext())
            .setMessage("Необходимо одобрение разрешения информации по локации")
            .setPositiveButton("OK", { _, _ -> requestLocationPermission() })
            .setNegativeButton("Отмена", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rationaleDialog?.dismiss()
        rationaleDialog = null
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 4313
    }
}