package kr.ac.shingu.dolby

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MarkerListAdapter(private val markerList: ArrayList<MarkerDTO>) :
    RecyclerView.Adapter<MarkerListAdapter.LatLngViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatLngViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marker_view_holder, parent, false)
        return LatLngViewHolder(view)
    }

    override fun onBindViewHolder(holder: LatLngViewHolder, position: Int) {
        holder.bind(markerList[position], position)
    }

    override fun getItemCount(): Int = markerList.size

    inner class LatLngViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameView = view.findViewById<TextView>(R.id.name)
        private val latEditView = view.findViewById<EditText>(R.id.lat)
        private val lonEditView = view.findViewById<EditText>(R.id.lon)
        private val deleteButton = view.findViewById<ImageButton>(R.id.delete_btn)

        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(dto: MarkerDTO, position: Int) {
            val nameNumber = position + 1
            nameView.text = "marker$nameNumber"
            latEditView.setText(dto.latitude.toString())
            lonEditView.setText(dto.longitude.toString())
            deleteButton.setOnClickListener {
                markerList.removeAt(position)
                notifyDataSetChanged()
            }
        }

    }

}