package lotto.domain

// 각 등수 : 나온 숫자 의 맵을 저장하는 일급컬렉션
class ScoreRankMap(
    val map: Map<Rank, Int>,
) {
    init {
        requireRankMap()
    }

    fun getEarned() = map.entries.sumOf { it.key.winningMoney * it.value }

    fun getPaid() = map.values.sum() * LOTTO_PRICE

    private fun requireRankMap() {
        val missingKeys = Rank.entries.filter { it !in map }
        require(missingKeys.isEmpty()) { IllegalArgumentException("올바르지 않은 값입니다") }
    }
}
