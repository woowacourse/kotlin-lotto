package lotto.model

data class LotteryResult(val winning: Lotto, val bonusNumber: Int) {
    init {
        require(!winning.checkBonusNumbers(bonusNumber)) { "보너스 숫자는 당첨 번호와 중복될 수 없습니다." }
        require(bonusNumber in Lotto.NUMBER_RANGE) { "보너스 숫자의 범위는 ${Lotto.NUMBER_RANGE.first}~${Lotto.NUMBER_RANGE.last} 사이의 숫자입니다." }
    }
}
