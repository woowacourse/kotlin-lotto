package lotto.domain

import lotto.view.InputInterface

class FakeInputView(
    private val purchaseMoney: Int = 0,
    private val manualLottoCount: Int = 0,
    private val manualLottoNumbers: List<Int> = emptyList(),
    private val winningLottoNumbers: List<Int> = emptyList(),
    private val winningBonusNumber: Int = 0,
) : InputInterface {
    override fun getPurchaseMoney(): Int = purchaseMoney

    override fun getManualLottoCount(): Int = manualLottoCount

    override fun getManualLottoNumbers(): List<Int> = manualLottoNumbers

    override fun getWinningLottoNumbers(): List<Int> = winningLottoNumbers

    override fun getWinningBonusNumber(): Int = winningBonusNumber
}
