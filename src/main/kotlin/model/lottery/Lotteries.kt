package model.lottery

data class Lotteries(val lotteries: List<Lottery>) {
    operator fun plus(other: Lotteries): Lotteries = Lotteries(this.lotteries + other.lotteries)
}
