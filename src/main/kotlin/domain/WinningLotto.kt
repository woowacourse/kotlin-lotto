package domain

class WinningLotto(val winningLotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!winningLotto.containsNumber(bonusNumber)) { BONUS_NUMBER_OVERLAY_ERROR_MESSAGE }
    }

    fun matchLotto(lotto: Lotto): Rank {
        return Rank.valueOf(lotto.countSameLottoNumber(winningLotto), lotto.containsNumber(bonusNumber))
    }

    fun matchLottos(lottos: Lottos): WinningResult {
        return lottos.lottos.map { matchLotto(it) }
            .groupingBy { it }
            .eachCount()
            .let { WinningResult(it) }
    }

    companion object {
        const val BONUS_NUMBER_OVERLAY_ERROR_MESSAGE = "보너스 번호가 당첨 번호에 포함되어 있습니다."
    }
}
