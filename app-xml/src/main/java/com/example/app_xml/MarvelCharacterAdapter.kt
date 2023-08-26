package com.example.app_xml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.app_xml.databinding.ItemMavelCharacterBinding
import com.example.app_xml.network.response.Result


class MarvelCharacterAdapter : RecyclerView.Adapter<MarvelCharacterViewHolder>() {
    private val characterList = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val marvelBinding =
            ItemMavelCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterViewHolder(marvelBinding)
    }


    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    fun addAll(list: List<Result>) {
        characterList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() = characterList.clear()


}

class MarvelCharacterViewHolder(private val binding: ItemMavelCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Result) {
        val thumbnail = item.thumbnail
        val url = thumbnail.path + "." + thumbnail.extension
        Glide.with(binding.root)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(binding.ivCharacter)


        binding.tvName.text = item.name
        binding.tvComicsCnt.text = item.comics.items?.size?.toString() ?: "0"
        binding.tvEventCnt.text = item.events.items?.size?.toString() ?: "0"
        binding.tvUrlsCnt.text = item.events.items?.size?.toString() ?: "0"
        binding.tvSeriesCnt.text = item.series.items?.size?.toString() ?: "0"
        binding.tvStoriesCnt.text = item.stories.items?.size?.toString() ?: "0"

        binding.ivCharacter.setOnClickListener {
            it.drawToBitmap()
            saveImage(url)
        }

    }

    private fun saveImage(url: String) {
        //포기!
    }
}




