package lotto.entity

class ProfitRate(val value: Float) {
    fun roundDown(): Float = (value * 100).toInt() / 100f
}
