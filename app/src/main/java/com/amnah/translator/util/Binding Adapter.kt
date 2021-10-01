package com.amnah.translator.util

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.R
import android.widget.AdapterView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.amnah.translator.model.data.languages.LanguagesResponseItem

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T>showWhenLoading(view: View, state: State<T>?){
    if (state is State.Loading){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T>showWhenSuccess(view: View, state: State<T>?){
    if (state is State.Success){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T>showWhenError(view: View, state: State<T>?) {
    if (state is State.Error) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
@BindingAdapter(value = ["app:showEntries"])
fun setEntries(view: Spinner, entries:ArrayList<LanguagesResponseItem>?){
    if (entries!=null){
        ArrayAdapter(
            view.context,
            R.layout.simple_spinner_dropdown_item, (entries.map { "${it.code}" })
        ).also {
            it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            view.adapter = it
        }
    }

}
@BindingAdapter(value = ["app:selectedValueOfItem"]) //set
fun selectedItem(view: Spinner, item: String?){
    if (view.selectedItem?.toString() != item){
        view.setSelection(view.getSelectedIndex(item))
    }
}

@InverseBindingAdapter(attribute = "selectedValueOfItem", event = "SpinnerOnItemSelected") //get
fun captureSelectedValue(view: Spinner): String? {
    return view.selectedItem.toString()
}

@BindingAdapter("SpinnerOnItemSelected")
fun setSelectedListener(view: Spinner, changeListener: InverseBindingListener) {
    view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            changeListener.onChange()
        } 
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }
}