package kg.turar.arykbaev.letstalk.extension

import androidx.appcompat.app.AlertDialog
import java.util.*

fun AlertDialog.Builder.positiveButton(btnText: String = "YES", handleClick: () -> Unit = {}) {
    this.setPositiveButton(btnText.toUpperCase(Locale.ROOT)) { dialogInterface, _ ->
        handleClick()
        dialogInterface.dismiss()
    }
}

fun AlertDialog.Builder.negativeButton(btnText: String = "NO", handleClick: () -> Unit = {}) {
    this.setNegativeButton(btnText.toUpperCase(Locale.ROOT)) { dialogInterface, _ ->
        handleClick()
        dialogInterface.dismiss()
    }
}