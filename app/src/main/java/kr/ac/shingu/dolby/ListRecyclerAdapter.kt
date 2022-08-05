package kr.ac.shingu.dolby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.shingu.dolby.databinding.RoomDbBySeongyoolBinding

class ListRecyclerAdapter(private val memoList: List<RoomMemo>) :
    RecyclerView.Adapter<ListRecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RoomDbBySeongyoolBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(memoList[position])
    }

    override fun getItemCount(): Int = memoList.size

    class Holder(private val binding: RoomDbBySeongyoolBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setMemo(roomMemo: RoomMemo) {
            with(binding) {
                textNumber.text = "${roomMemo.no}"
                latValue.text = roomMemo.content.toString()
                lonValue.text = roomMemo.content2.toString()
            }
        }
    }
}