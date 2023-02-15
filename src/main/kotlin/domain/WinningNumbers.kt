package domain

class WinningNumbers(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!lotto.lottoNumbers.contains(bonusNumber)) { "$PREFIX 보너스 번호가 당첨 번호와 중복되면 안된다." }
    }

    companion object {
        const val PREFIX = "[Error]"
    }
}