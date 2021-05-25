package com.martakonik.sentiacodetest.ui.propertylist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.martakonik.sentiacodetest.R
import com.martakonik.sentiacodetest.data.Property
import com.martakonik.sentiacodetest.databinding.PropertyListItemBinding

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

    // Create new views (invoked by the layout manager)
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
        viewHolder.binding.propertyNameTextView.text = property.propertyType
        viewHolder.binding.locationTextView.text = property.location.address
        viewHolder.binding.agentNameTextView.text =
            ("${property.agent.firstName} ${property.agent.lastName}")
        viewHolder.binding.bedroomTextView.text = property.bedrooms.toString()
        viewHolder.binding.bathroomTextView.text = property.bathrooms.toString()
        viewHolder.binding.carspaceTextView.text = property.carspaces.toString()

        val propertyImageView: ImageView = viewHolder.binding.propertyImageView
        Glide.with(propertyImageView)
            .load(property.propertyImages[0].attachment.large.url)
            .centerCrop()
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .into(propertyImageView)

        val agentAvatarImageView = viewHolder.binding.agentAvatarImageView
        Glide.with(agentAvatarImageView)
            .load(property.agent.avatar.small.url)
            .apply(RequestOptions.circleCropTransform())
            .into(agentAvatarImageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}