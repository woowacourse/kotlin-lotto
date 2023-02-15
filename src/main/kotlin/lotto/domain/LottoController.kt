package lotto.domain

class LottoController {

    fun getNumberOfLotto(money: Int): Int {
        return money / LOTTO_PRICE
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
