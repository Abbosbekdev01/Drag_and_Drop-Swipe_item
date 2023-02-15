package uz.abbosbek.drag_and_drop_swipe_item.utils

interface ItemTouchHelperAdapter {

    /** Item larni ko'chirish uchun ishlatiladi*/
    fun onItemMove(fromPosition:Int, toPosition:Int)

    /** Item larni ong yoki chapga o'tkazish uchun ishlatiladi*/
    fun onItemDismiss(position:Int)
}