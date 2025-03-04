package lotto.domain

// 각 등수 : 나온 숫자 의 맵을 저장하는 일급컬렉션
data class RankScoreBoard(
    val map: Map<Rank, Int>,
) {
    init {
        requireRankMap()
    }

    fun getRate(): String {
        val earned = getEarned()
        val paid = getPaid()
        return if (paid == 0) "0.0" else String.format("%.2f", earned.toDouble() / paid.toDouble())
    }

    fun getEarned() = map.entries.sumOf { it.key.winningMoney * it.value }

    fun getPaid() = map.values.sum() * Lotto.LOTTO_PRICE

    private fun requireRankMap() {
        val missingKeys = Rank.entries.filter { it !in map }
        require(missingKeys.isEmpty()) { IllegalArgumentException("올바르지 않은 값입니다") }
    }

    companion object {
        /**
         * Rank 클래스에 없는 key에는 자동으로 0을 채운 후 객체를 안전하게 생성합니다
         * 생성자로 생성 시 인자의 map에는 Rank 의 모든 key가 있어야 합니다
         * @param:Map<Rank, Int>
         * @return RankScoreBoard
         */
        fun fromNecessaryKey(rawMap: Map<Rank, Int>): RankScoreBoard {
            val map = Rank.entries.associateWith { rawMap[it] ?: 0 }
            return RankScoreBoard(map)
        }
    }
}
