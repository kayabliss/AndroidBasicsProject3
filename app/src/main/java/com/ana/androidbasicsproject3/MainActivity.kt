package com.ana.androidbasicsproject3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caren.androidbasicsproject3.TaskItemAdapter
import com.caren.androidbasicsproject3.Taskwritten
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val INTENT_DATA_NAME = "data"

class MainActivity : AppCompatActivity() {


    val tasks = mutableListOf<Taskwritten>()
    var positionEditted = -1
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                //cODE THAT'S CALLED WHEN WE COME BACK FROM SECOND ACTIVITY
                //if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes

                val data: Intent? = result.data
                Log.i(
                    "ana",
                    "data passed from secondActivity" + data?.getStringExtra(INTENT_DATA_NAME)
                )
                //
                var userInputSecAct = data?.getStringExtra(INTENT_DATA_NAME)
                //getStringExtra, allows us to get the data that was sent from one activity to another
                var newTextSec = findViewById<TextView>(R.id.task).text.get(positionEditted)
                //tasks.add(userInputtedStringFromSecondActivity.toString())

                if (userInputSecAct != null) {
                    tasks[positionEditted] = Taskwritten(userInputSecAct)
                    adapter.notifyDataSetChanged()
                }
            }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        tasks.addAll(tasks)

        // When we create a new adapter, now we have to pass both a mutablelistof and an object, which is going to be an onItemClickListener
        // I am going to create a new object, which is going to be an onItemClickListener and I must implement this function called on item click
        adapter = TaskItemAdapter(mutableListOf(), object : TaskItemAdapter.onItemClickListener {
            override fun onItemClick(index: Int) {

                Log.i("ana", "Clicked on item in list: $index")
                //positionEditted = index

                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                // var userInputTextMain = tasks[positionEditted].todoTask.toString()
                intent.putExtra(INTENT_DATA_NAME, tasks[index])
                //startActivity(i)
                resultLauncher.launch(intent)
            }

        })


        // This will pass an empty list, to our adapter
        //object
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)//arranges how things will layout
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        findViewById<View>(R.id.fabAdd).setOnClickListener {
            val taskEntryEditTextField = findViewById<EditText>(R.id.taskEntry)

            // Get the new task entered
            val newTask = taskEntryEditTextField.text.toString()
            // Clear the EditText field

            if (newTask.isNotEmpty()) {// Checking if list is empty,we don't want to add empty items
                val listAdd = Taskwritten(newTask)
                adapter.addItem(listAdd)//this will add the task to our list
                taskEntryEditTextField.text.clear()//clear the content of our edit text
                adapter.notifyDataSetChanged()
            }
        }


        //tasks.add(Taskwritten(newTask.toString()))
        // adapter.notifyItemChanged(positionEditted)


        /*fun deleteItems(index: Int){
            tasks.removeAt(index)

            // ** need to have it removed at the selected index based on checkbox
        } */

        // to fix
        //1. set on clicklisener inside the adapter for the checkbox view
        // 2. main activity :   were do a for loop that is going to check which checkboxes were checked.


        // Setting Delete button with onclickListener

        findViewById<FloatingActionButton>(R.id.fabDelete).setOnClickListener {
            adapter.deleteItems()

        }


        // }
    }
}


