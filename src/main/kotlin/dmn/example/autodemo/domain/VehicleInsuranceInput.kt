package dmn.example.autodemo.domain

/***
 * Defines the input keys exactly as defined in the Camunda DMN diagram.
 */
enum class VehicleInsuranceInputKey {
    // defines the model of the vehicle
    model,
    //defines id the vehicle is new or user
    new,
    //defines the way the vehicle will be financed e.g. credit, leasing, cash
    financing
}