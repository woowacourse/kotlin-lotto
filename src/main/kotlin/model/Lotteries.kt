package model

data class Lotteries(val lotteries: List<Lottery>) {
    companion object {
        fun from(
            manualLotteries: Lotteries,
            autoLotteries: Lotteries,
        ): Lotteries {
            val lotteries = manualLotteries.lotteries.toMutableList()
            lotteries.addAll(autoLotteries.lotteries)
            return Lotteries(lotteries)
        }
    }
}
