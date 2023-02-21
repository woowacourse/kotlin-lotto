package view

import domain.Lotto
import domain.LottoResult
import domain.Rank
import kotlin.math.floor

class OutputView {
    fun outputGetAmount() {
        println("구입금액을 입력해 주세요.")
    }

    fun outputGetNumberOfLottosToBuyManually() {
        println("\n수동으로 구매할 로또 수를 입력해 주세요.")
    }

    fun outputGetWinningNumbers() {
        println("지난 주 당첨 번호를 입력해 주세요.")
    }

    fun outputGetBonusNumber() {
        println("보너스 볼을 입력해 주세요.")
    }

    fun outputLottos(lottos: List<Lotto>) {
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(it) }
        println()
    }

    fun outputResult(lottoResult: LottoResult) {
        println("\n당첨 통계")
        println("---------")
        println("3개 일치 (${Rank.FIFTH.winningMoney})원 - ${lottoResult[Rank.FIFTH]}개")
        println("4개 일치 (${Rank.FOURTH.winningMoney})원 - ${lottoResult[Rank.FOURTH]}개")
        println("5개 일치 (${Rank.THIRD.winningMoney})원 - ${lottoResult[Rank.THIRD]}개")
        println("5개 일치, 보너스 볼 일치(${Rank.SECOND.winningMoney}원) - ${lottoResult[Rank.SECOND]}개")
        println("6개 일치 (${Rank.FIRST.winningMoney})원 - ${lottoResult[Rank.FIRST]}개")
        println("총 수익률은 ${floor(lottoResult.getRateOfReturn() * 100) / 100}입니다.")
    }
}
