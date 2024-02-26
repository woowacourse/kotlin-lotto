package lotto.model

import kotlin.math.floor

class ProfitRatio(ratio: Double) {
    val ratio = ratio.floor()

    private fun Double.floor() = floor(this * 100) / 100
}
