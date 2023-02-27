package lotto.controller

import lotto.view.LottoInputView

class TestInputView : LottoInputView {
    override fun readMoney(): Int = 4000

    override fun readManualLottoCount(): Int = 2

    override fun readManualLottoNumber(count: Int): List<List<Int>> =
        listOf(listOf(1, 2, 3, 4, 5, 6), listOf(7, 8, 9, 10, 11, 12))

    override fun readWinningLottoNumber(): List<Int> = listOf(1, 2, 3, 7, 8, 9)

    override fun readBonusNumber(): Int = 10
}
