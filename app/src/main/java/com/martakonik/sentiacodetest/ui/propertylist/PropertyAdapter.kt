package com.martakonik.sentiacodetest.ui.propertylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martakonik.sentiacodetest.R
import com.martakonik.sentiacodetest.data.Property
import com.martakonik.sentiacodetest.databinding.PropertyListItemBinding
import com.martakonik.sentiacodetest.utils.loadAvatarCircularImage
import com.martakonik.sentiacodetest.utils.loadImageWithUrl

class PropertyAdapter(
    private val onItemClicked: (String) -> Unit
) :
    RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    var dataSet = emptyList<Property>()

    class ViewHolder(
        val binding: PropertyListItemBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = PropertyListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(view) {
            onItemClicked(dataSet[it].id)
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val property = dataSet[position]
        viewHolder.binding.run {
            propertyNameTextView.text = property.propertyType
            locationTextView.text = property.location.address
            agentNameTextView.text = ("${property.agent.firstName} ${property.agent.lastName}")
            bedroomTextView.text = property.bedrooms.toString()
            bathroomTextView.text = property.bathrooms.toString()
            carspaceTextView.text = property.carspaces.toString()
            propertyImageView.loadImageWithUrl(
                property.propertyImages[0].attachment.large.url,
                R.drawable.ic_baseline_image_not_supported_24
            )
            agentAvatarImageView.loadAvatarCircularImage(
                property.agent.avatar.small.url
            )
        }
    }

    override fun getItemCount() = dataSet.size

}