package kg.turar.arykbaev.letstalk.extension

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.ui.MainActivity

fun Fragment.showMessage(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.showWarningSnackbar(message: String, anchorView: View? = null) {
    val rootView: View = (requireActivity()).window.decorView.findViewById(android.R.id.content)
    val snackbar = Snackbar.make(anchorView ?: rootView, "", Snackbar.LENGTH_LONG)
    val customSnackbar: View = LayoutInflater.from(requireContext()).inflate(
        R.layout.view_snackbar_warning,
        null
    )
    val tv = customSnackbar.findViewById<TextView>(R.id.message_warning)
    tv.text = message.parseAsHtml()
    snackbar.apply {
        anchorView?.let { setAnchorView(it) }
        view.setBackgroundColor(Color.TRANSPARENT)
        (snackbar.view as Snackbar.SnackbarLayout).apply {
            val margin = resources.getDimension(R.dimen.padding_8dp).toInt()
            setPadding(margin, margin, margin, margin)
            addView(customSnackbar, 0)
        }
        show()
    }
}

fun Fragment.hideKeyboard() {
    val activity = (activity as MainActivity)
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = activity.currentFocus
    view?.let { imm.hideSoftInputFromWindow(it.windowToken, 0) }
}
