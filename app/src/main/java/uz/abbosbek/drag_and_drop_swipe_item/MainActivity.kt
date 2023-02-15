package uz.abbosbek.drag_and_drop_swipe_item

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.drag_and_drop_swipe_item.adapters.PersonAdapter
import uz.abbosbek.drag_and_drop_swipe_item.databinding.ActivityMainBinding
import uz.abbosbek.drag_and_drop_swipe_item.models.Person
import uz.abbosbek.drag_and_drop_swipe_item.utils.ItemTouchHelperAdapter

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var personAdapter: PersonAdapter
    private lateinit var list: ArrayList<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()
        personAdapter = PersonAdapter(list)
        binding.rv.adapter = personAdapter

        val itemTouchHelper = object :ItemTouchHelper.Callback(){

            /** getMovementFlags -> RecyclerView ni item ni qaysi tarafga harakatlanishini boshqarish uchun ishlatiladi*/
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            /** onMove - item ni ko'chirish uchun ishlatiladi */
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                /** item ni turgan joyidan boshqa joyga o'zgartirish uchun <viewHolder - tanlangan item, target - tanlangan item ni yangi joyga yoylaydi*/
                personAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            /** onSwiped -> item ni o'nga yoki chapga surish uchun ishlatiladi*/
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                personAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }

        /** Tanlangan item ustida amal bajarish uchun ishlatiladi*/
        val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(binding.rv)
    }

    private fun loadData() {
        list = ArrayList()
        for (i in 0 .. 100){
            list.add(Person("Abdulhay", 22))
            list.add(Person("Abdulhay", 22))
            list.add(Person("Abdulhay", 22))
            list.add(Person("Abdulhay", 22))
        }
    }
}