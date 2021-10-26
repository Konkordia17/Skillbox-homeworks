package com.example.homework18

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment


class DangerousPermissionFragment : Fragment(R.layout.fragment_dangerous_permission) {
    private val navigator: Navigator?
        get() {
            return activity?.let { it as? Navigator }
        }
    private var dialog: AlertDialog? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLocationRationaleDialog()
    }


    private fun showCurrentLocationPermissionCheck() {
        val isLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (isLocationPermissionGranted) {
            showLocationInfo()
        } else {
            requestLocationPermission()

        }
    }

    private fun showLocationRationaleDialog() {
        dialog = AlertDialog.Builder(requireContext())
            .setMessage("Для получения списка локаций необходимо разрешение")
            .setPositiveButton("Разрешить", { _, _ -> showCurrentLocationPermissionCheck() })
            .show()
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
            Toast.makeText(
                context,
                "Не возможно получить локацию без разрешения доступа",
                Toast.LENGTH_SHORT
            ).show()
            showLocationRationaleDialog()
        }
    }

    private fun requestLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)

    }

    companion object {
        private const val REQUEST_CODE = 1
    }

    private fun showLocationInfo() {
        navigator?.navigateTo(ListFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
        dialog = null
    }
}