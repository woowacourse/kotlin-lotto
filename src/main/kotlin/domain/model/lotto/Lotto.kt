package domain.model.lotto

class Lotto(val numbers: Set<LottoNumber>) {

    init {
        require(numbers.size == NUMBER_COUNT) {
            NUMBER_COUNT_ERROR
        }
    }

    fun getMatchCount(otherLotto: Lotto): Int = numbers.intersect(otherLotto.numbers).size

    fun contains(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)

    companion object {
        private const val NUMBER_COUNT = 6

        private const val NUMBER_COUNT_ERROR = "[ERROR] 로또 번호는 6개의 숫자로 이루어져야 합니다."
    }
}
