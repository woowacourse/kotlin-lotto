package lotto.model

class LottoNumbers(private val numbers: Set<LottoNumber>) {

    init {
        if (numbers.size != Lotto.LOTTO_LEN)
            throw LottoEvent.checkLotto(LottoEvent.InvalidNumCount)
    }

    fun getNumbers(): Set<Int> {
        return numbers
            .map { it.getNumber() }
            .sorted()
            .toSet()
    }

    companion object {
        fun checkNumbersValid(numbers: List<String>): Set<LottoNumber> {
            val lottoNumbers = numbers.map {
                LottoNumber.checkLottoNumberValid(it)
            }.toSet().also {
                if (it.size != numbers.size) throw LottoEvent.checkLotto(LottoEvent.InvalidDuplication)
            }
            return lottoNumbers.map {
                LottoNumber(it)
            }.toSet()
        }
    }
}
