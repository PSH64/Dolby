/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.ac.shingu.dolby.geospatial.helpers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kr.ac.shingu.dolby.R
import kr.ac.shingu.dolby.HelloGeoActivity

class MapView(val activity: HelloGeoActivity, val googleMap: GoogleMap) {
    private val CAMERA_MARKER_COLOR: Int = Color.argb(255, 0, 0, 255)
    val EARTH_MARKER_COLOR: Int = Color.argb(255, 255, 0, 0)
    var cameraIdle = true
    var setInitialCameraPosition = false
    private val myLocMarker = createMarker(CAMERA_MARKER_COLOR) // MARKER
//  val earthMarker = createMarker(EARTH_MARKER_COLOR)

    init {
        googleMap.uiSettings.apply {
            isMapToolbarEnabled = false
            isIndoorLevelPickerEnabled = false
            isZoomControlsEnabled = false
            isTiltGesturesEnabled = false
            isScrollGesturesEnabled = false
        }

        googleMap.setOnMarkerClickListener { unused -> false }

        // Add listeners to keep track of when the GoogleMap camera is moving.
        googleMap.setOnCameraMoveListener { cameraIdle = false }
        googleMap.setOnCameraIdleListener { cameraIdle = true }
    }

    fun updateMapPosition(latitude: Double, longitude: Double, heading: Double) {
        val position = LatLng(latitude, longitude)
        activity.runOnUiThread {
            // If the map is already in the process of a camera update, then don't move it.
            if (!cameraIdle) {
                return@runOnUiThread
            }
            myLocMarker.isVisible = true
            myLocMarker.position = position
            myLocMarker.rotation = heading.toFloat()

            val cameraPositionBuilder: CameraPosition.Builder = if (!setInitialCameraPosition) {
                // Set the camera position with an initial default zoom level.
                setInitialCameraPosition = true
                CameraPosition.Builder().zoom(15f).target(position)
            } else {
                // Set the camera position and keep the same zoom level.
                CameraPosition.Builder()
                    .zoom(googleMap.cameraPosition.zoom)
                    .target(position)
            }
            googleMap.moveCamera(
                CameraUpdateFactory.newCameraPosition(cameraPositionBuilder.build())
            )
        }
    }

    /** Creates and adds a 2D anchor marker on the 2D map view.  */
    private fun createMarker(
        color: Int,
    ): Marker {
        val markersOptions = MarkerOptions()
            .position(LatLng(0.0, 0.0))
            .draggable(false)
            .anchor(0.5f, 0.5f)
            .flat(true)
            .visible(false)
            .icon(BitmapDescriptorFactory.fromBitmap(createColoredMarkerBitmap(color)))
        return googleMap.addMarker(markersOptions)!!
    }

    fun createMarker(
        color: Int, latlng: LatLng
    ): Marker {
        val markersOptions = MarkerOptions()
            .position(latlng)
            .draggable(false)
            .anchor(0.5f, 0.5f)
            .flat(true)
            .visible(true)
            .icon(BitmapDescriptorFactory.fromBitmap(createColoredMarkerBitmapp(color)))
        return googleMap.addMarker(markersOptions)!!
    }

    private fun createColoredMarkerBitmap(@ColorInt color: Int): Bitmap {
        val opt = BitmapFactory.Options()
        opt.inMutable = true
        val navigationIcon =
            BitmapFactory.decodeResource(
                activity.resources,
                R.drawable.ic_navigation_white_48dp,
                opt
            )
        val p = Paint()
        p.colorFilter = LightingColorFilter(color,  /* add= */1)
        val canvas = Canvas(navigationIcon)
        canvas.drawBitmap(navigationIcon,  /* left= */0f,  /* top= */0f, p)
        return navigationIcon
    }
    private fun createColoredMarkerBitmapp(@ColorInt color: Int): Bitmap {
        val opt = BitmapFactory.Options()
        opt.inMutable = true
        val navigationIcon =
            BitmapFactory.decodeResource(
                activity.resources,
                R.drawable.ic_navigation_white_48dpp,
                opt
            )
        val p = Paint()
        p.colorFilter = LightingColorFilter(color,  /* add= */1)
        val canvas = Canvas(navigationIcon)
        canvas.drawBitmap(navigationIcon,  /* left= */0f,  /* top= */0f, p)
        return navigationIcon
    }
}