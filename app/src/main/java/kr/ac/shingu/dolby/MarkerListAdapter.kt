package kr.ac.shingu.dolby

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.ac.shingu.dolby.databinding.CustomDialogBinding
import kr.ac.shingu.dolby.databinding.MarkerViewHolderBinding

class MarkerListAdapter(
    private val markerList: ArrayList<MarkerETY>,
    private val listener: SaveMarkerInterface
) :
    RecyclerView.Adapter<MarkerListAdapter.LatLngViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatLngViewHolder {
        val binding = MarkerViewHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LatLngViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatLngViewHolder, position: Int) {
        holder.bind(markerList[position], position)
    }

    override fun getItemCount(): Int = markerList.size

    inner class LatLngViewHolder(private val binding: MarkerViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(dto: MarkerETY, position: Int) {
            val nameNumber = position + 1
            with(binding) {
                name.text = "marker$nameNumber"
                lat.setText(dto.latitude.toString())
                lon.setText(dto.longitude.toString())
                deleteBtn.setOnClickListener {
                    markerList.removeAt(position)
                    listener.updateMarker(markerList)
                    notifyDataSetChanged()
                }
            }

        }

    }

}