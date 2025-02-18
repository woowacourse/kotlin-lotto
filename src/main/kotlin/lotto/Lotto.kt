package lotto

class Lotto {
    val price: Int = LOTTO_PRICE
    val numbers: List<Int> = (1..45).shuffled().take(6)

    companion object {
        const val LOTTO_PRICE: Int = 1_000
    }
}
