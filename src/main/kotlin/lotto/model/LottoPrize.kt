package lotto.model

enum class LottoPrize(
    private val matchNumbers: Int,
    private val price: Int
) {
    BOOM(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    fun getMatchNumbers(): Int {
        return matchNumbers
    }

    fun getPrice(): Int {
        return price
    }

    companion object {
        fun getLottoMessage(lottoPrize: LottoPrize): String {
            return when (lottoPrize) {
                BOOM -> "꽝"
                FIFTH -> "3개 일치 (5,000원) -"
                FOURTH -> "4개 일치 (50,000원) -"
                THIRD -> "5개 일치 (1,500,000원) -"
                SECOND -> "5개 일치, 보너스 볼 일치 (30,000,000원) -"
                FIRST -> "6개 일치 (2,000,000,000원) -"
            }
        }
    }
}
