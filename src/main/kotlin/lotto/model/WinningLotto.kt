package lotto.model

data class WinningLotto(val winning: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!winning.checkBonusNumbers(bonusNumber)) { "보너스 숫자는 당첨 번호와 중복될 수 없습니다." }
    }
}
