package ir.ah.vajehyabfarsi.ui.fragment.favorite.adapter

import android.view.*
import androidx.recyclerview.widget.*
import ir.ah.vajehyabfarsi.*
import ir.ah.vajehyabfarsi.base.*
import ir.ah.vajehyabfarsi.data.local.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.databinding.*
import kotlinx.coroutines.flow.*
import javax.inject.*

class FavoriteAdapter @Inject constructor(private val database: VajehDao) :
    BaseAdapter<Vajeh, FavoriteAdapter.VajehViewHolder>() {
    private var favoriteEventListener: FavoriteEventListener? = null

    inner class VajehViewHolder(private val binding: ItemVajehBinding) :
        RecyclerView.ViewHolder(binding.root) {

         fun bind(vajeh: Vajeh) {
            binding.apply {
                title.text = vajeh.title
                source.text = vajeh.source
                text.text = vajeh.text
                var bookmarkFlag = false
                database.checkVajehsFavorite(vajeh.id).observeForever {
                    if (it) {
                        binding.bookmark.setImageResource(R.drawable.ic_bookmark)
                        bookmarkFlag = it
                    } else {
                        binding.bookmark.setImageResource(R.drawable.ic_bookmark_border)
                        bookmarkFlag = it
                    }
                }
                binding.bookmark.setOnClickListener {
                    favoriteEventListener?.onFavorite(vajeh, position, bookmarkFlag)
                    if (bookmarkFlag) {
                        binding.bookmark.setImageResource(R.drawable.ic_bookmark)

                    } else {
                        binding.bookmark.setImageResource(R.drawable.ic_bookmark_border)
                        bookmarkFlag = false
                    }
                }



            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VajehViewHolder {
        val binding = ItemVajehBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VajehViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VajehViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    interface FavoriteEventListener {
        fun onFavorite(vajeh: Vajeh, position: Int, flag: Boolean)
    }

    fun setOnFavoriteItemEventListener(favoriteEventListener: FavoriteEventListener) {
        this.favoriteEventListener = favoriteEventListener
    }
}