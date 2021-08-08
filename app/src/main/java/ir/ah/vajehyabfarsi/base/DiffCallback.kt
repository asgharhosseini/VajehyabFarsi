package ir.ah.vajehyabfarsi.base

import androidx.recyclerview.widget.*

class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: T, newItem: T) =
            oldItem.hashCode() == newItem.hashCode()
    }