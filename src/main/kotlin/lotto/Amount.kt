package lotto

class Amount private constructor(
    val money: Int,
) {
    fun getCount(lottoPrize: Int): Int = money / lottoPrize

    companion object {
        fun createOrNull(input: Int): Amount? {
            if (input <= 0) return null
            return Amount(input)
        }
    }
}
