package com.example.pyco.toannckotlinassessment.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pyco.toannckotlinassessment.R

import com.example.pyco.toannckotlinassessment.Data.*
import com.example.pyco.toannckotlinassessment.Services.KLApiServices
import com.example.pyco.toannckotlinassessment.Services.KLFriendServices
import kotlinx.android.synthetic.main.activity_klfriend_list.*
import kotlinx.android.synthetic.main.klfriend_list_content.view.*
import kotlinx.android.synthetic.main.klfriend_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [KLFriendDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class KLFriendListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_klfriend_list)

        setSupportActionBar(toolbar)
        toolbar.title = "Friend List"

        if (klfriend_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(klfriend_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this,
                KLFriendContent.ITEMS, twoPane)
        // call api
        val mockService = KLApiServices.getInstance().create(KLFriendServices::class.java)
        val request = mockService.getFriendList()

        request.enqueue(object: Callback<List<KLFriend>> {
            override fun onFailure(call: Call<List<KLFriend>>, t: Throwable) {}

            override fun onResponse(call: Call<List<KLFriend>>,
                                    response: Response<List<KLFriend>>) {
                KLFriendContent.ITEMS = response.body()!!
                recyclerView.adapter?.notifyDataSetChanged()
            }
        })
    }

    class SimpleItemRecyclerViewAdapter(private val parentActivity: KLFriendListActivity,
                                        private val values: List<KLFriend>,
                                        private val twoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as KLFriend
                if (twoPane) {
                    val fragment = KLFriendDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(KLFriendDetailFragment.ARG_ITEM_ID, item.id)
                        }
                    }
                    parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.klfriend_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, KLFriendDetailActivity::class.java).apply {
                        putExtra(KLFriendDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.klfriend_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id
            holder.contentView.text = item.name

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}
