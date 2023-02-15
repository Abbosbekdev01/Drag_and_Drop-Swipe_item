package uz.abbosbek.drag_and_drop_swipe_item.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.drag_and_drop_swipe_item.databinding.ItemRvBinding
import uz.abbosbek.drag_and_drop_swipe_item.models.Person
import uz.abbosbek.drag_and_drop_swipe_item.utils.ItemTouchHelperAdapter
import java.util.*
import kotlin.collections.ArrayList

class PersonAdapter(val list: ArrayList<Person>) : RecyclerView.Adapter<PersonAdapter.Vh>(),ItemTouchHelperAdapter {
    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(person: Person) {
            itemRvBinding.tvName.setText(person.name)
            itemRvBinding.tvNumber.setText(person.number.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list,i,i)
            }
        }else{
            for (i in fromPosition until toPosition + 1){
                Collections.swap(list,i,i)
            }
        }
        /** Item ni qayerdan qayergacha bo'lganni yangilsh uchun ishlatiladi*/
        notifyItemMoved(fromPosition, toPosition)
    }

    /** onItemDismiss ishlaganda itemni o'chirish uchun ishlaydi*/
    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}