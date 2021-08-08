package ir.ah.vajehyabfarsi.ui.fragment.home.adapter

import android.view.*
import androidx.recyclerview.widget.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.databinding.*

class HistoryAdapter  :
    BaseAdapter<History, HistoryAdapter.HistoryViewHolder>() {
    private var deleteEventListener: DeleteEventListener? = null

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

         fun bind(history: History) {
            binding.apply {
                titleHistory.text = history.title

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    interface DeleteEventListener {
        fun onDelete(history: History, position: Int)
    }

    fun setOnDeleteItemEventListener(deleteEventListener: DeleteEventListener) {
        this.deleteEventListener = deleteEventListener
    }
}