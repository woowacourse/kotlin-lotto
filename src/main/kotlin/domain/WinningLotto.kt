package domain

class WinningLotto(val winngLotto: List<LottoNumber>, val bonusNumber: LottoNumber) {
    init {
        require(!winngLotto.contains(bonusNumber)) { BONUS_NUMBER_OVERLAY_ERROR_MESSAGE }
    }

    companion object {
        const val BONUS_NUMBER_OVERLAY_ERROR_MESSAGE = "보너스 번호가 당첨 번호에 포함되어 있습니다."
    }
}