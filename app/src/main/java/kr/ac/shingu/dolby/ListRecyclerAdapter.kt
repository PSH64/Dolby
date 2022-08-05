package kr.ac.shingu.dolby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.shingu.dolby.databinding.RoomDbBySeongyoolBinding

class ListRecyclerAdapter(val ListSeong:List<ListSeong<Any?>>) : RecyclerView.Adapter<ListRecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(ListSeong.get(position))
    }

    override fun getItemCount(): ListSeong.size
}
    class Holder(binding: RoomDbBySeongyoolBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setMemo(memo:ListSeong) {

    }
}