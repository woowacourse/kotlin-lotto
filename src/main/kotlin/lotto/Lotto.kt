package lotto

class Lotto() {
    val lottoNums = listOf(1, 2, 3, 4, 5, 6)

    init {
        require(lottoNums.size == DEFAULT_LOTTO_SIZE) { "로또는 6개의 숫자를 가져야 합니다" }
    }

    companion object {
        const val DEFAULT_LOTTO_SIZE = 6
    }
}
