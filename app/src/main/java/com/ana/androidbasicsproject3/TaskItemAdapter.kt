package com.caren.androidbasicsproject3

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ana.androidbasicsproject3.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskItemAdapter(
    private val tasks: MutableList<Taskwritten>,
    private val myListener: onItemClickListener
) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    //private lateinit var myListener: onItemClickListener

    // Created a new Interface onItemclickListener
    interface onItemClickListener {
        fun onItemClick(index: Int)
    }

    //   fun setOnItemClickListener(listener: onItemClickListener) {
    //       myListener =
    //           listener // myListener is the local variable and listener will be passed as an arguement
    //   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val inflater = LayoutInflater.from(parent.context)
        val taskItemView = inflater.inflate(R.layout.item_task, parent, false)
        return TaskItemAdapter.ViewHolder(
            taskItemView,
            myListener
        )
        // need to have 2 parameters because we changed our viewholder so it can take a clicklistener
    }

    // Will create a new behavior that if the checkbox is checked there will be a strikeThrough

    private fun strikeThroughLine(genericTextView: TextView, isChecked: Boolean) {
        if (isChecked) {// to do item is checked
            genericTextView.paintFlags = genericTextView.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {// to do item is unchecked
            genericTextView.paintFlags = genericTextView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task =
            tasks.get(position)// This gives us a reference to the Mutablelist called tasks, and we "get the (position)
        // set the new "task" variable to the mutable list of task position
        // also use the holder, use the items of the "items layout" to match the new variable and atta
        holder.genericTextView.text =
            task.todoTask// we need set our new variable task to our current textView, and the class variable "TaskWritten" variable we intialized
        holder.checkItOff.isChecked =
            task.isChecked// we set the checkbox view, to our new "task" variable with the position, and the class variable "TaskWritten" for isChecked with the boolean value
        // calling the striketthroughline function, passing through "genericTextView" & "checkbox
        strikeThroughLine(
            holder.genericTextView,
            holder.checkItOff.isChecked
        )// This is a function that when we click on the checkbox it will check it off.
        // Want to call this function when our item is done and set an on checked listener


        holder.checkItOff.setOnCheckedChangeListener { _, isChecked -> // This is a lamdada function, underscore means we don't need parameter, and isCheck is the state, we are checking
            // on the setOnCheckChangeListener, it will check if it is checked, if is true or falsa
            strikeThroughLine(holder.genericTextView, isChecked)
            task.isChecked =
                !task.isChecked // if the current task item is checked and is true,  it will be inverted to false & the opposite applies

        }
        holder.genericTextView.setOnClickListener {
            myListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


    class ViewHolder(itemView: View, listener: TaskItemAdapter.onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        // Specifically call each row of the item layout an itemView
        val genericTextView = itemView.findViewById<TextView>(R.id.task)
        val checkItOff = itemView.findViewById<CheckBox>(R.id.checkbox)
        val fabBtn = itemView.findViewById<FloatingActionButton>(R.id.fabAdd)
        val fabBtnDel = itemView.findViewById<FloatingActionButton>(R.id.fabDelete)
        val editTx = itemView.findViewById<EditText>(R.id.taskEntry)


        //init .itemView.setOnClickListener {
        // mylistener.onItemClick(adapterPosition) }
        // We set an onclicklistener on the checkbox
        // assigned a variable to the mutablelist called tasked in the current adapter position and checked if it is checked
        /* checkItOff.setOnClickListener {

            var isCurrentlyChecked = tasks[adapterPosition].isChecked

            tasks[adapterPosition].isChecked = !(false)
            checkItOff.isChecked = isCurrentlyChecked
            notifyItemRemoved(adapterPosition)//!() function inverts the logical meaning of the enclosed numeric expression.
        }

        */
    }

    /*fun removeitems(){
     tasks.removeIf { it.isChecked }
     notifyDataSetChanged()
     }
         */
    fun addItem(newTaskAdd: Taskwritten) {
        tasks.add(newTaskAdd)
        notifyItemInserted(tasks.size - 1) //

    }

    fun deleteItems() {
        tasks.removeAll { completed -> // we gave the name "completed"
            completed.isChecked // we are removing an item when and item is checked
        }
        notifyDataSetChanged()
    }    //tasks.removeAt(index)
    // ** need to have it removed at the selected index based on checkbox
}       //notifyDataSetChanged()




