package view

import domain.LottoBundle
import domain.LottoNumber
import domain.Payment
import domain.Rank
import domain.TotalPrize
import domain.WinStatistics

object OutputView {

    fun printRequestMoney() {
        println("구입금액을 입력해 주세요.")
    }

    fun printRequestManualCount() {
        println()
        println("수동으로 구매할 로또 수를 입력해 주세요.")
    }

    fun printRequestManualLottos() {
        println()
        println("수동으로 구매할 번호를 입력해 주세요.")
    }

    fun printPurchasedLottoCount(manualCount: Int, autoCount: Int) {
        println()
        if (manualCount == 0) {
            println("자동으로 ${autoCount}개를 구매했습니다.")
            return
        }
        println("수동으로 ${manualCount}장, 자동으로 ${autoCount}개를 구매했습니다.")
    }

    fun printPurchasedLotto(lottoBundle: LottoBundle) {
        println()
        lottoBundle.lottos.forEach {
            println(it.lottoNumbers.map { lottoNumber: LottoNumber -> lottoNumber.number })
        }
        println()
    }

    fun printRequestWinningNumbers() {
        println("지난 주 당첨 번호를 입력해 주세요.")
    }

    fun printRequestBonusNumber() {
        println("보너스 볼을 입력해 주세요.")
    }

    fun printWinStatistics(winStatistics: WinStatistics) {
        println()
        println("당첨 통계")
        println("---------")
        winStatistics.rankCount.toList().asReversed().forEach {
            printRankCount(it)
        }
    }

    private fun printRankCount(rankCount: Pair<Rank, Int>) {
        if (rankCount.first == Rank.SECOND) {
            println("${rankCount.first.countOfMatch}개 일치, 보너스 볼 일치(${rankCount.first.winningMoney}원)- ${rankCount.second}개")
            return
        }
        if (rankCount.first == Rank.MISS) {
            return
        }
        println("${rankCount.first.countOfMatch}개 일치 (${rankCount.first.winningMoney}원)- ${rankCount.second}개")
    }

    fun printEarningRate(totalPrize: TotalPrize, spendPayment: Payment) {
        println("총 수익률은 ${totalPrize.getEarningRate(spendPayment)}입니다.")
    }
}
