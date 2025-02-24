package lotto.service

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoProfitCalculator
import lotto.model.Rank
import lotto.model.WinningDiscriminator

class LottoService {
    fun getPurchaseLottos(purchaseAmount: Int): List<Lotto> {
        val lottoCashier = LottoCashier(purchaseAmount)
        val lottoMachine = LottoMachine()

        return lottoMachine.getLottos(lottoCashier.getPurchaseQuantity())
    }

    fun getLottosDiscriminateResult(
        lottos: List<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        val winningLotto = Lotto.from(winningNumbers)
        val bonusLottoNumber = LottoNumber.from(bonusNumber)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusLottoNumber)

        return winningDiscriminator.getResult(lottos)
    }

    fun getProfitRate(
        lottoWinningResult: Map<Rank, Int>,
        purchaseAmount: Int,
    ): Float = LottoProfitCalculator().getProfitRate(lottoWinningResult, purchaseAmount)
}
