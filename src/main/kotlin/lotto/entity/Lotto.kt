package lotto.entity

class Lotto(val numbers: List<Int>) {
    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val LOTTO_MINIMUM_RANGE = 0
        const val LOTTO_COUNT = 6
    }
}
