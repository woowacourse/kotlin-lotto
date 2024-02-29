package model.profit

data class ProfitRate(val rate: Double) {
    fun decideProfitStatus(): ProfitStatus =
        when {
            rate > 1.0 -> ProfitStatus.GAIN
            rate < 1.0 -> ProfitStatus.LOSS
            else -> ProfitStatus.EVEN
        }
}
