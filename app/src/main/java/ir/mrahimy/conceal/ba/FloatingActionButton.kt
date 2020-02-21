package ir.mrahimy.conceal.ba

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("colorFilter")
fun FloatingActionButton.setColorFilter(@ColorRes color: Int?) {
    val myFabSrc = resources.getDrawable(android.R.drawable.ic_input_add)
    val willBeWhite = myFabSrc.constantState!!.newDrawable()
    willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
    setImageDrawable(willBeWhite)
}