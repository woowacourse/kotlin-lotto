package domain

import domain.lotto.Lotto
import domain.lotto.LottoBundleDto
import domain.lotto.LottoNumber
import util.PREFIX

class WinningNumbers(private val lotto: Lotto, private val bonusNumber: LottoNumber) {
    init {
        require(!lotto.has(bonusNumber)) { "$PREFIX 보너스 번호가 당첨 번호와 중복되면 안된다." }
    }

    private fun compareLotto(purchasedLotto: Lotto): ComparingResultDto {
        return ComparingResultDto(
            purchasedLotto.countSameNumber(lotto),
            purchasedLotto.has(bonusNumber),
        )
    }

    fun compareLottoBundle(purchasedLottoBundle: LottoBundleDto): WinningResultDto {
        return WinningResultDto(purchasedLottoBundle.lottos.map { compareLotto(it) })
    }
}
