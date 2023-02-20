package view

import domain.Lotto
import domain.LottoResult
import domain.Rank

class OutputView {
    fun outputGetAmount() {
        println("구입금액을 입력해 주세요.")
    }

    fun outputGetCount() {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
    }

    fun outputGetManualLottos() {
        println("수동으로 구매할 번호를 입력해 주세요.")
    }

    fun outputGetWinningNumbers() {
        println("지난 주 당첨 번호를 입력해 주세요.")
    }

    fun outputGetBonusNumber() {
        println("보너스 볼을 입력해 주세요.")
    }

    fun outputLottos(manual: List<Lotto>, auto: List<Lotto>) {
        println("수동으로 ${manual.size}장, 자동으로 ${auto.size}개를 구매했습니다.")
        (manual + auto).forEach { println(it.toList()) }
        println()
    }

    fun outputResult(lottoResult: LottoResult) {
        println("\n당첨 통계")
        println("---------")
        for (value in Rank.values().reversed()) {
            if (value == Rank.MISS) continue
            if (value.mustHaveBonus) {
                println("%d개 일치, 보너스 볼 일치 (%s)원 - %d개".format(value.countOfMatch, value.winningMoney, lottoResult[value]))
                continue
            }
            println("%d개 일치 (%s)원 - %d개".format(value.countOfMatch, value.winningMoney, lottoResult[value]))
        }
        println("총 수익률은 ${lottoResult.getRateOfReturn()}입니다.")
    }
}
