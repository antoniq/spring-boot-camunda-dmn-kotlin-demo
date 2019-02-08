package dmn.example.autodemo

/**
 * Defines the payload that is returned as response.
 */
data class FullCascoPayload(
    val model: String,

    val newVehicle: Boolean,

    val financing: String,

    val totalCost: Double
)