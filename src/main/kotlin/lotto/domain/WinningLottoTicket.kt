package lotto.domain

data class WinningLottoTicket(
    val lotto: Lotto,
    val bonusNumber: LottoNumber,
) {
    fun getCountOfMatchWith(contrast: Lotto): Int = lotto.value.count { it in contrast.value }

    fun isMatchedBonusWith(contrast: Lotto): Boolean = lotto.contains(bonusNumber) && !contrast.contains(bonusNumber)
}
