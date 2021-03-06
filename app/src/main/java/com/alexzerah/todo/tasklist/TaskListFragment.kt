package com.alexzerah.todo.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexzerah.todo.R
import kotlinx.android.synthetic.main.fragment_task_list.*
import java.util.*

class TaskListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val taskListAdapter = TaskListAdapter(taskList)
        recycler_view.adapter = taskListAdapter
        recycler_view.layoutManager = LinearLayoutManager(activity)
        floatingActionButton.setOnClickListener {
            val task = Task(id = UUID.randomUUID().toString(), title = "Task ${taskList.size + 1}")
            taskList.add(task)
            (recycler_view.adapter as TaskListAdapter).notifyDataSetChanged()
        }

        taskListAdapter.onDeleteClickListener = {
            task -> taskList.remove(task)
            (recycler_view.adapter as TaskListAdapter).notifyDataSetChanged()
        }
    }

    private val taskList = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id = "id_3", title = "Task 3"))

}