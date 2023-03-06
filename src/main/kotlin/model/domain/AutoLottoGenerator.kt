package model.domain

import model.LottoNumber

class AutoLottoGenerator : LottoGenerator {
    override fun generate(): List<LottoNumber> = getAutoLotto()

    private fun getAutoLotto(): List<LottoNumber> {
        return changeNumberToLottoNumber().take(LOTTO_NUMBER_COUNT).sortedBy { lottoNumber ->
            lottoNumber.value
        }
    }

    private fun changeNumberToLottoNumber(): List<LottoNumber> {
        return initShuffledNumbers().map { number ->
            LottoNumber(number)
        }
    }

    private fun initShuffledNumbers(): List<Int> = range.toList().shuffled()

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private const val LOTTO_NUMBER_COUNT = 6
        private val range = MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER
    }
}
