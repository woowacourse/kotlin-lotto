package lotto.model

enum class LottoRank(val countOfMatch: Int, val winningMoney: Int, private val matchBonus: Boolean? = null) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    fun description(): String {
        val bonusMatchText =
            when (matchBonus) {
                true -> ", 보너스 볼 일치"
                else -> ""
            }
        return "${countOfMatch}개 일치$bonusMatchText (${winningMoney}원)"
    }

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): LottoRank {
            return entries.find { lottoRank ->
                lottoRank.countOfMatch == countOfMatch && (lottoRank.matchBonus == null || lottoRank.matchBonus == matchBonus)
            } ?: MISS
        }
    }
}
