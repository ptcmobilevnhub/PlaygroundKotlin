package com.example.pyco.toannckotlinassessment.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object KLFriendContent {


    var ITEMS: List<KLFriend> = ArrayList()

    val ITEM_MAP: MutableMap<String, KLFriend> = HashMap()

    private val COUNT = 10

    init { }

    private fun addItem(item: KLFriend) {
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): KLFriend {
        return KLFriend(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }
}

@Parcelize
data class KLFriend(val id: String, val name: String, val avatarUrl : String): Parcelable
