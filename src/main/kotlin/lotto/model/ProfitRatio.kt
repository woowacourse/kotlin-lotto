package lotto.model

import kotlin.math.floor

class ProfitRatio(ratio: Double) {
    private val ratio = ratio.floor()

    private fun Double.floor() = floor(this * 100) / 100

    override fun toString() = "%.2f".format(ratio)
}
