package ir.ah.vajehyabfarsi.ui.fragment.search.adapter

import android.view.*
import androidx.recyclerview.widget.*
import androidx.viewbinding.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.databinding.*

class VajehAdapter:BaseAdapter<Vajeh, VajehAdapter.VajehViewHolder>() {

    private var vajehEventListener:VajehEventListener?=null
   inner class VajehViewHolder(private val binding: ItemVajehBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(vajeh: Vajeh) {
            binding.apply {
                title.text=vajeh.title
                source.text=vajeh.source
                text.text=vajeh.text
                bookmarkBorder.setOnClickListener {
                    vajehEventListener?.onFavorite(vajeh)
                }
                bookmark.setOnClickListener {
                    vajehEventListener?.onRemoveFavorite(vajeh)
                }

            }
        }

    }
    fun setOnOrderItemEventListener(vajehEventListener:VajehEventListener) {
        this.vajehEventListener = vajehEventListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VajehViewHolder {
        val binding = ItemVajehBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VajehViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VajehViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    interface VajehEventListener {
        fun onFavorite(vajeh: Vajeh)
        fun onRemoveFavorite(vajeh: Vajeh)
    }
}