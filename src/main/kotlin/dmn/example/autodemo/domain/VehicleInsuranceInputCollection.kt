package dmn.example.autodemo.domain

data class Model(
    val value: String
)

data class VehicleAge(
    val value: Boolean
)

data class Financing(
    val value: PaymentMethod
)

enum class PaymentMethod {
    credit,
    leasing,
    cash
}