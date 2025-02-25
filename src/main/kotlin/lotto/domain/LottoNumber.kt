package lotto.domain

data class LottoNumber(
    val number: Int,
) {
    init {
        require(number in MIN_RANGE..MAX_RANGE) { "로또 번호는 1에서 45까지의 숫자이다" }
    }

    companion object {
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
    }
}
