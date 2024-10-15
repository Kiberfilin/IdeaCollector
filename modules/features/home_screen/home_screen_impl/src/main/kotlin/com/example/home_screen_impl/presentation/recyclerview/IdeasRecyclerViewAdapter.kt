package com.example.home_screen_impl.presentation.recyclerview

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.home_screen_impl.R
import com.example.home_screen_impl.presentation.converters.DateFormatConverterForRecyclerView
import com.example.ui_kit.databinding.LayoutIdeaListItemBinding

class IdeasRecyclerViewAdapter :
    ListAdapter<IdeaEntity, IdeasRecyclerViewAdapter.IdeasRecyclerViewViewHolder>(IdeaComparator()) {
    companion object {
        private var positionOfIdeaFromContextMenu: Int = RecyclerView.NO_POSITION
    }

    fun getCurrentIdeaFromContextMenu(): IdeaEntity = currentList[positionOfIdeaFromContextMenu]
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IdeasRecyclerViewViewHolder {
        val binding = LayoutIdeaListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return IdeasRecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IdeasRecyclerViewViewHolder, position: Int) {
        val currentIdea = getItem(position)
        holder.bind(currentIdea)
    }

    class IdeasRecyclerViewViewHolder(private val binding: LayoutIdeaListItemBinding) :
        RecyclerView.ViewHolder(binding.root), OnCreateContextMenuListener {
        init {
            binding.root.setOnCreateContextMenuListener(this)
        }

        fun bind(idea: IdeaEntity) {
            binding.apply {
                priorityIcon.setBlueCornerVisible(false)
                priorityIcon.setPriority(idea.priority)
                ideaText.text = idea.ideaText
                ideaDate.text =
                    DateFormatConverterForRecyclerView.convertFromMillsToString(idea.date)
            }
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            positionOfIdeaFromContextMenu = adapterPosition
            menu?.apply {
                setHeaderTitle(R.string.context_menu_title)
                add(0, R.id.editItem, 0, R.string.edit_idea)
                add(0, R.id.deleteItem, 0, R.string.delete_idea)
            }
        }
    }

    class IdeaComparator : DiffUtil.ItemCallback<IdeaEntity>() {
        override fun areItemsTheSame(oldItem: IdeaEntity, newItem: IdeaEntity): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: IdeaEntity, newItem: IdeaEntity): Boolean =
            oldItem == newItem
    }
}