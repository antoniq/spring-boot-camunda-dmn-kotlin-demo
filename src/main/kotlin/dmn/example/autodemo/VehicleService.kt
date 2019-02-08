package dmn.example.autodemo

import dmn.example.autodemo.domain.VehicleInsuranceInputKey
import dmn.example.autodemo.domain.Financing
import dmn.example.autodemo.domain.Model
import dmn.example.autodemo.domain.VehicleAge
import mu.KotlinLogging
import org.camunda.bpm.engine.DecisionService
import org.springframework.stereotype.Service

// Defines the key of the Camunda DMN diagram as const.
private const val AUTO_INSURANCE_DMN_KEY = "autoInsurance"

@Service
class VehicleService(
    val decisionService: DecisionService
) {
    private val log = KotlinLogging.logger {}

    fun getFullCascoCost(
        model: Model, vehicleAge: VehicleAge, financing: Financing
    ): Double {
        log.info { "Evaluating rule..." }
        val result = decisionService
            .evaluateDecisionTableByKey(
                AUTO_INSURANCE_DMN_KEY, setValues(model, vehicleAge, financing)
            )
        log.debug { "Rule received with ${result}" }
        val finalCost = result.firstResult.getFirstEntry<Double>()
        log.info { "Final cost is '${finalCost}'" }
        return finalCost
    }


    /**
     * Maps the values that are needed for the decision evaluation.
     */
    private fun setValues(
        model: Model,
        vehicleAge: VehicleAge,
        financing: Financing
    ): Map<String, Any> =
        mapOf(
            Pair(VehicleInsuranceInputKey.model.toString(), model.value),
            Pair(VehicleInsuranceInputKey.new.toString(), vehicleAge.value),
            Pair(VehicleInsuranceInputKey.financing.toString(), financing.value.toString())
        )
}
