package ir.ah.vajehyabfarsi.base

import androidx.recyclerview.widget.*

abstract class BaseAdapter<T,H : RecyclerView.ViewHolder?> : ListAdapter<T, H>(DiffCallback<T>()) {

    var onItemClickListener: ((T) -> Unit)? = null

    @JvmName("setOnItemClickListener1")
    fun setOnItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }
}