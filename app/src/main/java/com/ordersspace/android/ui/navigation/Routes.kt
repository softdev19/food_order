package com.ordersspace.android.ui.navigation

object CommonRoutes {

    const val login = "/login"
    const val signup = "/signup"
}

object CustomerRoutes {

    const val main = "/customer"

    const val map = "$main/map"

    const val menu = "$main/menu"
    const val place = "$menu/place"
    const val item = "$menu/item"
    const val checkout = "$menu/checkout"

    const val profile = "$main/profile"
    const val status = "$profile/status"
}

object EmployeeRoutes {

    const val main = "/employee"

    const val shift = "$main/shift"

    const val orders = "$main/orders"
    const val status = "$orders/status"
}