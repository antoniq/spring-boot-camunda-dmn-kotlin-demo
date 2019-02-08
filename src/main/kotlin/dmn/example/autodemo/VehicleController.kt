package dmn.example.autodemo

import dmn.example.autodemo.domain.Financing
import dmn.example.autodemo.domain.Model
import dmn.example.autodemo.domain.PaymentMethod
import dmn.example.autodemo.domain.VehicleAge
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auto/cost")
class VehicleController(
    private val vehicleService: VehicleService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAutoInsuranceCost(
        @RequestParam("model") model: String,
        @RequestParam("isNew") isNew: Boolean,
        @RequestParam("financing") financing: String
    ): FullCascoPayload {
        val finalCost = vehicleService.getFullCascoCost(
            Model(model), VehicleAge(isNew), Financing(PaymentMethod.valueOf(financing))
        )
        return FullCascoPayload(
            model = model,
            newVehicle = isNew,
            financing = financing,
            totalCost = finalCost
        )
    }
}