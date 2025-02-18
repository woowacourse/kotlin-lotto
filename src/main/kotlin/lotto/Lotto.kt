package lotto

class Lotto(val lottoNums: List<Int>) {
    init {
        require(lottoNums.size == DEFAULT_LOTTO_SIZE) { "로또는 6개의 숫자를 가져야 합니다" }
        require(lottoNums.all { it in 1..45 }) { "로또 번호는 1에서 45까지의 숫자이다" }
    }

    companion object {
        const val DEFAULT_LOTTO_SIZE = 6
    }
}
