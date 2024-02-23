package model

data class PrizeCount(val count: Int = DEFAULT_PRIZE_COUNT) {
    init {
        check(count >= 0) { "로또의 당첨 개수는 0 이상이어야 합니다." }
    }

    operator fun plus(other: PrizeCount): PrizeCount = PrizeCount(count + other.count)

    companion object {
        private const val DEFAULT_PRIZE_COUNT = 0
    }
}
