package com.example.homework16

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.homework16.adapters.ImageAdapter

class ImageListFragment : Fragment(R.layout.fragment_image_list) {
    private lateinit var imageList: RecyclerView

    private val images = listOf(
        "https://c.wallhere.com/photos/02/f5/2048x1365_px_cliff_clouds_forest_grass_hdr_landscape_mountain-698862.jpg!d",
        "https://www.ejin.ru/wp-content/uploads/2017/09/11-681.jpg",
        "https://i.artfile.ru/2048x1365_858222_[www.ArtFile.ru].jpg",
        "https://i.artfile.ru/4000x2674_772417_[www.ArtFile.ru].jpg",
        "https://i.pinimg.com/736x/7f/c5/b1/7fc5b1b55f7cae4bdfec37581d223077--banff-alberta-alberta-canada.jpg"
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageList = requireView().findViewById(R.id.imageList)
        initList()
    }

    private fun initList() = with(imageList) {
        adapter = ImageAdapter().apply {
            setImages(images + images + images + images)
        }
        setHasFixedSize(true)
//        val dividerItemDecoration =
//            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
//        addItemDecoration(dividerItemDecoration)
//        addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))

        addItemDecoration(ItemOffsetDecoration(requireContext()))
        layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL).apply {

        }
    }
}

